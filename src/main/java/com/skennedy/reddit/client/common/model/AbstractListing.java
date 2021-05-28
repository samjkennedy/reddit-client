package com.skennedy.reddit.client.common.model;

import java.util.List;

public class AbstractListing<T extends Thing<?>> implements Listing<T> {

    private String kind;
    private Data<T> data;

    public String getKind() {
        return kind;
    }

    @Override
    public String getAfter() {
        return data.getAfter();
    }

    @Override
    public String getBefore() {
        return data.getBefore();
    }

    @Override
    public List<T> getChildren() {
        return data.getChildren();
    }
}
