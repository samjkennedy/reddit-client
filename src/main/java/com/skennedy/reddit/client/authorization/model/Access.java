package com.skennedy.reddit.client.authorization.model;

import com.google.gson.annotations.SerializedName;
import com.skennedy.reddit.client.common.model.OAuthScope;

import java.util.List;

public class Access {

    @SerializedName("access_token")
    private final String accessToken;
    @SerializedName("token_type")
    private final String tokenType;
    @SerializedName("expires_in")
    private final long expiresIn;
    @SerializedName("scope")
    private final List<OAuthScope> oAuthScopes;
    @SerializedName("refresh_token")
    private final String refreshToken;

    public Access(String accessToken, String tokenType, long expiresIn, List<OAuthScope> oAuthScopes, String refreshToken) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
        this.oAuthScopes = oAuthScopes;
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public List<OAuthScope> getOAuthScopes() {
        return oAuthScopes;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
