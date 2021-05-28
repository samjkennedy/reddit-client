package com.skennedy.reddit.client.best;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.skennedy.reddit.client.common.response.Fail;
import com.skennedy.reddit.client.common.response.Page;
import com.skennedy.reddit.client.common.response.Response;
import com.skennedy.reddit.client.common.util.RequestUtils;
import com.skennedy.reddit.client.search.model.Submission;
import com.skennedy.reddit.client.search.model.SubmissionListing;
import com.skennedy.reddit.client.search.model.SubmissionThing;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BestClientImpl implements BestClient {

    private final String token;
    private final CloseableHttpClient httpClient;
    final Gson gson;

    public BestClientImpl(String token, CloseableHttpClient httpClient) {
        this.token = token;
        this.httpClient = httpClient;
        gson = new GsonBuilder().create();
    }

    @Override
    public BestRequest best() {
        return new BestRequest(token);
    }

    public class BestRequest {

        private String token;
        private int limit;
        private String before;
        private String after;

        BestRequest(String token) {

            if (StringUtils.isBlank(token)) {
                throw new IllegalArgumentException("Token must not be blank");
            }
            this.token = token;

            //Defaults
            this.limit = 25;
        }

        /**
         * Limit the number of posts returned
         * @param limit The maximum number of submissions to return.
         *              Returns a default of 25 if not set.
         * @return instance of BestRequest
         */
        public BestRequest limit(int limit) {
            if (limit < 0 || limit > 100) {
                throw new IllegalArgumentException("Limit must be between 0 and 100 inclusive");
            }
            this.limit = limit;
            return this;
        }

        /**
         * Used to get a page before a given article name
         * @see Submission#getId() to get the before value
         * @param before the name of the submission to get a page before
         * @return instance of BestRequest
         */
        public BestRequest before(String before) {
            this.before = before;
            return this;
        }

        /**
         * Used to get a page after a given article name
         * @see Submission#getId() to get the after value
         * @param after the name of the submission to get a page after
         * @return instance of BestRequest
         */
        public BestRequest after(String after) {
            this.after = after;
            return this;
        }

        public Response<Page<Submission>> search() throws Exception {
            if (StringUtils.isNoneBlank(after, before)) {
                throw new IllegalArgumentException("Only one of before or after can be set, not both");
            }

            List<NameValuePair> params = new ArrayList<>();

            params.add(new BasicNameValuePair("limit", String.valueOf(limit)));
            if (StringUtils.isNotBlank(after)) {
                params.add(new BasicNameValuePair("after", after));
            }
            if (StringUtils.isNotBlank(before)) {
                params.add(new BasicNameValuePair("before", before));
            }

            String uri = new URIBuilder("https://oauth.reddit.com/best")
                    .addParameters(params)
                    .toString();

            HttpGet get = new HttpGet(uri);
            get.setHeader(HttpHeaders.USER_AGENT, RequestUtils.USER_AGENT);

            get.setHeader(HttpHeaders.AUTHORIZATION, "bearer " + token);

            try (CloseableHttpResponse response = httpClient.execute(get)) {
                String content = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
                if (response.getStatusLine().getStatusCode() != 200) {
                    return Response.error(new Fail<>(RequestUtils.parseError(response))); //TODO: Define errors
                }
                SubmissionListing submissionListing = gson.fromJson(content, SubmissionListing.class);

                Page<Submission> submissions = new Page<>(submissionListing.getBefore(), submissionListing.getAfter(),
                        submissionListing.getChildren().stream()
                                .map(SubmissionThing::getData)
                                .collect(Collectors.toList()));

                return Response.success(submissions);
            }
        }
    }
}
