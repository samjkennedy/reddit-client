package com.skennedy.reddit.client.listing.model;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.skennedy.reddit.client.common.adapters.BooleanAsNullDateAdapter;
import com.skennedy.reddit.client.common.adapters.EmptyStringAsNullDeserializer;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Comment {

    @SerializedName("total_awards_received")
    private int totalAwardsReceived;
    private int ups;
    @SerializedName("author_flair_type")
    private String authorFlairType;
    @SerializedName("link_id")
    private String linkId;
    @SerializedName("author_flair_template_id")
    private String authorFlairTemplateId;
    @JsonAdapter(EmptyStringAsNullDeserializer.class)
    private CommentListing replies;
    private boolean saved;
    private String id;
    private int gilded;
    private boolean archived;
    @SerializedName("no_follow")
    private boolean noFollow;
    private String author;
    @SerializedName("can_mod_post")
    private boolean canModPost;
    @SerializedName("send_replies")
    private boolean sendReplies;
    @SerializedName("parent_id")
    private String parentId;
    private int score;
    @SerializedName("author_fullname")
    private String authorFullname;
    @SerializedName("subreddit_id")
    private String subredditId;
    private String body;
    @JsonAdapter(BooleanAsNullDateAdapter.class)
    private Date edited;
    private int downs;
    @SerializedName("is_submitter")
    private boolean isSubmitter;
    private boolean collapsed;
    @SerializedName("body_html")
    private String bodyHtml;
    private Gildings gildings;
    private boolean stickied;
    @SerializedName("author_premium")
    private boolean authorPremium;
    @SerializedName("subreddit_type")
    private String subredditType;
    @SerializedName("can_gild")
    private boolean canGild;
    @SerializedName("score_hidden")
    private boolean scoreHidden;
    private String permalink;
    private boolean locked;
    private String name;
    private double created;
    private String subreddit;
    @SerializedName("author_flair_text")
    private String authorFlairText;
    @SerializedName("created_utc")
    private Date createdUtc;
    @SerializedName("subreddit_name_prefixed")
    private String subredditNamePrefixed;
    private int controversiality;
    private int depth;
    @SerializedName("author_flair_background_color")
    private String authorFlairBackgroundColor;

    public int getTotalAwardsReceived() {
        return totalAwardsReceived;
    }

    public int getUps() {
        return ups;
    }

    public String getAuthorFlairType() {
        return authorFlairType;
    }

    public String getLinkId() {
        return linkId;
    }

    public String getAuthorFlairTemplateId() {
        return authorFlairTemplateId;
    }

    public boolean isSaved() {
        return saved;
    }

    public String getId() {
        return id;
    }

    public int getGilded() {
        return gilded;
    }

    public boolean isArchived() {
        return archived;
    }

    public boolean isNoFollow() {
        return noFollow;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isCanModPost() {
        return canModPost;
    }

    public boolean isSendReplies() {
        return sendReplies;
    }

    public String getParentId() {
        return parentId;
    }

    public int getScore() {
        return score;
    }

    public String getAuthorFullname() {
        return authorFullname;
    }

    public String getSubredditId() {
        return subredditId;
    }

    public String getBody() {
        return body;
    }

    /**
     * Returns when the comment was edited. If the comment has never been edited returns null
     * @return when the comment was edited. If the comment has never been edited returns null
     */
    public Date getEdited() {
        return edited;
    }

    public int getDowns() {
        return downs;
    }

    public boolean isSubmitter() {
        return isSubmitter;
    }

    public boolean isCollapsed() {
        return collapsed;
    }

    public String getBodyHtml() {
        return bodyHtml;
    }

    public Gildings getGildings() {
        return gildings;
    }

    public boolean isStickied() {
        return stickied;
    }

    public boolean isAuthorPremium() {
        return authorPremium;
    }

    public String getSubredditType() {
        return subredditType;
    }

    public boolean isCanGild() {
        return canGild;
    }

    public boolean isScoreHidden() {
        return scoreHidden;
    }

    public String getPermalink() {
        return permalink;
    }

    public boolean isLocked() {
        return locked;
    }

    public String getName() {
        return name;
    }

    public double getCreated() {
        return created;
    }

    public String getSubreddit() {
        return subreddit;
    }

    public String getAuthorFlairText() {
        return authorFlairText;
    }

    public Date getCreatedUTC() {
        return createdUtc;
    }

    public String getSubredditNamePrefixed() {
        return subredditNamePrefixed;
    }

    public int getControversiality() {
        return controversiality;
    }

    public int getDepth() {
        return depth;
    }

    public String getAuthorFlairBackgroundColor() {
        return authorFlairBackgroundColor;
    }

    public List<Comment> getReplies() {
        return replies.getChildren().stream()
                .map(CommentThing::getData)
                .collect(Collectors.toList());
    }
}
