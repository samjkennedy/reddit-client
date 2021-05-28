package com.skennedy.reddit.client.submit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.skennedy.reddit.client.authorization.model.Access;
import com.skennedy.reddit.client.submit.model.Vote;
import com.skennedy.reddit.client.submit.request.SubmitCommentRequest;
import com.skennedy.reddit.client.submit.request.SubmitImageRequest;
import com.skennedy.reddit.client.submit.request.SubmitLinkRequest;
import com.skennedy.reddit.client.submit.request.SubmitTextPostRequest;
import com.skennedy.reddit.client.submit.request.VoteRequest;
import org.apache.http.impl.client.CloseableHttpClient;

import java.net.MalformedURLException;
import java.net.URL;

public class SubmissionClientImpl implements SubmissionClient {

    private final Access access;
    private final CloseableHttpClient httpClient;
    final Gson gson;

    public SubmissionClientImpl(Access access, CloseableHttpClient httpClient) {
        this.access = access;
        this.httpClient = httpClient;
        gson = new GsonBuilder().create();
    }

    @Override
    public SubmitLinkRequest link(String link) throws MalformedURLException, IllegalAccessException {
        URL url = new URL(link);
        return new SubmitLinkRequest(access, httpClient, url);
    }

    @Override
    public SubmitTextPostRequest textPost(String text) throws IllegalAccessException {
        return new SubmitTextPostRequest(access, httpClient, text);
    }

    @Override
    public SubmitImageRequest image(String imgUrl) throws MalformedURLException, IllegalAccessException {
        URL url = new URL(imgUrl);
        return new SubmitImageRequest(access, httpClient, url);
    }

    @Override
    public SubmitCommentRequest comment(String commentText) throws IllegalAccessException {
        return new SubmitCommentRequest(access, httpClient, commentText);
    }

    @Override
    public VoteRequest vote(Vote vote) throws IllegalAccessException {
        return new VoteRequest(access, httpClient, vote);
    }
}
