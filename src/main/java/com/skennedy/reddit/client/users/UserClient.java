package com.skennedy.reddit.client.users;

import com.skennedy.reddit.client.users.request.UserRequest;

public interface UserClient {

    UserRequest user(String username) throws Exception;

}
