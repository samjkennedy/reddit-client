package com.skennedy.reddit.client.subreddit.model;

import com.google.gson.annotations.SerializedName;

public class SubredditRule {

    private String kind;
    private String description;
    @SerializedName("short_name")
    private String shortName;
    @SerializedName("violation_reason")
    private String violationReason;
    @SerializedName("created_utc")
    private float createdUtc; //TODO: parse as date
    private int priority;
    @SerializedName("description_html")
    private String descriptionHtml;

    public String getKind() {
        return kind;
    }

    public String getDescription() {
        return description;
    }

    public String getShortName() {
        return shortName;
    }

    public String getViolationReason() {
        return violationReason;
    }

    public float getCreatedUtc() {
        return createdUtc;
    }

    public int getPriority() {
        return priority;
    }

    public String getDescriptionHtml() {
        return descriptionHtml;
    }
}
