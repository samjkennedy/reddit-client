package com.skennedy.reddit.client.subreddit.request;

import com.skennedy.reddit.client.authorization.model.Access;
import com.skennedy.reddit.client.common.error.CommonErrorCode;
import com.skennedy.reddit.client.common.model.OAuthScope;
import com.skennedy.reddit.client.common.request.Request;
import com.skennedy.reddit.client.common.response.Fail;
import com.skennedy.reddit.client.common.response.Response;
import com.skennedy.reddit.client.common.util.RequestUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Represents the actions that can be taken on multiple subreddits, any actions that require only one subreddit must go in {@link SubredditRequest}
 */
public class SubredditsRequest extends Request {

    private List<String> subreddits;

    public SubredditsRequest(Access access, CloseableHttpClient httpClient, String subreddit, String... subreddits) {
        super(access, httpClient, OAuthScope.READ);
        if (ArrayUtils.isEmpty(subreddits)) {
            throw new IllegalArgumentException("At least one subreddit must be provided");
        }

        this.subreddits = Arrays.asList(subreddits);
        this.subreddits.add(subreddit);
    }

    public SubredditsRequest(Access access, CloseableHttpClient httpClient, Collection<String> subreddits) {
        super(access, httpClient, OAuthScope.READ);
        if (CollectionUtils.isEmpty(subreddits)) {
            throw new IllegalArgumentException("At least one subreddit must be provided");
        }

        this.subreddits = new ArrayList<>(subreddits);
    }

    /**
     * Subscribes to the subreddits
     * @return a Void response
     */
    public Response<Void> subscribe() {
        return subscriptionAction(SubscriptionType.SUBSCRIBE);
    }

    /**
     * Unsubscribes from the subreddits
     * @return a Void response
     */
    public Response<Void> unsubscribe() {
        return subscriptionAction(SubscriptionType.UNSUBSCRIBE);
    }

    private Response<Void> subscriptionAction(SubscriptionType type) {

        if (!access.getOAuthScopes().contains(OAuthScope.SUBSCRIBE)) {
            return Response.error(new Fail<>(CommonErrorCode.ACCESS_DENIED, "Subscribe scope is required for this endpoint"));
        }

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("sr_name", String.join(",", subreddits)));
        params.add(new BasicNameValuePair("action", type == SubscriptionType.SUBSCRIBE ? "sub" : "unsub"));

        HttpPost post = new HttpPost("https://oauth.reddit.com/api/subscribe");

        post.setHeader(HttpHeaders.USER_AGENT, RequestUtils.USER_AGENT);
        post.setHeader(HttpHeaders.AUTHORIZATION, "bearer " + access.getAccessToken());

        try {
            post.setEntity(new UrlEncodedFormEntity(params));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new Fail<>(CommonErrorCode.MALFORMED_URL));
        }

        try (CloseableHttpResponse response = httpClient.execute(post)) {
            String content = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
            if (response.getStatusLine().getStatusCode() != 200) {
                return Response.error(new Fail<>(RequestUtils.parseError(response)));
            }

            return Response.success(null);
        } catch (IOException e) {
            return Response.error(new Fail<>(CommonErrorCode.HTTP_ERROR));
        }
    }

    private enum SubscriptionType {
        SUBSCRIBE,
        UNSUBSCRIBE
    }
}
