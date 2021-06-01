package com.skennedy.reddit.client.authorization;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.skennedy.reddit.client.authorization.model.Access;
import com.skennedy.reddit.client.common.adapters.ScopeListTypeAdapter;
import com.skennedy.reddit.client.common.model.OAuthScope;
import com.skennedy.reddit.client.common.response.Fail;
import com.skennedy.reddit.client.common.response.Response;
import com.skennedy.reddit.client.common.util.RequestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

class AuthClientImpl implements AuthClient {

    @Override
    public HttpResponse authorise(String clientId, String duration, String redirectUri, List<String> scopes) throws Exception {

        List<NameValuePair> parameters = new ArrayList<>();

        parameters.add(new BasicNameValuePair("client_id", clientId));
        parameters.add(new BasicNameValuePair("response_type", "code"));
        parameters.add(new BasicNameValuePair("state", clientId)); //TODO: encrypt a state
        parameters.add(new BasicNameValuePair("redirect_uri", redirectUri));
        parameters.add(new BasicNameValuePair("duration", duration));
        parameters.add(new BasicNameValuePair("scope", String.join(" ", scopes)));

        URI uri = new URIBuilder("https://www.reddit.com/api/v1/authorize")
                .addParameters(parameters)
                .build();

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            return httpClient.execute(new HttpGet(uri));
        }
    }

    @Override
    public Response<Access> getAccess(String clientId, String authCode, String redirectUri) throws Exception {
        return getAccess(clientId, "", authCode, redirectUri);
    }

    @Override
    public Response<Access> getAccess(String clientId, String clientSecret, String authCode, String redirectUri) throws Exception {

        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {

            List<NameValuePair> params = new ArrayList<>();

            params.add(new BasicNameValuePair("grant_type", "authorization_code"));
            params.add(new BasicNameValuePair("code", authCode));
            params.add(new BasicNameValuePair("redirect_uri", redirectUri));

            HttpPost httpPost = new HttpPost("https://www.reddit.com/api/v1/access_token");
            httpPost.setEntity(new UrlEncodedFormEntity(params));

            UsernamePasswordCredentials creds = new UsernamePasswordCredentials(clientId, clientSecret);
            httpPost.setHeader(new BasicScheme().authenticate(creds, httpPost, null));
            httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
            httpPost.setHeader(HttpHeaders.USER_AGENT, RequestUtils.USER_AGENT);

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                String content = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
                if (response.getStatusLine().getStatusCode() != 200) {
                    return Response.error(new Fail<>(RequestUtils.parseError(response))); //TODO: Define errors
                }
                return Response.success(new GsonBuilder().create().fromJson(content, Access.class));
            }
        }
    }

//    @Override
//    public Response<Access> getAccess(AuthCredentials authCredentials, String redirectUri) throws Exception {
//
//        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
//
//            List<NameValuePair> params = new ArrayList<>();
//
//            params.add(new BasicNameValuePair("grant_type", "authorization_code"));
//            params.add(new BasicNameValuePair("code", authCredentials.getAuthCode()));
//            params.add(new BasicNameValuePair("redirect_uri", redirectUri));
//
//            HttpPost httpPost = new HttpPost("https://www.reddit.com/api/v1/access_token");
//            httpPost.setEntity(new UrlEncodedFormEntity(params));
//
//            UsernamePasswordCredentials creds = new UsernamePasswordCredentials(clientId, clientSecret);
//            httpPost.setHeader(new BasicScheme().authenticate(creds, httpPost, null));
//            httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
//            httpPost.setHeader(HttpHeaders.USER_AGENT, RequestUtils.USER_AGENT);
//
//            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
//                String content = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
//                if (response.getStatusLine().getStatusCode() != 200) {
//                    return Response.error(new Fail<>(content));
//                }
//                return Response.success(new GsonBuilder().create().fromJson(content, Access.class));
//            }
//        }
//    }

    @Override
    public Response<Access> refresh(String token, String clientId, String clientSecret) throws Exception {

        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {

            List<NameValuePair> params = new ArrayList<>();

            params.add(new BasicNameValuePair("grant_type", "refresh_token"));
            params.add(new BasicNameValuePair("refresh_token", token));

            HttpPost httpPost = new HttpPost("https://www.reddit.com/api/v1/access_token");
            httpPost.setEntity(new UrlEncodedFormEntity(params));

            UsernamePasswordCredentials creds = new UsernamePasswordCredentials(clientId, clientSecret);
            httpPost.setHeader(new BasicScheme().authenticate(creds, httpPost, null));
            httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
            httpPost.setHeader(HttpHeaders.USER_AGENT, RequestUtils.USER_AGENT);

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                String content = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
                if (response.getStatusLine().getStatusCode() != 200) {
                    return Response.error(new Fail<>(RequestUtils.parseError(response)));
                }
                return Response.success(new GsonBuilder()
                        //Christ
                        .registerTypeAdapter(new TypeToken<List<OAuthScope>>(){}.getType(), new ScopeListTypeAdapter())
                        .create()
                        .fromJson(content, Access.class));
            }
        }
    }
}
