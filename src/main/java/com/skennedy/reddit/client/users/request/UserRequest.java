package com.skennedy.reddit.client.users.request;

import com.skennedy.reddit.client.account.model.Account;
import com.skennedy.reddit.client.authorization.model.Access;
import com.skennedy.reddit.client.common.model.OAuthScope;
import com.skennedy.reddit.client.common.request.Request;
import com.skennedy.reddit.client.common.response.Fail;
import com.skennedy.reddit.client.common.response.Response;
import com.skennedy.reddit.client.common.util.RequestUtils;
import com.skennedy.reddit.client.listing.model.Comment;
import com.skennedy.reddit.client.listing.model.Submission;
import com.skennedy.reddit.client.users.model.AccountThing;
import com.skennedy.reddit.client.users.model.HistoryType;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

import java.nio.charset.StandardCharsets;

public class UserRequest extends Request {

    private final String username;

    public UserRequest(Access access, CloseableHttpClient httpClient, String username) throws IllegalAccessException {
        super(access, httpClient, OAuthScope.READ);

        if (StringUtils.isBlank(username)) {
            throw new IllegalArgumentException("Username must not be blank");
        }
        this.username = username;
    }

    public UserCommentHistoryRequest commentHistory() throws IllegalAccessException {
        return new UserCommentHistoryRequest(access, httpClient, username);
    }

    public UserPostHistoryRequest postHistory() throws IllegalAccessException {
        return new UserPostHistoryRequest(access, httpClient, username);
    }

    public Response<Account> about() throws Exception {

        HttpGet get = new HttpGet("https://oauth.reddit.com/user/" + username + "/about");

        get.setHeader(HttpHeaders.USER_AGENT, RequestUtils.USER_AGENT);
        get.setHeader(HttpHeaders.AUTHORIZATION, "bearer " + access.getAccessToken());

        try (CloseableHttpResponse response = httpClient.execute(get)) {
            String content = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
            if (response.getStatusLine().getStatusCode() != 200) {
                return Response.error(new Fail<>(RequestUtils.parseError(response)));
            }
            AccountThing accountThing = gson.fromJson(content, AccountThing.class);
            return Response.success(accountThing.getData());
        }
    }
}
