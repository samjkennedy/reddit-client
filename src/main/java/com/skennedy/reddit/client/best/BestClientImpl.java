package com.skennedy.reddit.client.best;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.skennedy.reddit.client.authorization.model.Access;
import com.skennedy.reddit.client.best.request.BestRequest;
import com.skennedy.reddit.client.common.adapters.DateLongTypeAdapter;
import org.apache.http.impl.client.CloseableHttpClient;

import java.util.Date;

public class BestClientImpl implements BestClient {

    private final Access access;
    private final CloseableHttpClient httpClient;
    final Gson gson;

    public BestClientImpl(Access access, CloseableHttpClient httpClient) {
        this.access = access;
        this.httpClient = httpClient;
        gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateLongTypeAdapter())
                .create();
    }

    @Override
    public BestRequest best() throws IllegalAccessException {
        return new BestRequest(access, httpClient);
    }
}
