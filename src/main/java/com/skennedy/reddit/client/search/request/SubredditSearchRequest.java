package com.skennedy.reddit.client.search.request;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.skennedy.reddit.client.common.request.Request;
import com.skennedy.reddit.client.common.response.Fail;
import com.skennedy.reddit.client.common.response.Response;
import com.skennedy.reddit.client.common.util.RequestUtils;
import com.skennedy.reddit.client.search.model.Subreddit;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SubredditSearchRequest extends Request {

    private String query;
    private boolean includeOver18;
    private boolean includeUnadvertisable;
    private boolean exact;

    public SubredditSearchRequest(CloseableHttpClient httpClient, String token, String query) {
        super(token, httpClient);

        this.query = query;
    }

    /**
     * Whether or not to include NSFW subreddits. By default this is false.
     *
     * @return the SubredditSearchRequest instance
     */
    public SubredditSearchRequest includeOver18() {
        this.includeOver18 = true;

        return this;
    }

    /**
     * If false, subreddits that have hide_ads set to True or are on the anti_ads_subreddits list will be filtered.
     * By default this is false.
     *
     * @return the SubredditSearchRequest instance
     */
    public SubredditSearchRequest includeUnadvertisable() {
        this.includeUnadvertisable = true;

        return this;
    }

    /**
     * Sets whether the query is exact. By default this is false.
     *
     * @return the SubredditSearchRequest instance
     */
    public SubredditSearchRequest exact() {
        this.exact = true;

        return this;
    }

    public Response<List<Subreddit>> execute() throws Exception {

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("query", query));
        params.add(new BasicNameValuePair("includeOver18", String.valueOf(includeOver18)));
        params.add(new BasicNameValuePair("includeUnadvertisable", String.valueOf(includeUnadvertisable)));
        params.add(new BasicNameValuePair("exact", String.valueOf(exact)));

        HttpPost post = new HttpPost("https://oauth.reddit.com/api/search_subreddits");
        post.setEntity(new UrlEncodedFormEntity(params));
        post.setHeader(HttpHeaders.USER_AGENT, RequestUtils.USER_AGENT);
        post.setHeader(HttpHeaders.AUTHORIZATION, "bearer " + token);

        try (CloseableHttpResponse response = httpClient.execute(post)) {
            String content = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
            if (response.getStatusLine().getStatusCode() != 200) {
                return Response.error(new Fail<>(RequestUtils.parseError(response)));
            }
            //Bit gross
            JsonElement subreddits = JsonParser.parseString(content).getAsJsonObject().get("subreddits");
            Type listType = new TypeToken<List<Subreddit>>() {}.getType();
            return Response.success(gson.fromJson(subreddits, listType));

        }
    }

}
