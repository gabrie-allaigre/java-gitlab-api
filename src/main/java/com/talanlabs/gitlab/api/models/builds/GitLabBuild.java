package com.talanlabs.gitlab.api.models.builds;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.talanlabs.gitlab.api.models.commits.GitLabCommit;
import com.talanlabs.gitlab.api.models.runners.GitLabRunner;
import com.talanlabs.gitlab.api.models.users.GitLabUser;

public class GitLabBuild {

    private GitLabCommit commit;
    private Float coverage;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("artifacts_file")
    private ArtifactsFile artifactsFile;
    @JsonProperty("finishedAt")
    private String finishedAt;
    private Integer id;
    private String name;
    private String ref;
    private GitLabRunner runner;
    private String stage;
    @JsonProperty("started_at")
    private String startedAt;
    private String status;
    private Boolean tag;
    private GitLabUser user;

    public GitLabUser getUser() {
        return user;
    }

    public void setUser(GitLabUser user) {
        this.user = user;
    }

    public Boolean getTag() {
        return tag;
    }

    public void setTag(Boolean tag) {
        this.tag = tag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(String startedAt) {
        this.startedAt = startedAt;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public GitLabRunner getRunner() {
        return runner;
    }

    public void setRunner(GitLabRunner runner) {
        this.runner = runner;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(String finishedAt) {
        this.finishedAt = finishedAt;
    }

    public ArtifactsFile getArtifactsFile() {
        return artifactsFile;
    }

    public void setArtifactsFile(ArtifactsFile artifactsFile) {
        this.artifactsFile = artifactsFile;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Float getCoverage() {
        return coverage;
    }

    public void setCoverage(Float coverage) {
        this.coverage = coverage;
    }

    public GitLabCommit getCommit() {
        return commit;
    }

    public void setCommit(GitLabCommit commit) {
        this.commit = commit;
    }

    public static class ArtifactsFile {

        private String filename;
        private Long size;

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }

        public Long getSize() {
            return size;
        }

        public void setSize(Long size) {
            this.size = size;
        }
    }
}
