package com.skennedy.reddit.client.listing.model;

public enum CommentSort {

    CONFIDENCE("confidence"),
    TOP("top"),
    NEW("new"),
    OLD("old"),
    RANDOM("random"),
    QA("qa"),
    LIVE("live");

    private final String sort;

    CommentSort(String sort) {
        this.sort = sort;
    }

    public String getSort() {
        return sort;
    }
}
