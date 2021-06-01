package com.skennedy.reddit.client.submit.request;

import com.skennedy.reddit.client.authorization.model.Access;
import com.skennedy.reddit.client.common.model.OAuthScope;
import com.skennedy.reddit.client.common.request.Request;
import com.skennedy.reddit.client.common.response.Fail;
import com.skennedy.reddit.client.common.response.Response;
import com.skennedy.reddit.client.common.util.RequestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.List;

public abstract class SubmitRequest<T extends SubmitRequest<T>> extends Request {

    String kind;
    String sr;
    String title;
    boolean nsfw;
    boolean spoiler;

    public SubmitRequest(Access access, CloseableHttpClient httpClient) throws IllegalAccessException {
        super(access, httpClient, OAuthScope.SUBMIT);
    }

    abstract List<NameValuePair> buildParams();

    public T toSubreddit(String subreddit) {
        if (StringUtils.isBlank(subreddit)) {
            throw new IllegalArgumentException("Subreddit must not be blank");
        }
        this.sr = subreddit;

        return (T) this;
    }

    /**
     * Sets the title of the submission
     * @param title The title of the submission
     * @return the SubmitRequest instance
     * @throws IllegalArgumentException if the title is blank
     */
    public T withTitle(String title) {
        if (StringUtils.isBlank(title)) {
            throw new IllegalArgumentException("Title must not be blank");
        }
        if (title.length() > 300) {
            throw new IllegalArgumentException("Titles must be 300 characters or less");
        }
        this.title = title;

        return (T) this;
    }

    /**
     * Marks the submission as nsfw
     * @return the SubmitRequest instance
     */
    public T nsfw() {
        this.nsfw = true;

        return (T) this;
    }

    /**
     * Marks the submission as a spoiler
     * @return the SubmitRequest instance
     */
    public T spoiler() {
        this.spoiler = true;

        return (T) this;
    }

    public Response<Void> submit() throws Exception {

        List<NameValuePair> params = buildParams();

        params.add(new BasicNameValuePair("kind", kind));
        params.add(new BasicNameValuePair("sr", sr));
        params.add(new BasicNameValuePair("title", title));
        params.add(new BasicNameValuePair("nsfw", String.valueOf(nsfw)));
        params.add(new BasicNameValuePair("spoiler", String.valueOf(spoiler)));

        HttpPost post = new HttpPost("https://oauth.reddit.com/api/submit");
        post.setEntity(new UrlEncodedFormEntity(params));
        post.setHeader(HttpHeaders.USER_AGENT, RequestUtils.USER_AGENT);
        post.setHeader(HttpHeaders.AUTHORIZATION, "bearer " + access.getAccessToken());

        try (CloseableHttpResponse response = httpClient.execute(post)) {
            if (response.getStatusLine().getStatusCode() != 200) {
                return Response.error(new Fail<>(RequestUtils.parseError(response)));
            }

            return Response.success(null);
        }
    }

}
