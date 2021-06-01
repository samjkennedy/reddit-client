package com.skennedy.reddit.client.users.request;

import com.skennedy.reddit.client.authorization.model.Access;
import com.skennedy.reddit.client.common.model.OAuthScope;
import com.skennedy.reddit.client.common.request.ListingRequest;
import com.skennedy.reddit.client.listing.model.SortTime;
import com.skennedy.reddit.client.users.model.HistoryType;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.impl.client.CloseableHttpClient;

public abstract class UserHistoryRequest<T extends UserHistoryRequest<T, D>, D> extends ListingRequest<UserHistoryRequest<T, D>, D> {

    protected final String username;
    protected final HistoryType historyType;
    protected Where where;
    protected HistorySort sort;
    protected SortTime time;

    public UserHistoryRequest(Access access, CloseableHttpClient httpClient, String username, HistoryType historyType) throws IllegalAccessException {
        super(access, httpClient, OAuthScope.HISTORY);

        if (StringUtils.isBlank(username)) {
            throw new IllegalArgumentException("Username must not be blank");
        }

        this.username = username;
        this.sort = HistorySort.NEW;
        this.time = SortTime.ALL;
        this.historyType = historyType;
    }

    /**
     * Sets the sort of the user's post history. If not set defaults to NEW ALL
     *
     * @param sort The sort field
     * @param time The time to search over
     * @return a UserHistoryRequest instance
     */
    public T by(HistorySort sort, SortTime time) {
        this.sort = sort;
        this.time = time;

        return (T) this;
    }

    protected String getPath(Where where) {
        switch (where) {
            case OVERVIEW:
                return "/overview";
            case SUBMITTED:
                return "/submitted";
            case COMMENTS:
                return "/comments";
            case UPVOTED:
                return "/upvoted";
            case DOWNVOTED:
                return "/downvoted";
            case HIDDEN:
                return "/hidden";
            case SAVED:
                return "/saved";
            case GILDED:
                return "/gilded";
            default:
                throw new IllegalStateException("Unexpected value: " + where);
        }
    }

    protected enum Where {
        OVERVIEW,
        SUBMITTED,
        COMMENTS,
        UPVOTED,
        DOWNVOTED,
        HIDDEN,
        SAVED,
        GILDED
    }
}
