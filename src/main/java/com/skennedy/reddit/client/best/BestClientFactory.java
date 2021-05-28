package com.skennedy.reddit.client.best;

import com.skennedy.reddit.client.authorization.model.Access;
import org.apache.http.impl.client.CloseableHttpClient;

public class BestClientFactory {

    public static BestClient getClient(Access access, CloseableHttpClient httpClient) {
        return new BestClientImpl(access, httpClient);
    }

    private BestClientFactory() {

    }
}
