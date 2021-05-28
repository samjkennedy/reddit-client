package com.skennedy.reddit.client;

import com.skennedy.reddit.client.authorization.AuthClient;
import com.skennedy.reddit.client.authorization.AuthClientFactory;
import com.skennedy.reddit.client.authorization.model.Access;
import com.skennedy.reddit.client.common.response.Response;
import org.apache.http.impl.client.HttpClientBuilder;

/*
 * Big ol' TODO:
 *          This should be an interface with some method of creating it based off of what kind of application you're using
 *          i.e. do I need a secret or not, AutoRenewable etc etc
 */
public class RedditWebApp extends AbstractReddit {

    private final String clientId;
    private final String clientSecret;

    public RedditWebApp(Access access, String clientId, String clientSecret) {
        super(access, HttpClientBuilder.create().build());

        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    @Override
    public RedditWebApp refresh() throws Exception {
        AuthClient authClient = AuthClientFactory.getClient();

        Response<Access> accessResponse = authClient.refresh(access.getRefreshToken(), clientId, clientSecret);
        if (accessResponse.hasError()) {
            throw new IllegalAccessException("Could not refresh token: " + accessResponse.getError().getCode());
        }
        access = accessResponse.getData();

        refreshClients(access);

        return this;
    }

    @Override
    public void close() throws Exception {
        httpClient.close();
    }
}
