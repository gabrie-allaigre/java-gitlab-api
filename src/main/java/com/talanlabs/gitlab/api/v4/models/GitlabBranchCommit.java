package com.talanlabs.gitlab.api.v4.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.talanlabs.gitlab.api.v4.models.users.GitLabUser;

import java.util.Date;

public class GitlabBranchCommit {
    public static String URL = "/users";

    private String id;
    private String tree;
    private String message;
    private GitLabUser author;
    private GitLabUser committer;

    @JsonProperty("authored_date")
    private Date authoredDate;

    @JsonProperty("committed_date")
    private Date committedDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTree() {
        return tree;
    }

    public void setTree(String tree) {
        this.tree = tree;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public GitLabUser getAuthor() {
        return author;
    }

    public void setAuthor(GitLabUser author) {
        this.author = author;
    }

    public GitLabUser getCommitter() {
        return committer;
    }

    public void setCommitter(GitLabUser committer) {
        this.committer = committer;
    }

    public Date getAuthoredDate() {
        return authoredDate;
    }

    public void setAuthoredDate(Date authoredDate) {
        this.authoredDate = authoredDate;
    }

    public Date getCommittedDate() {
        return committedDate;
    }

    public void setCommittedDate(Date committedDate) {
        this.committedDate = committedDate;
    }
}
