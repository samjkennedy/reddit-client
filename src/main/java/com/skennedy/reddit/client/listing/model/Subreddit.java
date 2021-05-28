package com.skennedy.reddit.client.listing.model;

import com.google.gson.annotations.SerializedName;
import com.skennedy.reddit.client.common.model.AllowlistStatus;
import com.skennedy.reddit.client.common.model.LanguageCode;

import java.util.Date;

public class Subreddit {

    @SerializedName("user_flair_background_color")
    private String userFlairBackgroundColor;
    @SerializedName("submit_text_html")
    private String submitTextHtml;
    @SerializedName("restrict_posting")
    private boolean restrictPosting;
    @SerializedName("user_is_banned")
    private boolean userBanned;
    @SerializedName("free_form_reports")
    private boolean freeFormReports;
    @SerializedName("wiki_enabled")
    private boolean wikiEnabled;
    @SerializedName("user_is_muted")
    private boolean userMuted;
    @SerializedName("user_can_flair_in_sr")
    private boolean userCanFlairInSubreddit;
    @SerializedName("display_name")
    private String displayName;
    @SerializedName("header_img")
    private String headerImage;
    private String title;
    @SerializedName("allow_galleries")
    private boolean galleriesAllowed;
    @SerializedName("icon_size")
    private int[] iconSizes;
    @SerializedName("primary_color")
    private String primaryColor;
    @SerializedName("active_user_count")
    private int activeUserCount;
    @SerializedName("icon_img")
    private String iconImage;
    @SerializedName("display_name_prefixed")
    private String displayNamePrefixed;
    @SerializedName("accounts_active")
    private int accountsActive;
    @SerializedName("private_traffic")
    private boolean privateTraffic;
    private int subscribers;
    //private List<Object> user_flair_richtext; https://snuze.shaunc.com/docs/api-reference/subreddit/#field-user_flair_richtext
    @SerializedName("videostream_links_count")
    private int videostreamLinksCount;
    private String name;
    private boolean quarantine;
    @SerializedName("hide_ads")
    private boolean hideAds;
    @SerializedName("prediction_leaderboard_entry_type")
    private String predictionLeaderboardEntryType;
    @SerializedName("emojis_enabled")
    private boolean emojisEnabled;
    @SerializedName("advertiser_category")
    private String advetiserCategory;
    @SerializedName("private_description")
    private String privateDescription;
    @SerializedName("comment_score_hide_mins")
    private int commentScoreHideMins;
    @SerializedName("allow_predictions")
    private boolean allowPredictions;
    @SerializedName("user_has_favorited")
    private boolean userHasFavorited;
    @SerializedName("user_flair_template_id")
    private String userFlairTemplateId;
    @SerializedName("community_icon")
    private String communityIcon;
    @SerializedName("banner_background_image")
    private String bannerBackgroundImage;
    @SerializedName("original_content_tag_enabled")
    private boolean originalContentTagEnabled;
    @SerializedName("community_reviewed")
    private boolean communityReviewed;
    @SerializedName("submit_text")
    private String submitText;
    @SerializedName("description_html")
    private String descriptionHtml;
    @SerializedName("spoilers_enabled")
    private boolean spoilersEnabled;
    @SerializedName("header_title")
    private String headerTitle;
    @SerializedName("header_size")
    private int[] headerSize;
    @SerializedName("user_flair_position")
    private String userFlairPosition;
    @SerializedName("all_original_content")
    private boolean allOriginalContent;
    @SerializedName("has_menu_widget")
    private boolean hasMenuWidget;
    @SerializedName("is_enrolled_in_new_modmail")
    private boolean isEnrolledInNewModmail;
    @SerializedName("key_color")
    private String keyColor;
    @SerializedName("can_assign_user_flair")
    private boolean canAssignUserFlair;
    private Date created;
    @SerializedName("wls")
    private int wls;
    @SerializedName("show_media_preview")
    private boolean showMediaPreview;
    @SerializedName("submission_type")
    private String submissionType;
    @SerializedName("user_is_subscriber")
    private boolean userSubscribed;
    @SerializedName("disable_contributor_requests")
    private boolean disableContributorRequests;
    @SerializedName("allow_videogifs")
    private boolean allowVideogifs;
    @SerializedName("user_flair_type")
    private String userFlairType;
    @SerializedName("allow_polls")
    private boolean allowPolls;
    @SerializedName("collapse_deleted_comments")
    private boolean collapseDeletedComments;
    @SerializedName("emojis_custom_size")
    private int[] emojisCustomSize;
    @SerializedName("private_description_html")
    private String privateDescriptionHtml;
    @SerializedName("allow_videos")
    private boolean allowVideos;
    @SerializedName("is_crosspostable_subreddit")
    private boolean isCrosspostableSubreddit;
    @SerializedName("notification_level")
    private String notificationLevel;
    @SerializedName("can_assign_link_flair")
    private boolean canAssignLinkFlair;
    @SerializedName("accounts_active_is_fuzzed")
    private boolean accountsActiveIsFuzzed;
    @SerializedName("submit_text_label")
    private String submitTextLabel;
    @SerializedName("link_flair_position")
    private String linkFlairPosition;
    @SerializedName("user_sr_flair_enabled")
    private boolean userSubredditFlairEnabled;
    @SerializedName("user_flair_enabled_in_sr")
    private boolean userFlairEnabledInSubreddit;
    @SerializedName("allow_discovery")
    private boolean allowDiscovery;
    @SerializedName("user_sr_theme_enabled")
    private boolean userSubredditThemeEnabled;
    @SerializedName("link_flair_enabled")
    private boolean linkFlairEnabled;
    @SerializedName("subreddit_type")
    private String subredditType;
    @SerializedName("suggested_comment_sort")
    private CommentSort suggestedCommentSort;
    @SerializedName("banner_img")
    private String bannerImage;
    @SerializedName("user_flair_text")
    private String userFlairText;
    @SerializedName("banner_background_color")
    private String bannerBackgroundColor;
    @SerializedName("show_media")
    private boolean showMedia;
    private String id;
    @SerializedName("user_is_moderator")
    private boolean userIsModerator;
    private boolean over18;
    private String description;
    @SerializedName("submit_link_label")
    private String submitLinkLabel;
    @SerializedName("user_flair_text_color")
    private String userFlairTextColor;
    @SerializedName("restrict_commenting")
    private boolean restrictCommenting;
    @SerializedName("user_flair_css_class")
    private String userFlairCssClass;
    @SerializedName("allow_images")
    private boolean allowImages;
    @SerializedName("lang")
    private LanguageCode language;
    @SerializedName("whitelist_status")
    private AllowlistStatus allowlistStatus;
    private String url;
    @SerializedName("created_utc")
    private Date createdUTC;
    @SerializedName("bannerSize")
    private int[] bannerSize;
    @SerializedName("mobile_banner_image")
    private String mobileBannerImage;
    @SerializedName("user_is_contributor")
    private boolean userIsContributor;
    @SerializedName("allow_predictions_tournament")
    private boolean allowPredictionsTournament;

