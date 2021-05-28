package com.skennedy.reddit.client.submit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

    private final String token;
    private final CloseableHttpClient httpClient;
    final Gson gson;

    public SubmissionClientImpl(String token, CloseableHttpClient httpClient) {
        this.token = token;
        this.httpClient = httpClient;
        gson = new GsonBuilder().create();
    }

    @Override
    public SubmitLinkRequest link(String link) throws MalformedURLException {
        URL url = new URL(link);
        return new SubmitLinkRequest(token, httpClient, url);
    }

    @Override
    public SubmitTextPostRequest textPost(String text) {
        return new SubmitTextPostRequest(token, httpClient, text);
    }

    @Override
    public SubmitImageRequest image(String imgUrl) throws MalformedURLException {
        URL url = new URL(imgUrl);
        return new SubmitImageRequest(token, httpClient, url);
    }

    @Override
    public SubmitCommentRequest comment(String commentText) {
        return new SubmitCommentRequest(token, httpClient, commentText);
    }

    @Override
    public VoteRequest vote(Vote vote) {
        return new VoteRequest(token, httpClient, vote);
    }
}
