package com.skennedy.reddit.client.authorization.model;

import com.google.gson.annotations.SerializedName;

public class AccessTokenRequest {

    @SerializedName("grant_type")
    private String grantType;
    private String code;
    @SerializedName("redirect_uri")
    private String redirectUri;

    public AccessTokenRequest(String grantType, String code, String redirectUri) {
        this.grantType = grantType;
        this.code = code;
        this.redirectUri = redirectUri;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }
}
