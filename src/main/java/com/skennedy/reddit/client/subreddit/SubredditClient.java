package com.skennedy.reddit.client.subreddit;

import com.skennedy.reddit.client.common.response.Response;
import com.skennedy.reddit.client.subreddit.model.SubredditDetails;
import com.skennedy.reddit.client.subreddit.model.SubredditRule;

import java.util.List;

public interface SubredditClient {

    /**
     * Returns information about the subreddit such as description, subscriber count etc.
     * @param subreddit The non-prefixed name of the subreddit, e.g. "java", not "r/java"
     * @return A response containing the details of the subreddit
     * @throws Exception TODO: Wrap exceptions into the response object
     */
    Response<SubredditDetails> about(String subreddit) throws Exception;

    /**
     * Returns the rules of the subreddit
     * @param subreddit The non-prefixed name of the subreddit, e.g. "java", not "r/java"
     * @return A response containing a list of the subreddit rules
     * @throws Exception TODO: Wrap exceptions into the response object
     */
    Response<List<SubredditRule>> rules(String subreddit) throws Exception;

}
