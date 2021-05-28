package com.skennedy.reddit.client.subreddit;

import com.skennedy.reddit.client.Reddit;
import com.skennedy.reddit.client.common.AuthedIntegrationTest;
import com.skennedy.reddit.client.common.response.Response;
import com.skennedy.reddit.client.subreddit.model.SubredditDetails;
import com.skennedy.reddit.client.subreddit.model.SubredditRule;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SubredditClientIntegrationTest extends AuthedIntegrationTest {

    @Test
    void about_canGetSubredditDetails_givenSubreddit() throws Exception {
        try (Reddit reddit = getClient()) {

            Response<SubredditDetails> subredditDetailsResponse = reddit.subreddit()
                    .about("skyrim");

            assertTrue(subredditDetailsResponse.hasData());
            assertFalse(subredditDetailsResponse.hasError());

            SubredditDetails subredditDetails = subredditDetailsResponse.getData();

            assertNotNull(subredditDetails);
        }
    }

    @Test
    void about_canGetSubredditRules_givenSubreddit() throws Exception {
        try (Reddit reddit = getClient()) {

            Response<List<SubredditRule>> subredditRulesResponse = reddit.subreddit()
                    .rules("skyrim");

            assertTrue(subredditRulesResponse.hasData());
            assertFalse(subredditRulesResponse.hasError());

            List<SubredditRule> subredditRules = subredditRulesResponse.getData();

            assertNotNull(subredditRules);
            assertFalse(subredditRules.isEmpty());
        }
    }

}