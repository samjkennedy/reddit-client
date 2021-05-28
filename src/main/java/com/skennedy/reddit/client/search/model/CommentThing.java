package com.skennedy.reddit.client.search.model;

import com.skennedy.reddit.client.common.model.Thing;

public class CommentThing implements Thing<Comment> {

    private String kind;
    private Comment data;

    @Override
    public String getKind() {
        return kind;
    }

    @Override
    public Comment getData() {
        return data;
    }
}
