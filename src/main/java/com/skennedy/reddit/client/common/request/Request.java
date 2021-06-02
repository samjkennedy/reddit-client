package com.skennedy.reddit.client.common.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.skennedy.reddit.client.authorization.model.Access;
import com.skennedy.reddit.client.common.adapters.DateLongTypeAdapter;
import com.skennedy.reddit.client.common.adapters.LanguageCodeAdapter;
import com.skennedy.reddit.client.common.adapters.ScopeListTypeAdapter;
import com.skennedy.reddit.client.common.model.LanguageCode;
import com.skennedy.reddit.client.common.model.OAuthScope;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.http.impl.client.CloseableHttpClient;

import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Request {

    protected Access access;
    protected CloseableHttpClient httpClient;
    protected Gson gson;

    public Request(Access access, CloseableHttpClient httpClient, OAuthScope... oAuthScopes) {
        if (access == null) {
            throw new IllegalArgumentException("Access must not be null");
        }
        if (httpClient == null) {
            throw new IllegalArgumentException("HTTP Client must not be null");
        }
        if (!(Set.of(oAuthScopes).contains(OAuthScope.ANY) && access.getOAuthScopes().size() > 0) && !CollectionUtils.containsAll(access.getOAuthScopes(), Set.of(oAuthScopes))) {
            throw new IllegalArgumentException("Request requires ["
                    + Arrays.stream(oAuthScopes).map(OAuthScope::name).collect(Collectors.joining(", "))
                    + "] but provided access contains ["
                    + access.getOAuthScopes().stream().map(OAuthScope::name).collect(Collectors.joining(", "))
                    + "]");
        }

        this.access = access;
        this.httpClient = httpClient;

        this.gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateLongTypeAdapter())
                .registerTypeAdapter(LanguageCode.class, new LanguageCodeAdapter())
                .registerTypeAdapter(OAuthScope.class, new ScopeListTypeAdapter())
                .create();
    }
}
