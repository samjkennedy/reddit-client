package com.skennedy.reddit.client.account;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.skennedy.reddit.client.account.model.Account;
import com.skennedy.reddit.client.account.model.KarmaListing;
import com.skennedy.reddit.client.account.model.KarmaThing;
import com.skennedy.reddit.client.account.model.SubredditKarma;
import com.skennedy.reddit.client.authorization.model.Access;
import com.skennedy.reddit.client.common.response.Fail;
import com.skennedy.reddit.client.common.response.Response;
import com.skennedy.reddit.client.common.util.RequestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class AccountClientImpl implements AccountClient {

    private static final String ME = RequestUtils.DOMAIN + "/me";
    private static final String KARMA = RequestUtils.DOMAIN + "/me/karma";

    private final Access access;
    private final CloseableHttpClient httpClient;
    private final Gson gson;

    public AccountClientImpl(Access access, CloseableHttpClient httpClient) {
        this.access = access;
        this.httpClient = httpClient;
        gson = new GsonBuilder().create();
    }

    @Override
    public Response<Account> me() throws Exception {

        HttpGet get = new HttpGet(ME);
        get.setHeader(HttpHeaders.USER_AGENT, RequestUtils.USER_AGENT);

        get.setHeader(HttpHeaders.AUTHORIZATION, "bearer " + access.getAccessToken());

        try (CloseableHttpResponse response = httpClient.execute(get)) {
            String content = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
            if (response.getStatusLine().getStatusCode() != 200) {
                return Response.error(new Fail<>(RequestUtils.parseError(response))); //TODO: Define errors
            }
            return Response.success(gson.fromJson(content, Account.class));
        }
    }

    @Override
    public Response<List<SubredditKarma>> karma() throws Exception {

        HttpGet get = new HttpGet(KARMA);
        get.setHeader(HttpHeaders.USER_AGENT, RequestUtils.USER_AGENT);

        get.setHeader(HttpHeaders.AUTHORIZATION, "bearer " + access.getAccessToken());

        try (CloseableHttpResponse response = httpClient.execute(get)) {
            String content = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
            if (response.getStatusLine().getStatusCode() != 200) {
                return Response.error(new Fail<>(RequestUtils.parseError(response)));
            }
            KarmaListing karmaListing = gson.fromJson(content, KarmaListing.class);
            return Response.success(karmaListing.getChildren().stream()
                    .map(KarmaThing::getData)
                    .collect(Collectors.toList()));
        }
    }
}
