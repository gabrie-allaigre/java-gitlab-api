package com.talanlabs.gitlab.api.v3.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.talanlabs.gitlab.api.v3.models.users.GitLabUser;

public abstract class GitlabAbstractMember extends GitLabUser {

    public static final String URL = "/members";

    @JsonProperty("access_level")
    private int accessLevel;

    public GitlabAccessLevel getAccessLevel() {
        return GitlabAccessLevel.fromAccessValue(accessLevel);
    }

    public void setAccessLevel(GitlabAccessLevel accessLevel) {
        this.accessLevel = accessLevel.accessValue;
    }

}
