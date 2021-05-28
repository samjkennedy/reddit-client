package com.skennedy.reddit.client.common.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.skennedy.reddit.client.common.adapters.DateLongTypeAdapter;
import com.skennedy.reddit.client.common.adapters.LanguageCodeAdapter;
import com.skennedy.reddit.client.common.model.LanguageCode;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.impl.client.CloseableHttpClient;

import java.util.Date;

public abstract class Request {

    protected String token;
    protected CloseableHttpClient httpClient;
    protected Gson gson;

    public Request(String token, CloseableHttpClient httpClient) {
        if (StringUtils.isBlank(token)) {
            throw new IllegalArgumentException("Token must not be blank");
        }
        if (httpClient == null) {
            throw new IllegalArgumentException("HTTP Client must not be null");
        }

        this.token = token;
        this.httpClient = httpClient;

        this.gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateLongTypeAdapter())
                .registerTypeAdapter(LanguageCode.class, new LanguageCodeAdapter())
                .create();
    }
}
