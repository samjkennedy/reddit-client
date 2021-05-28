package com.skennedy.reddit.client.subreddit;

import com.skennedy.reddit.client.subreddit.request.SubredditRequest;

public interface SubredditClient {

    SubredditRequest r(String subreddit) throws IllegalAccessException;

}
