package com.skennedy.reddit.client.account.model;

import com.google.gson.annotations.SerializedName;

public class SubredditKarma {

    @SerializedName("sr")
    private String subreddit;
    @SerializedName("comment_karma")
    private long commentKarma;
    @SerializedName("link_karma")
    private long linkKarma;

    public String getSubreddit() {
        return subreddit;
    }

    public long getCommentKarma() {
        return commentKarma;
    }

    public long getLinkKarma() {
        return linkKarma;
    }

}
