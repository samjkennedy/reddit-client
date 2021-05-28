package com.skennedy.reddit.client.best;

import com.skennedy.reddit.client.RedditWebApp;
import com.skennedy.reddit.client.common.AuthedIntegrationTest;
import com.skennedy.reddit.client.common.response.Page;
import com.skennedy.reddit.client.common.response.Response;
import com.skennedy.reddit.client.search.model.Submission;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BestClientIntegrationTest extends AuthedIntegrationTest {

    @Test
    void best_returns100Best_givenLimit() throws Exception {
        try (RedditWebApp reddit = getClient()) {
            Response<Page<Submission>> bestResponse = reddit.best()
                    .limit(100)
                    .search();

            assertTrue(bestResponse.hasData());
            assertFalse(bestResponse.hasError());

            Page<Submission> submissions = bestResponse.getData();

            assertFalse(submissions.isEmpty());
            assertEquals(100, submissions.size());
        }
    }

    @Test
    void best_returns25submissions_givenNoLimit() throws Exception {
        try (RedditWebApp reddit = getClient()) {
            Response<Page<Submission>> bestResponse = reddit.best()
                    .search();

            assertTrue(bestResponse.hasData());
            assertFalse(bestResponse.hasError());

            Page<Submission> submissions = bestResponse.getData();

            assertFalse(submissions.isEmpty());
            assertEquals(25, submissions.size());
        }
    }

    @Test
    void best_returnsNextPage_givenAfter() throws Exception {
        try (RedditWebApp reddit = getClient()) {
            Response<Page<Submission>> bestResponse = reddit.best()
                    .limit(5)
                    .search();

            assertTrue(bestResponse.hasData());
            assertFalse(bestResponse.hasError());

            Page<Submission> submissions = bestResponse.getData();

            assertFalse(submissions.isEmpty());
            assertEquals(5, submissions.size());

            Response<Page<Submission>> nextPageResponse = reddit.best()
                    .after(submissions.getAfter())
                    .limit(5)
                    .search();

            Page<Submission> nextPage = nextPageResponse.getData();

            assertFalse(nextPage.isEmpty());
            assertEquals(5, nextPage.size());
        }
    }

}