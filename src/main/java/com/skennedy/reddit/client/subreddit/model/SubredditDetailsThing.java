package com.skennedy.reddit.client.subreddit.model;

import com.skennedy.reddit.client.common.model.Thing;

public class SubredditDetailsThing implements Thing<SubredditDetails> {

    private String kind;
    private SubredditDetails data;

    @Override
    public String getKind() {
        return kind;
    }

    @Override
    public SubredditDetails getData() {
        return data;
    }
}
