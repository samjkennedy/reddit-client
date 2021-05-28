package com.skennedy.reddit.client.subreddit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.skennedy.reddit.client.authorization.model.Access;
import com.skennedy.reddit.client.common.adapters.DateLongTypeAdapter;
import com.skennedy.reddit.client.common.adapters.LanguageCodeAdapter;
import com.skennedy.reddit.client.common.model.LanguageCode;
import com.skennedy.reddit.client.common.response.Fail;
import com.skennedy.reddit.client.common.response.Response;
import com.skennedy.reddit.client.common.util.RequestUtils;
import com.skennedy.reddit.client.subreddit.model.SubredditDetails;
import com.skennedy.reddit.client.subreddit.model.SubredditDetailsThing;
import com.skennedy.reddit.client.subreddit.model.SubredditRule;
import com.skennedy.reddit.client.subreddit.model.SubredditRules;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

public class SubredditClientImpl implements SubredditClient {

    private final Access access;
    private final CloseableHttpClient httpClient;
    final Gson gson;

    public SubredditClientImpl(Access access, CloseableHttpClient httpClient) {
        this.access = access;
        this.httpClient = httpClient;
        gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateLongTypeAdapter())
                .registerTypeAdapter(LanguageCode.class, new LanguageCodeAdapter())
                .create();
    }

    @Override
    public Response<SubredditDetails> about(String subreddit) throws Exception {

        String uri = "https://oauth.reddit.com/r/" + subreddit + "/about";

        HttpGet get = new HttpGet(uri);
        get.setHeader(HttpHeaders.USER_AGENT, RequestUtils.USER_AGENT);

        get.setHeader(HttpHeaders.AUTHORIZATION, "bearer " + access.getAccessToken());

        try (CloseableHttpResponse response = httpClient.execute(get)) {
            String content = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
            if (response.getStatusLine().getStatusCode() != 200) {
                return Response.error(new Fail<>(RequestUtils.parseError(response)));
            }

            SubredditDetailsThing subredditDetailsThing = gson.fromJson(content, SubredditDetailsThing.class);

            return Response.success(subredditDetailsThing.getData());
        }
    }

    @Override
    public Response<List<SubredditRule>> rules(String subreddit) throws Exception {

        String uri = "https://oauth.reddit.com/r/" + subreddit + "/about/rules";

        HttpGet get = new HttpGet(uri);
        get.setHeader(HttpHeaders.USER_AGENT, RequestUtils.USER_AGENT);

        get.setHeader(HttpHeaders.AUTHORIZATION, "bearer " + access.getAccessToken());

        try (CloseableHttpResponse response = httpClient.execute(get)) {
            String content = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
            if (response.getStatusLine().getStatusCode() != 200) {
                return Response.error(new Fail<>(RequestUtils.parseError(response)));
            }

            SubredditRules subredditRules = gson.fromJson(content, SubredditRules.class);

            return Response.success(subredditRules.getRules());
        }
    }
}
