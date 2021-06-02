package com.skennedy.reddit.client.listing.request;


import com.skennedy.reddit.client.authorization.model.Access;
import com.skennedy.reddit.client.common.model.OAuthScope;
import com.skennedy.reddit.client.common.request.ListingRequest;
import com.skennedy.reddit.client.common.response.Fail;
import com.skennedy.reddit.client.common.response.Page;
import com.skennedy.reddit.client.common.response.PagedResponse;
import com.skennedy.reddit.client.common.util.RequestUtils;
import com.skennedy.reddit.client.listing.model.Submission;
import com.skennedy.reddit.client.listing.model.SubmissionListing;
import com.skennedy.reddit.client.listing.model.SubmissionThing;
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

public class BestRequest extends ListingRequest<BestRequest, Submission> {

    public BestRequest(Access access, CloseableHttpClient httpClient) {
        super(access, httpClient, OAuthScope.READ);
    }

    public PagedResponse<Submission> execute() {
        try {
            List<NameValuePair> params = getListingParams();

            String uri = new URIBuilder("https://oauth.reddit.com/best")
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
                SubmissionListing submissionListing = gson.fromJson(content, SubmissionListing.class);

                Page<Submission> submissions = new Page<>(submissionListing.getBefore(), submissionListing.getAfter(),
                        submissionListing.getChildren().stream()
                                .map(SubmissionThing::getData)
                                .collect(Collectors.toList()));

                return PagedResponse.success(submissions, this);
            }
        } catch (Exception e) {
            return PagedResponse.error(new Fail<>(RequestUtils.parseException(e)), this);
        }
    }
}
