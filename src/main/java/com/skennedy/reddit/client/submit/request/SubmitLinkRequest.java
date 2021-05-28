package com.skennedy.reddit.client.submit.request;

import com.google.gson.GsonBuilder;
import com.skennedy.reddit.client.submit.model.SubmitKind;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SubmitLinkRequest extends SubmitRequest<SubmitLinkRequest> {

    private URL url;

    public SubmitLinkRequest(String token, CloseableHttpClient httpClient, URL url) {
        super(token, httpClient);
        if (url == null) {
            throw new IllegalArgumentException("URL cannot be null");
        }
        kind = SubmitKind.LINK.name().toLowerCase();

        this.url = url;
    }

    @Override
    List<NameValuePair> buildParams() {
        List<NameValuePair> params = new ArrayList<>();

        params.add(new BasicNameValuePair("url", url.toString()));

        return params;
    }
}
