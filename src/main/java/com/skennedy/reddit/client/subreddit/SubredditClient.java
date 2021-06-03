package com.skennedy.reddit.client.subreddit;

import com.skennedy.reddit.client.listing.model.Subreddit;
import com.skennedy.reddit.client.subreddit.request.MySubredditsRequest;
import com.skennedy.reddit.client.subreddit.request.SubredditRequest;
import com.skennedy.reddit.client.subreddit.request.SubredditsRequest;

import java.util.Collection;

public interface SubredditClient {

    /**
     * Begins a flow for accessing a subreddit.
     *
     * @param subreddit the subreddit name without the r/ prefix
     * @return a SubredditRequest instance
     */
    SubredditRequest r(String subreddit);

    /**
     * Begins a flow for accessing multiple subreddits.
     *
     * @param subreddits multiple subreddit names without the r/ prefix
     * @return a SubredditRequest instance
     */
    SubredditsRequest r(String subreddit, String... subreddits);

    /**
     * Begins a flow for accessing a subreddit.
     *
     * @param subreddit the subreddit instance
     * @return a SubredditRequest instance
     */
    SubredditRequest subreddit(Subreddit subreddit);

    /**
     * Begins a flow for accessing multiple subreddits. Some functions can only be performed on one subreddit, e.g. about()
     *
     * @param subreddits multiple subreddit instances
     * @return a SubredditRequest instance
     */
    SubredditsRequest subreddits(Subreddit subreddit, Subreddit... subreddits);

    /**
     * Begins a flow for accessing multiple subreddits.
     *
     * @param subreddits a collection of subreddit names without the r/ prefix
     * @return a SubredditRequest instance
     */
    SubredditsRequest r(Collection<String> subreddits);

    /**
     * Begins a flow for accessing multiple subreddits.
     *
     * @param subreddits a collection of subreddit instances
     * @return a SubredditRequest instance
     */
    SubredditsRequest subreddits(Collection<Subreddit> subreddits);

    /**
     * Begins a flow for accessing the logged in user's subreddits
     *
     * @return a MySubredditsRequest instance
     */
    MySubredditsRequest mine();
}
