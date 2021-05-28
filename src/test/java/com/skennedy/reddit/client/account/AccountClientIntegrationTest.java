package com.skennedy.reddit.client.account;

import com.skennedy.reddit.client.RedditWebApp;
import com.skennedy.reddit.client.account.model.Account;
import com.skennedy.reddit.client.account.model.SubredditKarma;
import com.skennedy.reddit.client.common.AuthedIntegrationTest;
import com.skennedy.reddit.client.common.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AccountClientIntegrationTest extends AuthedIntegrationTest {

    @Test
    void me_returnsMe() throws Exception {
        try (RedditWebApp reddit = getClient()) {

            Response<Account> meResponse = reddit.account().me();

            assertNotNull(meResponse);
            assertTrue(meResponse.hasData());
            assertFalse(meResponse.hasError());

            Account me = meResponse.getData();

            assertNotNull(me);
        }
    }

    @Test
    void karma_returnsKarma() throws Exception {
        try (RedditWebApp reddit = getClient()) {

            Response<List<SubredditKarma>> karmaResponse = reddit.account().karma();

            assertNotNull(karmaResponse);
            assertTrue(karmaResponse.hasData());
            assertFalse(karmaResponse.hasError());

            List<SubredditKarma> karma = karmaResponse.getData();

            assertNotNull(karma);
            assertFalse(karma.isEmpty());
        }
    }

}