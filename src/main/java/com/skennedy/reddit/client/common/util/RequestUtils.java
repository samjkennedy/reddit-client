package com.skennedy.reddit.client.common.util;

import com.skennedy.reddit.client.common.error.CommonErrorCode;
import com.skennedy.reddit.client.common.error.ErrorCode;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;

public class RequestUtils {

    public static final String DOMAIN = "https://oauth.reddit.com/api/v1";
    public static final String USER_AGENT = "skennedy/reddit-client/0.1.0";

    public static ErrorCode parseError(HttpResponse response) {
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_FORBIDDEN) {
            return CommonErrorCode.ACCESS_DENIED;
        }
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_NOT_FOUND) {
            return CommonErrorCode.NOT_FOUND;
        }
        return CommonErrorCode.ACCESS_DENIED;
    }
}
