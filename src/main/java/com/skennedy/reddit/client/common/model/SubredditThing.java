package com.skennedy.reddit.client.common.model;

import com.skennedy.reddit.client.listing.model.Subreddit;

public class SubredditThing implements Thing<Subreddit> {

    private String kind;
    private Subreddit data;

    @Override
    public String getKind() {
        return kind;
    }

    @Override
    public Subreddit getData() {
        return data;
    }
}
