package com.skennedy.reddit.client.search;

import com.skennedy.reddit.client.Reddit;
import com.skennedy.reddit.client.common.AuthedIntegrationTest;
import com.skennedy.reddit.client.common.response.Page;
import com.skennedy.reddit.client.common.response.PagedResponse;
import com.skennedy.reddit.client.common.response.Response;
import com.skennedy.reddit.client.search.model.Comment;
import com.skennedy.reddit.client.search.model.CommentSort;
import com.skennedy.reddit.client.search.model.SortTime;
import com.skennedy.reddit.client.search.model.SubSort;
import com.skennedy.reddit.client.search.model.SubSortTimed;
import com.skennedy.reddit.client.search.model.Submission;
import com.skennedy.reddit.client.search.model.Subreddit;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SearchClientIntegrationTest extends AuthedIntegrationTest {

    @Test
    void search_returnsTopPosts_givenSubredditTop() throws Exception {
        try (Reddit reddit = getClient()) {

            PagedResponse<Submission> pageResponse = reddit.search()
                    .r("skyrim")
                    .by(SubSort.HOT)
                    .limit(5)
                    .execute();

            assertTrue(pageResponse.hasData());
            assertFalse(pageResponse.hasError());

            Page<Submission> submissions = pageResponse.getData();

            assertTrue(submissions.size() >= 5); //Stickied posts don't count towards the limit....

            for (Submission submission : submissions) {
                assertEquals("skyrim", submission.getSubreddit());
            }
        }
    }

    @Test
    void search_returnsMultiplePages_givenAfter() throws Exception {
        try (Reddit reddit = getClient()) {

            PagedResponse<Submission> pageResponse = reddit.search()
                    .r("skyrim")
                    .by(SubSortTimed.TOP, SortTime.ALL)
                    .limit(5)
                    .execute();

            assertTrue(pageResponse.hasData());
            assertFalse(pageResponse.hasError());

            Page<Submission> submissions = pageResponse.getData();

            assertEquals(5, submissions.size());

            Response<Page<Submission>> page2Response = pageResponse.next();

            assertTrue(page2Response.hasData());
            assertFalse(page2Response.hasError());

            Page<Submission> nextSubmissions = page2Response.getData();

            assertEquals(5, nextSubmissions.size());

            assertNotEquals(submissions, nextSubmissions);
        }
    }

    @Test
    void comments_returnsComments_givenSubredditArticle() throws Exception {
        try (Reddit reddit = getClient()) {

            PagedResponse<Submission> pageResponse = reddit.search()
                    .r("skyrim")
                    .by(SubSortTimed.TOP, SortTime.ALL)
                    .limit(1)
                    .execute();

            assertTrue(pageResponse.hasData());
            assertFalse(pageResponse.hasError());

            Page<Submission> submissions = pageResponse.getData();

            assertEquals(1, submissions.size());

            Submission submission = submissions.iterator().next();
            PagedResponse<Comment> commentsResponse = reddit.search()
                    .comments(submission)
                    .by(CommentSort.CONFIDENCE)
                    .limit(5)
                    .depth(0)
                    .execute();

            assertTrue(commentsResponse.hasData());
            assertFalse(commentsResponse.hasError());

            Page<Comment> comments = commentsResponse.getData();

            for (Comment comment : comments) {
            }

            assertFalse(comments.isEmpty());
        }
    }

    @Test
    void comments_returnsReplies_givenDepth() throws Exception {
        try (Reddit reddit = getClient()) {
            PagedResponse<Submission> pageResponse = reddit.search()
                .r("skyrim")
                .by(SubSortTimed.TOP, SortTime.ALL)
                .limit(1)
                .execute();

            assertTrue(pageResponse.hasData());
            assertFalse(pageResponse.hasError());

            Page<Submission> submissions = pageResponse.getData();

            assertEquals(1, submissions.size());

            Submission submission = submissions.iterator().next();

            PagedResponse<Comment> commentsResponse = reddit.search()
                    .comments(submission)
                    .by(CommentSort.TOP)
                    .limit(5)
                    .depth(1)
                    .execute();

            assertTrue(commentsResponse.hasData());
            assertFalse(commentsResponse.hasError());

            Page<Comment> comments = commentsResponse.getData();

            assertFalse(comments.isEmpty());

        }
    }

    @Test
    void search_returnsSubreddits_givenPrefixSearch() throws Exception {
        try (Reddit reddit = getClient()) {

            Response<List<Subreddit>> subredditsResponse = reddit.search()
                    .subreddits("sky")
                    .execute();

            assertTrue(subredditsResponse.hasData());
            assertFalse(subredditsResponse.hasError());

            for (Subreddit subreddit : subredditsResponse.getData()) {
                assertTrue(StringUtils.startsWithIgnoreCase(subreddit.getName(), "sky"));
            }
        }
    }

    @Test
    void search_returnsSubreddit_givenExactSearch() throws Exception {
        try (Reddit reddit = getClient()) {

            Response<List<Subreddit>> subredditsResponse = reddit.search()
                    .subreddits("ShotgunSeat")
                    .exact()
                    .execute();

            assertTrue(subredditsResponse.hasData());
            assertFalse(subredditsResponse.hasError());

            List<Subreddit> subreddits = subredditsResponse.getData();

            assertEquals(1, subreddits.size());
            assertEquals("ShotgunSeat", subreddits.get(0).getName());
        }
    }

    @Test
    void rising_canGetRisingPosts_givenSubreddit() throws Exception{
        try (Reddit reddit = getClient()) {

            PagedResponse<Submission> pageResponse = reddit.search()
                    .r("skyrim")
                    .rising()
                    .limit(5)
                    .execute();

            assertTrue(pageResponse.hasData());
            assertFalse(pageResponse.hasError());

            Page<Submission> submissions = pageResponse.getData();

            for (Submission submission : submissions) {
                System.out.println(submission.getTitle());
            }

            assertNotNull(submissions);
            assertFalse(submissions.isEmpty());
        }
    }
}