package com.skennedy.reddit.client.submit;

import com.skennedy.reddit.client.submit.request.SubmitCommentRequest;
import com.skennedy.reddit.client.submit.request.SubmitImageRequest;
import com.skennedy.reddit.client.submit.request.SubmitLinkRequest;
import com.skennedy.reddit.client.submit.request.SubmitTextPostRequest;
import com.skennedy.reddit.client.submit.request.VoteRequest;
import com.skennedy.reddit.client.submit.model.Vote;

import java.net.MalformedURLException;

public interface SubmissionClient {

    /**
     * Submit a link
     *
     * @param link the URL of the link to submit
     * @return a SubmitLinkRequest instance
     * @throws MalformedURLException if the link url is not valid
     */
    SubmitLinkRequest link(String link) throws MalformedURLException;

    /**
     * Submit a text post
     *
     * @param text The text to submit
     * @return a SubmitTextPostRequest instance
     */
    SubmitTextPostRequest textPost(String text);

    /**
     * Submit an image post
     *
     * @param imgUrl The url of the image to submit
     * @return a SubmitImageRequest instance
     * @throws MalformedURLException if the image url is not valid
     */
    SubmitImageRequest image(String imgUrl) throws MalformedURLException;

    /**
     * Post a comment
     * 
     * @param commentText The text of the comment
     * @return a SubmitCommentRequest instance
     */
    SubmitCommentRequest comment(String commentText);
//    SubmitVideoRequest video(Video img);
//    SubmitVideoGifRequest videoGif(VideoGif img); //wtf is a videogif, webm?

    /**
     * Vote on a post or comment
     *
     * @param vote Whether to upvote, downvote, or clear vote for a post or comment
     * @return a VoteRequest instance
     */
    VoteRequest vote(Vote vote);
}
