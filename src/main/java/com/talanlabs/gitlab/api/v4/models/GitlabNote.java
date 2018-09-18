package com.talanlabs.gitlab.api.v4.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.talanlabs.gitlab.api.v4.models.users.GitLabUser;

import java.util.Date;

public class GitlabNote {

    public static final String URL = "/notes";

    private Integer id;
    private String body;
    private String attachment;
    private GitLabUser author;
    private boolean system;
    private boolean upvote;
    private boolean downvote;
    private boolean resolved;

    @JsonProperty("created_at")
    private Date createdAt;

    private Position position;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public GitLabUser getAuthor() {
        return author;
    }

    public void setAuthor(GitLabUser author) {
        this.author = author;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public boolean isSystem() {
        return system;
    }

    public void setSystem(boolean system) {
        this.system = system;
    }

    public boolean isUpvote() {
        return upvote;
    }

    public void setUpvote(boolean upvote) {
        this.upvote = upvote;
    }

    public boolean isDownvote() {
        return downvote;
    }

    public void setDownvote(boolean downvote) {
        this.downvote = downvote;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }

    public static class Position {
        @JsonProperty("base_sha")
        private String baseSha;
        @JsonProperty("start_sha")
        private String startSha;
        @JsonProperty("head_sha")
        private String headSha;
        @JsonProperty("old_path")
        private String oldPath;
        @JsonProperty("new_path")
        private String newPath;
        @JsonProperty("position_type")
        private String positionType;
        @JsonProperty("old_line")
        private Integer oldLine;
        @JsonProperty("new_line")
        private Integer newLine;

        public String getBaseSha() {
            return baseSha;
        }

        public void setBaseSha(String baseSha) {
            this.baseSha = baseSha;
        }

        public String getStartSha() {
            return startSha;
        }

        public void setStartSha(String startSha) {
            this.startSha = startSha;
        }

        public String getHeadSha() {
            return headSha;
        }

        public void setHeadSha(String headSha) {
            this.headSha = headSha;
        }

        public String getOldPath() {
            return oldPath;
        }

        public void setOldPath(String oldPath) {
            this.oldPath = oldPath;
        }

        public String getNewPath() {
            return newPath;
        }

        public void setNewPath(String newPath) {
            this.newPath = newPath;
        }

        public String getPositionType() {
            return positionType;
        }

        public void setPositionType(String positionType) {
            this.positionType = positionType;
        }

        public Integer getOldLine() {
            return oldLine;
        }

        public void setOldLine(Integer oldLine) {
            this.oldLine = oldLine;
        }

        public Integer getNewLine() {
            return newLine;
        }

        public void setNewLine(Integer newLine) {
            this.newLine = newLine;
        }
    }
}
