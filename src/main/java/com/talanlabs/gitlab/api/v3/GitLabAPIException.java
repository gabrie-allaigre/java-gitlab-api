package com.talanlabs.gitlab.api.v3;

import java.io.IOException;

/**
 * Gitlab API Exception
 */
public class GitLabAPIException extends IOException {

    private int responseCode;

    public GitLabAPIException(String message, Integer responseCode, Throwable cause) {
        super(message, cause);
        this.responseCode = responseCode;
    }

    public int getResponseCode() {
        return responseCode;
    }
}
