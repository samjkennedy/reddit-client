package com.skennedy.reddit.client.submit.request;

import com.skennedy.reddit.client.authorization.model.Access;
import com.skennedy.reddit.client.submit.model.SubmitKind;
import org.apache.http.NameValuePair;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SubmitImageRequest extends SubmitRequest<SubmitImageRequest> {

    private URL url;

    //TODO: This is identical to post link just with a different KIND, merge somehow
    public SubmitImageRequest(Access access, CloseableHttpClient httpClient, URL url) throws IllegalAccessException {
        super(access, httpClient);
        if (url == null) {
            throw new IllegalArgumentException("URL cannot be null");
        }
        kind = SubmitKind.IMAGE.name().toLowerCase();

        this.url = url;
    }

    @Override
    List<NameValuePair> buildParams() {
        List<NameValuePair> params = new ArrayList<>();

        params.add(new BasicNameValuePair("url", url.toString()));

        return params;
    }
}
