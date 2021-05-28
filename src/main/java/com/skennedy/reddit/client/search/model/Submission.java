package com.skennedy.reddit.client.search.model;

import com.google.gson.annotations.SerializedName;

public class Submission {

    private String subreddit;
    private String id;
    private String name;
    @SerializedName("selftext")
    String selfText;
    String title;
    @SerializedName("upvote_ratio")
    double upvoteRatio;
    long score;
    String thumbnail;
    @SerializedName("is_self")
    boolean isSelf;
    String author;
    String url;
    @SerializedName("over_18")
    boolean over18;
    boolean spoiler;

    public String getSubreddit() {
        return subreddit;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSelfText() {
        return selfText;
    }

    public String getTitle() {
        return title;
    }

    public double getUpvoteRatio() {
        return upvoteRatio;
    }

    public long getScore() {
        return score;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public boolean isSelf() {
        return isSelf;
    }

    public String getAuthor() {
        return author;
    }

    public String getUrl() {
        return url;
    }

    public boolean isOver18() {
        return over18;
    }

    public boolean isSpoiler() {
        return spoiler;
    }
}
