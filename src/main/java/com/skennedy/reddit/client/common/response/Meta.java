package com.skennedy.reddit.client.common.response;

import com.skennedy.reddit.client.common.request.ListingRequest;

public class Meta<D> {

    ListingRequest<? extends ListingRequest<?, D>, D> request;

    public ListingRequest<? extends ListingRequest<?, D>, D> getRequest() {
        return request;
    }

    public void setRequest(ListingRequest<? extends ListingRequest<?, D>, D> request) {
        this.request = request;
    }
}
