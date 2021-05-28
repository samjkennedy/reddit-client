package com.skennedy.reddit.client.common.error;

public enum CommonErrorCode implements ErrorCode {

    ACCESS_DENIED("Insufficient permissions to perform this operation"),
    NOT_FOUND("Requested resource not found"),
    UNKNOWN_ERROR("An error occurred"),
    HTTP_ERROR("An error occurred making the HTTP request"),
    MALFORMED_URL("Malformed URL");

    private final String message;

    CommonErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
