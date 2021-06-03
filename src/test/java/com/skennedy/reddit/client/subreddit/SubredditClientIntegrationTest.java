package com.skennedy.reddit.client.subreddit;

import com.skennedy.reddit.client.Reddit;
import com.skennedy.reddit.client.common.AuthedIntegrationTest;
import com.skennedy.reddit.client.common.response.Page;
import com.skennedy.reddit.client.common.response.PagedResponse;
import com.skennedy.reddit.client.common.response.Response;
import com.skennedy.reddit.client.listing.model.Sticky;
import com.skennedy.reddit.client.listing.model.Submission;
import com.skennedy.reddit.client.listing.model.Subreddit;
import com.skennedy.reddit.client.subreddit.model.Sidebar;
import com.skennedy.reddit.client.subreddit.model.SubredditDetails;
import com.skennedy.reddit.client.subreddit.model.SubredditRule;
import org.apache.commons.collections4.IteratorUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SubredditClientIntegrationTest extends AuthedIntegrationTest {

    @Test
    void about_canGetSubredditDetails_givenSubreddit() throws Exception {
        try (Reddit reddit = getClient()) {

            Response<SubredditDetails> subredditDetailsResponse = reddit.subreddits()
                    .r("skyrim")
                    .about();

            assertTrue(subredditDetailsResponse.hasData());
            assertFalse(subredditDetailsResponse.hasError());

            SubredditDetails subredditDetails = subredditDetailsResponse.getData();

            assertNotNull(subredditDetails);
        }
    }

    @Test
    void about_canGetSubredditRules_givenSubreddit() throws Exception {
        try (Reddit reddit = getClient()) {

            Response<List<SubredditRule>> subredditRulesResponse = reddit.subreddits()
                    .r("skyrim")
                    .rules();

            assertTrue(subredditRulesResponse.hasData());
            assertFalse(subredditRulesResponse.hasError());

            List<SubredditRule> subredditRules = subredditRulesResponse.getData();

            assertNotNull(subredditRules);
            assertFalse(subredditRules.isEmpty());
        }
    }

    @Test
    void sidebar_canGetSidebar_givenSubreddit() throws Exception {
        try (Reddit reddit = getClient()) {
            Response<Sidebar> sidebarResponse = reddit.subreddits()
                    .r("science")
                    .sidebar();

            assertTrue(sidebarResponse.hasData());
            assertFalse(sidebarResponse.hasError());

            Sidebar sidebar = sidebarResponse.getData();

            assertNotNull(sidebar);
        }
    }

    @Test
    void sticky_canGetFirstSticky_givenSubreddit() throws Exception {
        try (Reddit reddit = getClient()) {
            Response<Submission> stickyResponse = reddit.subreddits()
                    .r("starfield")
                    .sticky(Sticky.FIRST);

            assertTrue(stickyResponse.hasData());
            assertFalse(stickyResponse.hasError());

            Submission submission = stickyResponse.getData();

            assertNotNull(submission);
        }
    }

    @Test
    void sticky_canGetSecondSticky_givenSubreddit() throws Exception {
        try (Reddit reddit = getClient()) {
            Response<Submission> stickyResponse = reddit.subreddits()
                    .r("starfield")
                    .sticky(Sticky.SECOND);

            assertTrue(stickyResponse.hasData());
            assertFalse(stickyResponse.hasError());

            Submission submission = stickyResponse.getData();

            assertNotNull(submission);
        }
    }

    @Test
    void unsubscribeSubscribe_canSubscribeToSubreddits_givenSubreddits() throws Exception {
        try (Reddit reddit = getClient()) {
            //Unsubscribe
            Response<Void> unsubscribeResponse = reddit.subreddits()
                    .r("starfield")
                    .unsubscribe();

            assertFalse(unsubscribeResponse.hasError());

            //Check we are no longer subscribed to r/starfield
            assertFalse(reddit.subreddits()
                    .r("starfield")
                    .about()
                    .getData()
                    .isUserSubscribed());

            //Resubscribe
            Response<Void> subscribeResponse = reddit.subreddits()
                    .r("starfield")
                    .subscribe();

            assertFalse(subscribeResponse.hasError());

            //Check we are now subscribed to r/starfield again
            assertTrue(reddit.subreddits()
                    .r("starfield")
                    .about()
                    .getData()
                    .isUserSubscribed());
        }
    }

    @Test
    @Disabled("Let's not run this")
    void subscribeFluent_canSubscribeToSubreddits_givenSubreddits() throws Exception {
        try (Reddit reddit = getClient()) {
            Response<Void> subscribeResponse = reddit.subreddits()
                    .subreddits(reddit.listing()
                            .subreddits("skyrim")
                            .execute()
                            .getData()
                    ).subscribe();

            assertFalse(subscribeResponse.hasError());
        }
    }

    @Test
    @Disabled("Let's not run this")
    void unsubscribeFluent_canUnsubscribeFromAllSubreddits_givenSubreddits() throws Exception {
        try (Reddit reddit = getClient()) {

            PagedResponse<Subreddit> subredditPagedResponse = reddit.subreddits()
                    .mine()
                    .limit(10)
                    .subscribed()
                    .execute();

            while (subredditPagedResponse.hasNext()) {
                Collection<Subreddit> subreddits = subredditPagedResponse.getData().getResults();
                reddit.subreddits()
                        .subreddits(subreddits)
                        .unsubscribe();
                subredditPagedResponse = subredditPagedResponse.next();
            }
        }
    }

    @Test
    void subscribed_canGetAllSubscribedSubreddits() throws Exception {
        try (Reddit reddit = getClient()) {
            PagedResponse<Subreddit> subredditPagedResponse = reddit.subreddits()
                    .mine()
                    .subscribed()
                    .execute();
            assertTrue(subredditPagedResponse.hasData());
            assertFalse(subredditPagedResponse.hasError());

            Page<Subreddit> subreddits = subredditPagedResponse.getData();

            assertFalse(subreddits.isEmpty());
        }
    }

}