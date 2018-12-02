package com.talanlabs.gitlab.api.v4.models.discussion;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.talanlabs.gitlab.api.v4.models.GitlabPosition;

import java.util.Date;

public class GitlabDiscussion {

    public static final String URL = "/discussions";

    private Integer id;

    @JsonProperty("merge_request_iid")
    private Integer mergeRequestIid;

    private String body;

    @JsonProperty("created_at")
    private Date createdAt;

    private GitlabPosition position;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMergeRequestIid() {
        return mergeRequestIid;
    }

    public void setMergeRequestIid(Integer mergeRequestIid) {
        this.mergeRequestIid = mergeRequestIid;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public GitlabPosition getPosition() {
        return position;
    }

    public void setPosition(GitlabPosition position) {
        this.position = position;
    }
}
