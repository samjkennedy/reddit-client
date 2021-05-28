package com.skennedy.reddit.client.authorization;

public class AuthClientFactory {

    private static AuthClient authClient;

    public static AuthClient getClient() {
        if (authClient == null) {
            authClient = new AuthClientImpl();
        }
        return authClient;
    }
}
