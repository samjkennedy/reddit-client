reddit-client
==

## What is reddit-client?

reddit-client is a WIP fluent Java client for the Reddit API. It's intended to make using the Reddit API in your Java program as easy and readable as possible.

## How to use it?

### Conventions

reddit-client is designed to be consistent and easy to use. There are a few conventions used throughout to keep in mind.

#### The Reddit interface:

The `Reddit` interface is your way in to the Reddit API. 

It implements AutoClosable to close its HTTP connection when you're done using it, so it's recommended but not mandatory to use it in a try-with-resources block:

```
try (Reddit reddit = new RedditWebApp(access, clientId, clientSecret)) {

} catch (Exception e) {
    //handle
}
```

The `Reddit` interface has several methods on it that access various categories from the [Reddit API](https://www.reddit.com/dev/api/oauth).
e.g. to access endpoints in the `accounts` section, call `.accounts()` on your `Reddit` instance:

```
try (Reddit reddit = new RedditWebApp(access, clientId, clientSecret)) {
    Response<Account> meResponse = reddit.accounts()
        .me();

    if (meResponse.hasError) {
        //handle
    }
    
    Account me = meResponse.getData();
}
```

or to get the about page of a subreddit, call `.subreddits()` to begin a subreddit category flow:

```
try (Reddit reddit = new RedditWebApp(access, clientId, clientSecret)) {
    Response<SubredditDetails> subredditDetailsResponse = reddit.subreddits()
        .r("science")
        .about();
}
```

#### Requests/Responses:

All requests will return a `Response<T>` object where `T` is the data class expected (e.g. `Submission`). 

Requests that return a Reddit `Listing` will return a `PagedResponse<T>` that has utilities for paging through the response.

All Responses have a `data` field and an `error` field. If there is an error with the request (insufficient permissions, token expired, reddit servers down) then `data` will be null and `error` will contain information about the error.

All Responses have a `hasData()` and `hasError()` method to check the status of the response.

### Setting up an application

Coming soon!

### Examples

Getting an access for a webapp:

TODO!

Getting several pages of 25 results from the user's front page:
```
try (Reddit reddit = new RedditWebApp(access, clientId, clientSecret)) {
    PagedResponse<Submission> bestResponse = reddit.listing()
            .best()
            .limit(25)
            .execute();
    
    if (bestResponse.hasError()) {
        //handle error
    }

    Page<Submission> submissions = bestResponse.getData();

    for (Submission submission : submissions) {
        //Do your logic on the submission
    }

    //next() gets the next page of results with the same parameters
    PagedResponse<Submission> nextPageResponse = bestResponse.next();
    
    if (nextPageResponse.hasError()) {
        //handle error
    }
    
    Page<Submission> nextPage = nextPageResponse.getData();
}
```

Making a nfsw spoiler text post to a subreddit:
```
try (Reddit reddit = new RedditWebApp(access, clientId, clientSecret)) {

    //Submit responses do not return a value 
    //so return a Response<Void> that can contain errors but no data.
    Response<Void> submitResponse = reddit.submit()
        .textPost("I hope you enjoyed it")
        .withTitle("This is a test post")
        .nsfw()
        .spoiler()
        .toSubreddit("funny")
        .submit();

    if (submitResponse.hasError()) {
        //handle error
    }
}
```

Subscribing to every subreddit that begins with "Skyrim" in one fluent pass:

```
try (Reddit reddit = new RedditWebApp(access, clientId, clientSecret)) {
    reddit.listing()
        .subreddits("skyrim")
        .execute()
        .getData()
        .forEach(subreddit -> reddit.subreddits()
            .r(subreddit.getName())
            .subscribe());
}
```