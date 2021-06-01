package com.skennedy.reddit.client.submit.request;

import com.skennedy.reddit.client.authorization.model.Access;
import com.skennedy.reddit.client.common.model.OAuthScope;
import com.skennedy.reddit.client.common.request.Request;
import com.skennedy.reddit.client.common.response.Fail;
import com.skennedy.reddit.client.common.response.Response;
import com.skennedy.reddit.client.common.util.RequestUtils;
import com.skennedy.reddit.client.listing.model.Comment;
import com.skennedy.reddit.client.listing.model.Submission;
import com.skennedy.reddit.client.submit.model.Vote;
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

public class VoteRequest extends Request {

    private Vote vote;
    private String id;

    public VoteRequest(Access access, CloseableHttpClient httpClient, Vote vote) throws IllegalAccessException {
        super(access, httpClient, OAuthScope.VOTE);

        if (vote == null) {
            throw new IllegalArgumentException("Vote must not be null");
        }
        this.vote = vote;

    }

    public VoteRequest onSubmission(Submission submission) {
        if (submission == null) {
            throw new IllegalArgumentException("Submission cannot be null");
        }
        this.id = submission.getName();

        return this;
    }

    public VoteRequest onComment(Comment comment) {
        if (comment == null) {
            throw new IllegalArgumentException("Comment cannot be null");
        }
        this.id = comment.getName();

        return this;
    }

    public Response<Void> submit() throws Exception {

        if (StringUtils.isBlank(id)) {
            throw new IllegalArgumentException("Nothing to vote on has been set");
        }

        List<NameValuePair> params = new ArrayList<>();

        params.add(new BasicNameValuePair("id", id));
        params.add(new BasicNameValuePair("dir", String.valueOf(getDirection(vote))));
        params.add(new BasicNameValuePair("rank", "2")); //Docs just say "an integer greater than 1" - what does this do?

        HttpPost post = new HttpPost("https://oauth.reddit.com/api/vote");
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

    private int getDirection(Vote vote) {
        switch (vote) {
            case DOWNVOTE:
                return -1;
            case CLEAR_VOTE:
                return 0;
            case UPVOTE:
                return 1;
            default:
                throw new IllegalStateException("Unexpected vote value: " + vote);
        }
    }
}
