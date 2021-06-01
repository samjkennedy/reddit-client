package com.skennedy.reddit.client.users;

import com.skennedy.reddit.client.Reddit;
import com.skennedy.reddit.client.account.model.Account;
import com.skennedy.reddit.client.common.AuthedIntegrationTest;
import com.skennedy.reddit.client.common.response.Page;
import com.skennedy.reddit.client.common.response.PagedResponse;
import com.skennedy.reddit.client.common.response.Response;
import com.skennedy.reddit.client.listing.model.Comment;
import com.skennedy.reddit.client.listing.model.Submission;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserClientIntegrationTest extends AuthedIntegrationTest {

    @Test
    void about_getsAccountDetails_givenAnAccount() throws Exception {
        try (Reddit reddit = getClient()) {

            Response<Account> accountResponse = reddit.users()
                    .user("Gallowboob")
                    .about();

            assertTrue(accountResponse.hasData());
            assertFalse(accountResponse.hasError());

            Account account = accountResponse.getData();

            assertNotNull(account);
        }
    }

    @Test
    void postHistory_getsAccountPostHistory_givenByNew() throws Exception {
        try (Reddit reddit = getClient()) {

            PagedResponse<Submission> submissionPagedResponse = reddit.users()
                    .user("ShotgunSeat")
                    .postHistory()
                    .limit(10)
                    .execute();

            assertTrue(submissionPagedResponse.hasData());
            assertFalse(submissionPagedResponse.hasError());

            Page<Submission> submissions = submissionPagedResponse.getData();

            assertNotNull(submissions);
            assertFalse(submissions.isEmpty());

            assertEquals(10, submissions.size());
        }
    }

    @Test
    void commentHistory_getsAccountCommentHistory_givenByNew() throws Exception {
        try (Reddit reddit = getClient()) {

            PagedResponse<Comment> commentPagedResponse = reddit.users()
                    .user("ShotgunSeat")
                    .commentHistory()
                    .limit(10)
                    .execute();

            assertTrue(commentPagedResponse.hasData());
            assertFalse(commentPagedResponse.hasError());

            Page<Comment> comments = commentPagedResponse.getData();

            assertNotNull(comments);
            assertFalse(comments.isEmpty());

            assertEquals(10, comments.size());
        }
    }

}