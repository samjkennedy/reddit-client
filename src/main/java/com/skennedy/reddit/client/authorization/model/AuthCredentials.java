package com.skennedy.reddit.client.authorization.model;

public class AuthCredentials {

    private final String authCode;
    private final String state;

    public AuthCredentials(String authCode, String state) {
        this.authCode = authCode;
        this.state = state;
    }

    public String getAuthCode() {
        return authCode;
    }

    public String getState() {
        return state;
    }
}