    public String getUserFlairBackgroundColor() {
        return userFlairBackgroundColor;
    }

    public String getSubmitTextHtml() {
        return submitTextHtml;
    }

    public boolean isPostingRestricted() {
        return restrictPosting;
    }

    public boolean isUserBanned() {
        return userBanned;
    }

    public boolean isFreeFormReports() {
        return freeFormReports;
    }

    public boolean isWikiEnabled() {
        return wikiEnabled;
    }

    public boolean isUserMuted() {
        return userMuted;
    }

    public boolean canUserFlairInSubreddit() {
        return userCanFlairInSubreddit;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getHeaderImage() {
        return headerImage;
    }

    public String getTitle() {
        return title;
    }

    public boolean areGalleriesAllowed() {
        return galleriesAllowed;
    }

    public int[] getIconSizes() {
        return iconSizes;
    }

    public String getPrimaryColor() {
        return primaryColor;
    }

    public int getActiveUserCount() {
        return activeUserCount;
    }

    public String getIconImage() {
        return iconImage;
    }

    public String getDisplayNamePrefixed() {
        return displayNamePrefixed;
    }

    public int getAccountsActive() {
        return accountsActive;
    }

    public boolean isPrivateTraffic() {
        return privateTraffic;
    }

    public int getSubscribers() {
        return subscribers;
    }

    public int getVideostreamLinksCount() {
        return videostreamLinksCount;
    }

    public String getName() {
        return name;
    }

    public boolean isQuarantined() {
        return quarantine;
    }

    public boolean adsHidden() {
        return hideAds;
    }

    public String getPredictionLeaderboardEntryType() {
        return predictionLeaderboardEntryType;
    }

    public boolean areEmojisEnabled() {
        return emojisEnabled;
    }

    public String getAdvetiserCategory() {
        return advetiserCategory;
    }

    public String getPrivateDescription() {
        return privateDescription;
    }

    public int getCommentScoreHideMins() {
        return commentScoreHideMins;
    }

    public boolean predictionsAllowed() {
        return allowPredictions;
    }

    public boolean userHasFavorited() {
        return userHasFavorited;
    }

    public String getUserFlairTemplateId() {
        return userFlairTemplateId;
    }

    public String getCommunityIcon() {
        return communityIcon;
    }

    public String getBannerBackgroundImage() {
        return bannerBackgroundImage;
    }

    public boolean isOriginalContentTagEnabled() {
        return originalContentTagEnabled;
    }

    public boolean isCommunityReviewed() {
        return communityReviewed;
    }

    public String getSubmitText() {
        return submitText;
    }

    public String getDescriptionHtml() {
        return descriptionHtml;
    }

    public boolean areSpoilersEnabled() {
        return spoilersEnabled;
    }

    public String getHeaderTitle() {
        return headerTitle;
    }

    public int[] getHeaderSize() {
        return headerSize;
    }

    public String getUserFlairPosition() {
        return userFlairPosition;
    }

    public boolean isAllOriginalContent() {
        return allOriginalContent;
    }

    public boolean hasMenuWidget() {
        return hasMenuWidget;
    }

    public boolean isEnrolledInNewModmail() {
        return isEnrolledInNewModmail;
    }

    public String getKeyColor() {
        return keyColor;
    }

    public boolean canAssignUserFlair() {
        return canAssignUserFlair;
    }

    public Date getCreated() {
        return created;
    }

    public int getWls() {
        return wls;
    }

    public boolean doesShowMediaPreview() {
        return showMediaPreview;
    }

    public String getSubmissionType() {
        return submissionType;
    }

    public boolean isUserSubscribed() {
        return userSubscribed;
    }

    public boolean contributorRequestsDisabled() {
        return disableContributorRequests;
    }

    public boolean videogifsAllowed() {
        return allowVideogifs;
    }

    public String getUserFlairType() {
        return userFlairType;
    }

    public boolean pollsAllowed() {
        return allowPolls;
    }

    public boolean isCollapseDeletedComments() {
        return collapseDeletedComments;
    }

    public int[] getEmojisCustomSize() {
        return emojisCustomSize;
    }

    public String getPrivateDescriptionHtml() {
        return privateDescriptionHtml;
    }

    public boolean videosAllowed() {
        return allowVideos;
    }

    public boolean isCrosspostableSubreddit() {
        return isCrosspostableSubreddit;
    }

    public String getNotificationLevel() {
        return notificationLevel;
    }

    public boolean canAssignLinkFlair() {
        return canAssignLinkFlair;
    }

    public boolean isAccountsActiveFuzzed() {
        return accountsActiveIsFuzzed;
    }

    public String getSubmitTextLabel() {
        return submitTextLabel;
    }

    public String getLinkFlairPosition() {
        return linkFlairPosition;
    }

    public boolean isUserSubredditFlairEnabled() {
        return userSubredditFlairEnabled;
    }

    public boolean isUserFlairEnabledInSubreddit() {
        return userFlairEnabledInSubreddit;
    }

    public boolean discoveryAllowed() {
        return allowDiscovery;
    }

    public boolean userSubredditThemeEnabled() {
        return userSubredditThemeEnabled;
    }

    public boolean linkFlairEnabled() {
        return linkFlairEnabled;
    }

    public String getSubredditType() {
        return subredditType;
    }

    public CommentSort getSuggestedCommentSort() {
        return suggestedCommentSort;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public String getUserFlairText() {
        return userFlairText;
    }

    public String getBannerBackgroundColor() {
        return bannerBackgroundColor;
    }

    public boolean isShowMedia() {
        return showMedia;
    }

    public String getId() {
        return id;
    }

    public boolean userIsModerator() {
        return userIsModerator;
    }

    public boolean isOver18() {
        return over18;
    }

    public String getDescription() {
        return description;
    }

    public String getSubmitLinkLabel() {
        return submitLinkLabel;
    }

    public String getUserFlairTextColor() {
        return userFlairTextColor;
    }

    public boolean commentingRestricted() {
        return restrictCommenting;
    }

    public String getUserFlairCssClass() {
        return userFlairCssClass;
    }

    public boolean imagesAllowed() {
        return allowImages;
    }

    public LanguageCode getLanguage() {
        return language;
    }

    public AllowlistStatus getAllowlistStatus() {
        return allowlistStatus;
    }

    public String getUrl() {
        return url;
    }

    public Date getCreatedUTC() {
        return createdUTC;
    }

    public int[] getBannerSize() {
        return bannerSize;
    }

    public String getMobileBannerImage() {
        return mobileBannerImage;
    }

    public boolean userIsContributor() {
        return userIsContributor;
    }

    public boolean predictionsTournamentAllowed() {
        return allowPredictionsTournament;
    }
}
