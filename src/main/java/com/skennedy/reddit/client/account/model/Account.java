package com.skennedy.reddit.client.account.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Account {

    @SerializedName("default_set")
    private boolean defaultSet;
    @SerializedName("user_is_contributor")
    private boolean contributor;
    @SerializedName("banner_img")
    private String bannerImage;
    @SerializedName("restrict_posting")
    private boolean restrictPosting;
    @SerializedName("user_is_banned")
    private boolean banned;
    @SerializedName("free_form_reports")
    private boolean freeFormReports;
    @SerializedName("community_icon")
    private int[] communityIconSize;
    @SerializedName("show_media")
    private boolean showMedia;
    @SerializedName("icon_color")
    private String iconColor;
    @SerializedName("user_is_muted")
    private boolean muted;
    @SerializedName("display_name")
    private String displayName;
    @SerializedName("header_img")
    private int[] headerImageSize;
    private String title;
    @SerializedName("previous_names")
    private List<String> previousNames;
    @SerializedName("over_18")
    private boolean over18;
    @SerializedName("icon_size")
    private int[] iconSize;
    @SerializedName("primary_color")
    private String primaryColor;
    @SerializedName("icon_img")
    private String iconImage;
    private String description;
    @SerializedName("submit_link_label")
    private String submitLinkLabel;
    @SerializedName("header_size")
    private int[] headerSize;
    @SerializedName("restrict_commenting")
    private boolean restrictCommenting;
    private int subscribers;
    @SerializedName("submit_text_label")
    private String submitTextLabel;
    @SerializedName("is_default_icon")
    private boolean defaultIcon;
    @SerializedName("link_flair_position")
    private String linkFlairPosition;
    @SerializedName("display_name_prefixed")
    private String displayNamePrefixed;
    @SerializedName("key_color")
    private String keyColor;
    private String name;
    @SerializedName("is_default_banner")
    private boolean defaultBanner;
    private String url;
    @SerializedName("quarantine")
    private boolean quarantined;
    @SerializedName("banner_size")
    private int[] bannerSize;
    @SerializedName("user_is_moderator")
    private boolean moderator;
    @SerializedName("private_description")
    private String privateDescription;
    @SerializedName("link_flair_enabled")
    private boolean linkFlairEnabled;
    @SerializedName("disable_contributor_requests")
    private boolean disabledContributorRequests;
    @SerializedName("subreddit_type")
    private String subredditType;
    @SerializedName("user_is_subscriber")
    private boolean subscriber;

    public boolean isDefaultSet() {
        return defaultSet;
    }

    public boolean isContributor() {
        return contributor;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public boolean isPostingRestricted() {
        return restrictPosting;
    }

    public boolean isBanned() {
        return banned;
    }

    public boolean hasFreeFormReports() {
        return freeFormReports;
    }

    public int[] getCommunityIconSize() {
        return communityIconSize;
    }

    public boolean hasShowMedia() {
        return showMedia;
    }

    public String getIconColor() {
        return iconColor;
    }

    public boolean isMuted() {
        return muted;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int[] getHeaderImageSize() {
        return headerImageSize;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getPreviousNames() {
        return previousNames;
    }

    public boolean isOver18() {
        return over18;
    }

    public int[] getIconSize() {
        return iconSize;
    }

    public String getPrimaryColor() {
        return primaryColor;
    }

    public String getIconImage() {
        return iconImage;
    }

    public String getDescription() {
        return description;
    }

    public String getSubmitLinkLabel() {
        return submitLinkLabel;
    }

    public int[] getHeaderSize() {
        return headerSize;
    }

    public boolean commentingRestricted() {
        return restrictCommenting;
    }

    public int getSubscribers() {
        return subscribers;
    }

    public String getSubmitTextLabel() {
        return submitTextLabel;
    }

    public boolean hasDefaultIcon() {
        return defaultIcon;
    }

    public String getLinkFlairPosition() {
        return linkFlairPosition;
    }

    public String getDisplayNamePrefixed() {
        return displayNamePrefixed;
    }

    public String getKeyColor() {
        return keyColor;
    }

    public String getName() {
        return name;
    }

    public boolean hasDefaultBanner() {
        return defaultBanner;
    }

    public String getUrl() {
        return url;
    }

    public boolean isQuarantined() {
        return quarantined;
    }

    public int[] getBannerSize() {
        return bannerSize;
    }

    public boolean isModerator() {
        return moderator;
    }

    public String getPrivateDescription() {
        return privateDescription;
    }

    public boolean isLinkFlairEnabled() {
        return linkFlairEnabled;
    }

    public boolean contributorRequestsDisabled() {
        return disabledContributorRequests;
    }

    public String getSubredditType() {
        return subredditType;
    }

    public boolean isSubscriber() {
        return subscriber;
    }
}
