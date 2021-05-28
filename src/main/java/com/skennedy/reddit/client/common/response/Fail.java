package com.skennedy.reddit.client.common.response;

import com.skennedy.reddit.client.common.error.ErrorCode;

public class Fail<T> {

    private final ErrorCode code;
    private final String message;

    public Fail(ErrorCode code) {
        this(code, code.getMessage());
    }

    public Fail(ErrorCode code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorCode getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
