package com.skennedy.reddit.client.common.request;

import com.skennedy.reddit.client.authorization.model.Access;
import com.skennedy.reddit.client.common.model.OAuthScope;
import com.skennedy.reddit.client.common.response.PagedResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a request that returns a reddit listing
 *
 * @param <T> The type of ListingRequest
 * @param <D> The data type of the Listing
 */
public abstract class ListingRequest<T extends ListingRequest<T, D>, D> extends Request {

    private static final int DEFAULT_LIMIT = 25;
    protected int limit;
    protected String beforeName;
    protected String afterName;

    public ListingRequest(Access access, CloseableHttpClient httpClient, OAuthScope... oAuthScopes) {
        super(access, httpClient, oAuthScopes);

        this.limit = DEFAULT_LIMIT;
    }

    /**
     * Limit the number of items returned. Max 100
     * @param limit The maximum number of items to return.
     *              Returns a default of 25 if not set.
     * @return instance of SubRequest
     */
    public T limit(int limit) {
        if (limit < 0 || limit > 100) {
            throw new IllegalArgumentException("Limit must be between 0 and 100 inclusive");
        }
        this.limit = limit;
        return (T) this;
    }

    /**
     * Used to get a page before a given item name
     * @param before the name of the item to get a page before
     * @return instance of ListingRequest
     */
    public T before(String before) {
        this.beforeName = before;

        return (T) this;
    }

    /**
     * Used to get a page after a given item name
     * @param after the name of the item to get a page after
     * @return instance of ListingRequest
     */
    public T after(String after) {
        this.afterName = after;

        return (T) this;
    }

    public List<NameValuePair> getListingParams() {

        if (StringUtils.isNoneBlank(afterName, beforeName)) {
            throw new IllegalArgumentException("Only one of before or after can be set, not both");
        }

        List<NameValuePair> params = new ArrayList<>();

        params.add(new BasicNameValuePair("limit", String.valueOf(limit)));
        if (StringUtils.isNotBlank(afterName)) {
            params.add(new BasicNameValuePair("after", afterName));
        }
        if (StringUtils.isNotBlank(beforeName)) {
            params.add(new BasicNameValuePair("before", beforeName));
        }

        return params;
    }

    public abstract PagedResponse<D> execute();

}
