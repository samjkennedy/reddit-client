package com.skennedy.reddit.client.users;

import com.skennedy.reddit.client.authorization.model.Access;
import com.skennedy.reddit.client.users.request.UserRequest;
import org.apache.http.impl.client.CloseableHttpClient;

public class UserClientImpl implements UserClient {

    private final Access access;
    private final CloseableHttpClient httpClient;

    public UserClientImpl(Access access, CloseableHttpClient httpClient) {
        this.access = access;
        this.httpClient = httpClient;
    }

    @Override
    public UserRequest user(String username) throws IllegalAccessException {
        return new UserRequest(access, httpClient, username);
    }
}
