package com.skennedy.reddit.client.users.request;

import com.skennedy.reddit.client.authorization.model.Access;
import com.skennedy.reddit.client.common.response.Fail;
import com.skennedy.reddit.client.common.response.Page;
import com.skennedy.reddit.client.common.response.PagedResponse;
import com.skennedy.reddit.client.common.util.RequestUtils;
import com.skennedy.reddit.client.listing.model.Submission;
import com.skennedy.reddit.client.listing.model.SubmissionListing;
import com.skennedy.reddit.client.listing.model.SubmissionThing;
import com.skennedy.reddit.client.users.model.HistoryType;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class UserPostHistoryRequest extends UserHistoryRequest<UserPostHistoryRequest, Submission> {

    public UserPostHistoryRequest(Access access, CloseableHttpClient httpClient, String username) throws IllegalAccessException {
        super(access, httpClient, username, HistoryType.LINKS);

        this.where = Where.SUBMITTED;
    }

    @Override
    public PagedResponse<Submission> execute() throws Exception {

        List<NameValuePair> params = getListingParams();

        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("sort", sort.name().toLowerCase()));
        params.add(new BasicNameValuePair("t", time.name().toLowerCase()));
        params.add(new BasicNameValuePair("type", historyType.name().toLowerCase()));

        String wherePath = getPath(where);

        HttpGet get = new HttpGet("https://oauth.reddit.com/user/" + username + wherePath);

        get.setHeader(HttpHeaders.USER_AGENT, RequestUtils.USER_AGENT);
        get.setHeader(HttpHeaders.AUTHORIZATION, "bearer " + access.getAccessToken());

        try (CloseableHttpResponse response = httpClient.execute(get)) {
            String content = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
            if (response.getStatusLine().getStatusCode() != 200) {
                return PagedResponse.error(new Fail<>(RequestUtils.parseError(response)), this);
            }
            SubmissionListing submissionListing = gson.fromJson(content, SubmissionListing.class);

            Page<Submission> submissions = new Page<>(submissionListing.getBefore(), submissionListing.getAfter(),
                    submissionListing.getChildren().stream()
                            .map(SubmissionThing::getData)
                            .collect(Collectors.toList()));

            return PagedResponse.success(submissions, this);
        }
    }
}
