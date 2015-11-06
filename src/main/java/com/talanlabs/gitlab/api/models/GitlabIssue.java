package com.talanlabs.gitlab.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.talanlabs.gitlab.api.models.users.GitLabUser;

import java.util.Date;

public class GitlabIssue {

    public enum Action {
        LEAVE, CLOSE, REOPEN
    }

    public static final String STATE_CLOSED = "closed";
    public static final String STATE_OPENED = "opened";

    public static final String URL = "/issues";

    private int id;
    private int iid;

    @JsonProperty("project_id")
    private int projectId;

    private String title;
    private String description;
    private String[] labels;
    private GitlabMilestone milestone;

    private GitLabUser assignee;
    private GitLabUser author;

    private String state;

    @JsonProperty("updated_at")
    private Date updatedAt;

    @JsonProperty("created_at")
    private Date createdAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIid() {
        return iid;
    }

    public void setIid(int iid) {
        this.iid = iid;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getLabels() {
        return labels;
    }

    public void setLabels(String[] labels) {
        this.labels = labels;
    }

    public GitlabMilestone getMilestone() {
        return milestone;
    }

    public void setMilestone(GitlabMilestone milestone) {
        this.milestone = milestone;
    }

    public GitLabUser getAssignee() {
        return assignee;
    }

    public void setAssignee(GitLabUser assignee) {
        this.assignee = assignee;
    }

    public GitLabUser getAuthor() {
        return author;
    }

    public void setAuthor(GitLabUser author) {
        this.author = author;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
