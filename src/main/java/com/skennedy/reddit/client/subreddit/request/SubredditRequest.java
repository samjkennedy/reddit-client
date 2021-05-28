package com.skennedy.reddit.client.subreddit.request;

import com.skennedy.reddit.client.authorization.model.Access;
import com.skennedy.reddit.client.common.model.OAuthScope;
import com.skennedy.reddit.client.common.request.Request;
import com.skennedy.reddit.client.common.response.Fail;
import com.skennedy.reddit.client.common.response.Response;
import com.skennedy.reddit.client.common.util.RequestUtils;
import com.skennedy.reddit.client.subreddit.model.Sidebar;
import com.skennedy.reddit.client.subreddit.model.SubredditDetails;
import com.skennedy.reddit.client.subreddit.model.SubredditDetailsThing;
import com.skennedy.reddit.client.subreddit.model.SubredditRule;
import com.skennedy.reddit.client.subreddit.model.SubredditRules;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class SubredditRequest extends Request {
    
    private String subreddit;
    
    public SubredditRequest(Access access, CloseableHttpClient httpClient, String subreddit, OAuthScope... OAuthScopes) throws IllegalAccessException {
        super(access, httpClient, OAuthScopes);
        if (StringUtils.isBlank(subreddit)) {
            throw new IllegalArgumentException("Subreddit must not be blank");
        }

        this.subreddit = subreddit;
    }

    /**
     * Returns information about the subreddit such as description, subscriber count etc.
     * @return A response containing the details of the subreddit
     * @throws Exception TODO: Wrap exceptions into the response object
     */
    public Response<SubredditDetails> about() throws Exception {

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

    /**
     * Returns the rules of the subreddit
     * @return A response containing a list of the subreddit rules
     * @throws Exception TODO: Wrap exceptions into the response object
     */
    public Response<List<SubredditRule>> rules() throws Exception {

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
    
    public Response<Sidebar> sidebar() throws Exception {

        HttpGet get = new HttpGet("https://oauth.reddit.com/r/" + subreddit + "/about/sidebar");
        get.setHeader(HttpHeaders.USER_AGENT, RequestUtils.USER_AGENT);
        get.setHeader(HttpHeaders.AUTHORIZATION, "bearer " + access.getAccessToken());

        try (CloseableHttpResponse response = httpClient.execute(get)) {
            String content = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
            if (response.getStatusLine().getStatusCode() != 200) {
                return Response.error(new Fail<>(RequestUtils.parseError(response)));
            }
            return Response.success(gson.fromJson(content, Sidebar.class));
        }
    }
    
}
