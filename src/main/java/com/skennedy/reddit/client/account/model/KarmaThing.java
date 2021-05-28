package com.skennedy.reddit.client.account.model;

import com.skennedy.reddit.client.common.model.Thing;

public class KarmaThing implements Thing<SubredditKarma> {

    private String kind;
    private SubredditKarma data;

    @Override
    public String getKind() {
        return kind;
    }

    @Override
    public SubredditKarma getData() {
        return data;
    }
}
