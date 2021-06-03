package com.skennedy.reddit.client.subreddit.request;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.skennedy.reddit.client.authorization.model.Access;
import com.skennedy.reddit.client.common.response.Fail;
import com.skennedy.reddit.client.common.response.Response;
import com.skennedy.reddit.client.common.util.RequestUtils;
import com.skennedy.reddit.client.listing.error.SearchErrorCode;
import com.skennedy.reddit.client.listing.model.Sticky;
import com.skennedy.reddit.client.listing.model.Submission;
import com.skennedy.reddit.client.listing.model.SubmissionListing;
import com.skennedy.reddit.client.listing.model.Subreddit;
import com.skennedy.reddit.client.subreddit.model.Sidebar;
import com.skennedy.reddit.client.subreddit.model.SubredditDetails;
import com.skennedy.reddit.client.subreddit.model.SubredditDetailsThing;
import com.skennedy.reddit.client.subreddit.model.SubredditRule;
import com.skennedy.reddit.client.subreddit.model.SubredditRules;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

/**
 * Represents the actions that can be done on only one subreddit, any actions that can take multiple subreddits must go in {@link SubredditsRequest}
 */
public class SubredditRequest extends SubredditsRequest {

    private final String subreddit;

    public SubredditRequest(Access access, CloseableHttpClient httpClient, Subreddit subreddit) {
        super(access, httpClient, subreddit.getDisplayName());

        this.subreddit = subreddit.getDisplayName();
    }

    public SubredditRequest(Access access, CloseableHttpClient httpClient, String subreddit) {
        super(access, httpClient, subreddit);

        this.subreddit = subreddit;
    }

    /**
     * Returns information about the subreddit such as description, subscriber count etc.
     * @return A response containing the details of the subreddit
     */
    public Response<SubredditDetails> about() {

        try {

            String uri = "https://oauth.reddit.com/r/" + subreddit + "/about";

            HttpGet get = new HttpGet(uri);
            get.setHeader(HttpHeaders.USER_AGENT, RequestUtils.USER_AGENT);

            get.setHeader(HttpHeaders.AUTHORIZATION, "bearer " + access.getAccessToken());

            try (CloseableHttpResponse response = httpClient.execute(get)) {
                String content = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
                if (response.getStatusLine().getStatusCode() != 200) {
                    return Response.error(new Fail<>(RequestUtils.parseError(response)));
                }

                SubredditDetailsThing subredditDetailsThing = gson.fromJson(content, SubredditDetailsThing.class);

                return Response.success(subredditDetailsThing.getData());
            }
        } catch (Exception e) {
            return Response.error(new Fail<>(RequestUtils.parseException(e)));
        }
    }

    /**
     * Returns the rules of the subreddit
     * @return A response containing a list of the subreddit rules
     */
    public Response<List<SubredditRule>> rules() {

        try {
            String uri = "https://oauth.reddit.com/r/" + subreddit + "/about/rules";

            HttpGet get = new HttpGet(uri);
            get.setHeader(HttpHeaders.USER_AGENT, RequestUtils.USER_AGENT);

            get.setHeader(HttpHeaders.AUTHORIZATION, "bearer " + access.getAccessToken());

            try (CloseableHttpResponse response = httpClient.execute(get)) {
                String content = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
                if (response.getStatusLine().getStatusCode() != 200) {
                    return Response.error(new Fail<>(RequestUtils.parseError(response)));
                }

                SubredditRules subredditRules = gson.fromJson(content, SubredditRules.class);

                return Response.success(subredditRules.getRules());
            }
        } catch (Exception e) {
            return Response.error(new Fail<>(RequestUtils.parseException(e)));
        }
    }
    
    public Response<Sidebar> sidebar() {

        try {
            HttpGet get = new HttpGet("https://oauth.reddit.com/r/" + subreddit + "/about/sidebar");
            get.setHeader(HttpHeaders.USER_AGENT, RequestUtils.USER_AGENT);
            get.setHeader(HttpHeaders.AUTHORIZATION, "bearer " + access.getAccessToken());

            try (CloseableHttpResponse response = httpClient.execute(get)) {
                String content = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
                if (response.getStatusLine().getStatusCode() != 200) {
                    return Response.error(new Fail<>(RequestUtils.parseError(response)));
                }
                return Response.success(gson.fromJson(content, Sidebar.class));
            }
        } catch (Exception e) {
            return Response.error(new Fail<>(RequestUtils.parseException(e)));
        }
    }

    /**
     * Gets the first sticky submission on the subreddit if there is one
     * @return The first sticky on a subreddit if it exists, otherwise null
     */
    public Response<Submission> sticky() {
        return sticky(Sticky.FIRST);
    }

    /**
     * Returns a sticky submission on the subreddit if one exists
     * @param sticky Whether to return the {@code Sticky.FIRST} or the {@code Sticky.SECOND} sticky
     * @return The given sticky on the subreddit if it exists, otherwise null
     */
    public Response<Submission> sticky(Sticky sticky) {

        try {
            NameValuePair stickyParam = new BasicNameValuePair("num", String.valueOf(getIdx(sticky)));

            String uri = new URIBuilder("https://oauth.reddit.com/r/" + subreddit + "/about/sticky")
                    .addParameters(Collections.singletonList(stickyParam))
                    .toString();

            HttpGet get = new HttpGet(uri);

            get.setHeader(HttpHeaders.USER_AGENT, RequestUtils.USER_AGENT);
            get.setHeader(HttpHeaders.AUTHORIZATION, "bearer " + access.getAccessToken());


            try (CloseableHttpResponse response = httpClient.execute(get)) {
                String content = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
                if (response.getStatusLine().getStatusCode() != 200) {
                    return Response.error(new Fail<>(RequestUtils.parseError(response)));
                }

                JsonArray jsonArray = JsonParser.parseString(content).getAsJsonArray();
                if (jsonArray.size() != 2) {
                    return Response.error(new Fail<>(SearchErrorCode.INCORRECT_NUMBER_OF_LISTINGS));
                }
                JsonElement jsonElement = jsonArray.get(0);
                SubmissionListing submissionListing = gson.fromJson(jsonElement, SubmissionListing.class);

                return Response.success(submissionListing.getChildren().get(0).getData());
            }
        } catch (Exception e) {
            return Response.error(new Fail<>(RequestUtils.parseException(e)));
        }
    }

    private int getIdx(Sticky sticky) {
        switch (sticky) {
            case FIRST:
                return 1;
            case SECOND:
                return 2;
            default:
                throw new IllegalStateException("Unexpected value: " + sticky);
        }
    }

}
