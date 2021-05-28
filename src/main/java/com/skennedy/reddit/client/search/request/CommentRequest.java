package com.skennedy.reddit.client.search.request;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.skennedy.reddit.client.common.error.CommonErrorCode;
import com.skennedy.reddit.client.common.request.ListingRequest;
import com.skennedy.reddit.client.common.response.Fail;
import com.skennedy.reddit.client.common.response.Page;
import com.skennedy.reddit.client.common.response.PagedResponse;
import com.skennedy.reddit.client.common.util.RequestUtils;
import com.skennedy.reddit.client.search.error.SearchErrorCode;
import com.skennedy.reddit.client.search.model.Comment;
import com.skennedy.reddit.client.search.model.CommentListing;
import com.skennedy.reddit.client.search.model.CommentSort;
import com.skennedy.reddit.client.search.model.CommentThing;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CommentRequest extends ListingRequest<CommentRequest, Comment> {

    private String subreddit;
    private String article;
    private String sort;
    private Integer depth;

    public CommentRequest(CloseableHttpClient httpClient, String token, String subreddit, String article) {
        super(token, httpClient);

        this.subreddit = subreddit;
        this.article = article;
    }

    /**
     * @param sort The ordering to apply
     * @return instance of CommentRequest
     */
    public CommentRequest by(CommentSort sort) {
        this.sort = sort.getSort();
        return this;
    }

    /**
     * @param depth The maximum depth of comment replies to return.
     *              Returns all children by default.
     * @return instance of CommentRequest
     */
    public CommentRequest depth(int depth) {
        if (depth < 0) {
            throw new IllegalArgumentException("Depth must not be negative");
        }
        this.depth = depth;
        return this;
    }

    @Override
    public PagedResponse<Comment> execute() {

        List<NameValuePair> params = new ArrayList<>();

        if (StringUtils.isNotBlank(sort)) {
            params.add(new BasicNameValuePair("sort", sort));
        }
        if (limit > 0) {
            params.add(new BasicNameValuePair("limit", String.valueOf(limit)));
        }
        if (depth != null && depth >= 0) {
            params.add(new BasicNameValuePair("depth", String.valueOf(depth)));
        }

        String uri;
        try {
            uri = new URIBuilder("https://oauth.reddit.com/r/" + subreddit + "/comments/" + article)
                    .addParameters(params)
                    .toString();
        } catch (URISyntaxException e) {
            return PagedResponse.error(new Fail<>(CommonErrorCode.MALFORMED_URL, e.getMessage()), this);
        }

        HttpGet get = new HttpGet(uri);
        get.setHeader(HttpHeaders.USER_AGENT, RequestUtils.USER_AGENT);

        get.setHeader(HttpHeaders.AUTHORIZATION, "bearer " + token);

        try (CloseableHttpResponse response = httpClient.execute(get)) {
            String content = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
            if (response.getStatusLine().getStatusCode() != 200) {
                return PagedResponse.error(new Fail<>(RequestUtils.parseError(response)), this);
            }

            //Comment requests come back as an array where the 1st element is the submission listing and the 2nd is the comment listing
            //Awkward to parse but I get it - can be useful for displaying a comments view
            //TODO: support returning the Submission obj too somehow
            JsonArray jsonArray = JsonParser.parseString(content).getAsJsonArray();
            if (jsonArray.size() != 2) {
                return PagedResponse.error(new Fail<>(SearchErrorCode.INCORRECT_NUMBER_OF_LISTINGS), this);
            }
            JsonElement jsonElement = jsonArray.get(1);
            CommentListing commentsListing = gson.fromJson(jsonElement, CommentListing.class);
            Page<Comment> comments = new Page<>(commentsListing.getBefore(), commentsListing.getAfter(),
                    commentsListing.getChildren().stream()
                            .map(CommentThing::getData)
                            .collect(Collectors.toList()));

            return PagedResponse.success(comments, this);

        } catch (IOException e) {
            return PagedResponse.error(new Fail<>(CommonErrorCode.UNKNOWN_ERROR, e.getMessage()), this);
        }
    }
}
