package com.skennedy.reddit.client.common.response;

import com.skennedy.reddit.client.common.request.ListingRequest;

/**
 * Represents a page of data with the ability to get the next or previous pages with the same parameters
 *
 * @param <D> The data type held in the page
 */
public class PagedResponse<D> extends Response<Page<D>> {

    Meta<D> meta;

    public static <T extends ListingRequest<T, D>, D> PagedResponse<D> success(Page<D> data, ListingRequest<T, D> request) {
        return new PagedResponse<>(data, request);
    }

    public static <T extends ListingRequest<T, D>, D> PagedResponse<D> error(Fail<Page<D>> fail, ListingRequest<T, D> request) {
        return new PagedResponse(fail, request);
    }

    private <T extends ListingRequest<T, D>> PagedResponse(Page<D> data, ListingRequest<T, D> request) {
        super(data);

        meta = new Meta<>();
        meta.setRequest(request);
    }

    private PagedResponse(Fail<Page<D>> error, ListingRequest<? extends ListingRequest<?, D>, D> request) {
        super(error);

        meta = new Meta<>();
        meta.setRequest(request);
    }

    /**
     * Gets the next page of data with the same parameters that got this page
     *
     * @return The next page of data
     */
    public PagedResponse<D> next() {
        ListingRequest<? extends ListingRequest<?, D>, D> request = meta.getRequest();
        return request.after(getData().getAfter())
                .execute();
    }

    /**
     * Gets the previous page of data with the same parameters that got this page
     *
     * @return The previous page of data
     */
    public PagedResponse<D> previous() {
        ListingRequest<? extends ListingRequest<?, D>, D> request = meta.getRequest();
        return request.before(getData().getBefore())
                .execute();
    }
}
