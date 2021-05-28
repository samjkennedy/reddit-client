package com.skennedy.reddit.client.listing.error;

import com.skennedy.reddit.client.common.error.ErrorCode;

public enum SearchErrorCode implements ErrorCode {

    INCORRECT_NUMBER_OF_LISTINGS("Incorrect number of listings returned");

    private final String message;

    SearchErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
