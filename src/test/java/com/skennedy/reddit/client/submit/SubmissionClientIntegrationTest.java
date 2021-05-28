package com.skennedy.reddit.client.submit;

import com.skennedy.reddit.client.RedditWebApp;
import com.skennedy.reddit.client.common.AuthedIntegrationTest;
import com.skennedy.reddit.client.common.response.Page;
import com.skennedy.reddit.client.common.response.PagedResponse;
import com.skennedy.reddit.client.common.response.Response;
import com.skennedy.reddit.client.search.model.Comment;
import com.skennedy.reddit.client.search.model.Submission;
import com.skennedy.reddit.client.submit.model.Vote;
import org.junit.jupiter.api.Test;

import static com.skennedy.reddit.client.search.model.SubSort.NEW;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SubmissionClientIntegrationTest extends AuthedIntegrationTest {

    @Test
    void submitText_submitsTextPost_givenValidRequest() throws Exception {

        try (RedditWebApp reddit = getClient()) {

            Response<Void> submissionResponse = reddit.submit()
                    .textPost("I hope you enjoyed it")
                    .withTitle("This is today's test post")
                    .toSubreddit("ShotgunSeat")
                    .submit();

            assertFalse(submissionResponse.hasError());
        }
    }

    @Test
    void submitLink_submitsLink_givenValidRequest() throws Exception {

        try (RedditWebApp reddit = getClient()) {

            Response<Void> submissionResponse = reddit.submit()
                    .link("https://www.youtube.com/watch?v=dQw4w9WgXcQ")
                    .withTitle("test link")
                    .toSubreddit("ShotgunSeat")
                    .submit();

            assertFalse(submissionResponse.hasError());
        }
    }

    @Test
    void submitImage_submitsImage_givenValidRequest() throws Exception {

        try (RedditWebApp reddit = getClient()) {

            Response<Void> submissionResponse = reddit.submit()
                    .image("https://knowpathology.com.au/app/uploads/2018/07/Happy-Test-Screen-01.png")
                    .withTitle("test image")
                    .toSubreddit("ShotgunSeat")
                    .submit();

            assertFalse(submissionResponse.hasError());
        }
    }

    @Test
    void submitComment_submitsComment_givenValidRequest() throws Exception {

        try (RedditWebApp reddit = getClient()) {

            PagedResponse<Submission> pageResponse = reddit.search()
                    .r("ShotgunSeat")
                    .by(NEW)
                    .limit(1)
                    .execute();

            assertTrue(pageResponse.hasData());
            Page<Submission> submissions = pageResponse.getData();

            Submission submission = submissions.iterator().next();

            Response<Void> commentResponse = reddit.submit().comment("Hello I am a test comment!")
                    .onPost(submission)
                    .submit();

            assertFalse(commentResponse.hasError());

            Response<Page<Comment>> commentPageResponse = reddit.search()
                    .comments(submission)
                    .limit(1)
                    .execute();

            assertTrue(commentPageResponse.hasData());
            Page<Comment> comments = commentPageResponse.getData();

            Comment comment = comments.iterator().next();

            Response<Void> replyResponse = reddit.submit().comment("Hello test comment, I am a test reply!")
                    .inReplyTo(comment)
                    .submit();

            assertFalse(replyResponse.hasError());
        }
    }

    @Test
    void vote_canVoteOnSubmission() throws Exception {

        try (RedditWebApp reddit = getClient()) {

            PagedResponse<Submission> pageResponse = reddit.search()
                    .r("ShotgunSeat")
                    .by(NEW)
                    .limit(1)
                    .execute();

            assertTrue(pageResponse.hasData());
            Page<Submission> submissions = pageResponse.getData();

            Submission submission = submissions.iterator().next();

            Response<Void> voteResponse= reddit.submit()
                    .vote(Vote.UPVOTE)
                    .onSubmission(submission)
                    .submit();

            assertFalse(voteResponse.hasError());
        }
    }

}