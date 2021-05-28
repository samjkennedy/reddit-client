package com.skennedy.reddit.client.submit;

import com.skennedy.reddit.client.authorization.model.Access;
import org.apache.http.impl.client.CloseableHttpClient;

public class SubmissionClientFactory {

    public static SubmissionClient getClient(Access access, CloseableHttpClient httpClient) {
        return new SubmissionClientImpl(access, httpClient);
    }

    private SubmissionClientFactory() {
    }
}
