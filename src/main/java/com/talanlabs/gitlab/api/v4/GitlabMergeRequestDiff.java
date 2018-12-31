package com.talanlabs.gitlab.api.v4;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class GitlabMergeRequestDiff {

    private Integer id;

    @JsonProperty("head_commit_sha")
    private String headCommitSha;

    @JsonProperty("base_commit_sha")
    private String baseCommitSha;

    @JsonProperty("start_commit_sha")
    private String startCommitSha;

    @JsonProperty("created_at")
    private Date createdAt;

    @JsonProperty("merge_request_id")
    private Integer mergeRequestId;

    @JsonProperty("state")
    private String state;

    @JsonProperty("real_size")
    private Integer realSize;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHeadCommitSha() {
        return headCommitSha;
    }

    public void setHeadCommitSha(String headCommitSha) {
        this.headCommitSha = headCommitSha;
    }

    public String getBaseCommitSha() {
        return baseCommitSha;
    }

    public void setBaseCommitSha(String baseCommitSha) {
        this.baseCommitSha = baseCommitSha;
    }

    public String getStartCommitSha() {
        return startCommitSha;
    }

    public void setStartCommitSha(String startCommitSha) {
        this.startCommitSha = startCommitSha;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getMergeRequestId() {
        return mergeRequestId;
    }

    public void setMergeRequestId(Integer mergeRequestId) {
        this.mergeRequestId = mergeRequestId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getRealSize() {
        return realSize;
    }

    public void setRealSize(Integer realSize) {
        this.realSize = realSize;
    }
}
