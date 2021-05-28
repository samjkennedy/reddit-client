package com.skennedy.reddit.client.authorization;

import com.skennedy.reddit.client.authorization.model.Access;
import com.skennedy.reddit.client.authorization.model.AuthCredentials;
import com.skennedy.reddit.client.common.response.Response;
import org.apache.http.HttpResponse;

import java.util.List;

public interface AuthClient {

    HttpResponse authorise(String clientId, String duration, String redirectUri, List<String> scopes) throws Exception;

    Response<Access> getAccess(String clientId, String authCode, String redirectUri) throws Exception;

    Response<Access> getAccess(String clientId, String clientSecret, String authCode, String redirectUri) throws Exception;

    //Response<Access> getAccess(AuthCredentials credentials, String redirectUri) throws Exception;

    Response<Access> refresh(String token, String clientId, String clientSecret) throws Exception;
}
