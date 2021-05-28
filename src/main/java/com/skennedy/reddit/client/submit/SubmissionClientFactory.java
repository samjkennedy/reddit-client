package com.skennedy.reddit.client.submit;

import org.apache.http.impl.client.CloseableHttpClient;

public class SubmissionClientFactory {

    public static SubmissionClient getClient(String token, CloseableHttpClient httpClient) {
        return new SubmissionClientImpl(token, httpClient);
    }

    private SubmissionClientFactory() {
    }
}
