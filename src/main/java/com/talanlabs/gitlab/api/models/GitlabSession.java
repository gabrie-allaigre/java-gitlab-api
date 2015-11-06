package com.talanlabs.gitlab.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.talanlabs.gitlab.api.models.users.GitLabUser;

public class GitlabSession extends GitLabUser {

    public static final String URL = "/session";

    @JsonProperty("private_token")
    private String privateToken;

    public String getPrivateToken() {
        return privateToken;
    }

    public void setPrivateToken(String privateToken) {
        this.privateToken = privateToken;
    }

}
