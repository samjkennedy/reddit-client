package com.skennedy.reddit.client.submit.request;

import com.skennedy.reddit.client.authorization.model.Access;
import com.skennedy.reddit.client.common.model.Scope;
import com.skennedy.reddit.client.common.request.Request;
import com.skennedy.reddit.client.common.response.Fail;
import com.skennedy.reddit.client.common.response.Response;
import com.skennedy.reddit.client.common.util.RequestUtils;
import com.skennedy.reddit.client.search.model.Comment;
import com.skennedy.reddit.client.search.model.Submission;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class SubmitCommentRequest extends Request {

    String text;
    String parent;

    public SubmitCommentRequest(Access access, CloseableHttpClient httpClient, String text) throws IllegalAccessException {
        super(access, httpClient, Scope.ANY);
        if (StringUtils.isBlank(text)) {
            throw new IllegalArgumentException("Text must not be blank");
        }
        this.text = text;
    }

    public SubmitCommentRequest onPost(Submission submission) {
        parent = submission.getName();

        return this;
    }

    public SubmitCommentRequest inReplyTo(Comment comment) {
        parent = comment.getName();

        return this;
    }

    public Response<Void> submit() throws Exception {

        List<NameValuePair> params = new ArrayList<>();

        params.add(new BasicNameValuePair("parent", parent));
        params.add(new BasicNameValuePair("text", text));
        params.add(new BasicNameValuePair("api_type", "json"));

        HttpPost post = new HttpPost("https://oauth.reddit.com/api/comment");
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
