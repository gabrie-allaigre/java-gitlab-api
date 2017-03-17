package com.talanlabs.gitlab.api.models.commits;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.talanlabs.gitlab.api.models.users.GitLabUser;

public class GitLabCommitComments {

    private GitLabUser author;
    private String path;
    private String note;
    private Integer name;

    @JsonProperty("line_type")
    private String lineType;

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    public String getLineType() {
        return lineType;
    }

    public void setLineType(String lineType) {
        this.lineType = lineType;
    }

    public GitLabUser getAuthor() {
        return author;
    }

    public void setAuthor(GitLabUser author) {
        this.author = author;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
