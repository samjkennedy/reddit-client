package com.skennedy.reddit.client.subreddit.request;

import com.skennedy.reddit.client.authorization.model.Access;
import com.skennedy.reddit.client.common.model.OAuthScope;
import com.skennedy.reddit.client.common.model.SubredditListing;
import com.skennedy.reddit.client.common.model.SubredditThing;
import com.skennedy.reddit.client.common.request.ListingRequest;
import com.skennedy.reddit.client.common.response.Fail;
import com.skennedy.reddit.client.common.response.Page;
import com.skennedy.reddit.client.common.response.PagedResponse;
import com.skennedy.reddit.client.common.util.RequestUtils;
import com.skennedy.reddit.client.listing.model.Subreddit;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class MySubredditsRequest extends ListingRequest<MySubredditsRequest, Subreddit> {

    private Where where;

    public MySubredditsRequest(Access access, CloseableHttpClient httpClient) {
        super(access, httpClient, OAuthScope.MYSUBREDDITS);
    }

    public MySubredditsRequest subscribed() {
        this.where = Where.SUBSCRIBER;

        return this;
    }

    public MySubredditsRequest contributor() {
        this.where = Where.CONTRIBUTOR;

        return this;
    }

    public MySubredditsRequest moderator() {
        this.where = Where.MODERATOR;

        return this;
    }

    public MySubredditsRequest streams() {
        this.where = Where.STREAMS;

        return this;
    }

    @Override
    public PagedResponse<Subreddit> execute() {
        if (where == null) {
            this.where = Where.SUBSCRIBER;
        }
        try {
            List<NameValuePair> params = getListingParams();

            String uri = new URIBuilder("https://oauth.reddit.com/subreddits/mine/" + where.name().toLowerCase())
                    .addParameters(params)
                    .toString();

            HttpGet get = new HttpGet(uri);
            get.setHeader(HttpHeaders.USER_AGENT, RequestUtils.USER_AGENT);

            get.setHeader(HttpHeaders.AUTHORIZATION, "bearer " + access.getAccessToken());

            try (CloseableHttpResponse response = httpClient.execute(get)) {
                String content = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
                if (response.getStatusLine().getStatusCode() != 200) {
                    return PagedResponse.error(new Fail<>(RequestUtils.parseError(response)), this);
                }
                SubredditListing subredditListing = gson.fromJson(content, SubredditListing.class);

                Page<Subreddit> subreddits = new Page<>(subredditListing.getBefore(), subredditListing.getAfter(),
                        subredditListing.getChildren().stream()
                                .map(SubredditThing::getData)
                                .collect(Collectors.toList()));

                return PagedResponse.success(subreddits, this);
            }
        } catch (Exception e) {
            return PagedResponse.error(new Fail<>(RequestUtils.parseException(e)), this);
        }
    }

    private enum Where {
        SUBSCRIBER,
        CONTRIBUTOR,
        MODERATOR,
        STREAMS
    }
}
