package com.skennedy.reddit.client.submit.request;

import com.google.gson.GsonBuilder;
import com.skennedy.reddit.client.submit.model.SubmitKind;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class SubmitTextPostRequest extends SubmitRequest<SubmitTextPostRequest> {

    private boolean isRichText;

    String text;
    String richText;

    public SubmitTextPostRequest(String token, CloseableHttpClient httpClient) {
        this(token, httpClient, null);
    }

    public SubmitTextPostRequest(String token, CloseableHttpClient httpClient, String text) {
        super(token, httpClient);

        if (StringUtils.length(text) > 2000) {
            throw new IllegalArgumentException("Text must be less than or equal to 2000 characters");
        }

        kind = SubmitKind.SELF.name().toLowerCase();

        this.text = text;
    }

    /**
     * Marks this text post as using rich text.
     * @return the SubmitRequest instance
     */
    public SubmitTextPostRequest asRichText() {
        this.richText = text;
        isRichText = true;

        return this;
    }

    @Override
    List<NameValuePair> buildParams() {
        List<NameValuePair> params = new ArrayList<>();

        if (isRichText) {
            params.add(new BasicNameValuePair("richtext_json", richText));
        } else {
            params.add(new BasicNameValuePair("text", text));
        }

        return params;
    }
}
