package com.talanlabs.gitlab.api.services;

import com.talanlabs.gitlab.api.GitLabAPI;
import com.talanlabs.gitlab.api.Paged;
import com.talanlabs.gitlab.api.Pagination;
import com.talanlabs.gitlab.api.http.Query;
import com.talanlabs.gitlab.api.models.commits.GitLabCommit;
import com.talanlabs.gitlab.api.models.commits.GitLabCommitComments;
import com.talanlabs.gitlab.api.models.commits.GitLabCommitDiff;
import com.talanlabs.gitlab.api.models.commits.GitLabCommitStatus;

import java.io.IOException;
import java.io.Serializable;

/**
 * Commits API
 * <p>
 * http://doc.gitlab.com/ce/api/commits.html
 */
public class GitLabAPICommits {

    private final GitLabAPI gitLabAPI;

    public GitLabAPICommits(GitLabAPI gitLabAPI) {
        super();
        this.gitLabAPI = gitLabAPI;
    }

    /**
     * List repository commits
     * <p>
     * Get a list of repository commits in a project.
     * <p>
     * GET /projects/:id/repository/commits
     *
     * @param projectId  (required) - The ID of a project
     * @param pagination (optional) - number of projects to return per page
     * @return
     * @throws IOException
     */
    public Paged<GitLabCommit> getAllCommits(Serializable projectId, Pagination pagination) throws IOException {
        Query query;
        if (pagination != null) {
            query = pagination.asQuery();
        } else {
            query = Query.newQuery();
        }
        String tailUrl = String.format("/projects/%s/repository/commits%s", gitLabAPI.sanitize(projectId), query.build());
        return gitLabAPI.retrieve().toPaged(tailUrl, GitLabCommit[].class);
    }

    /**
     * Get a single commit
     * <p>
     * Get a specific commit identified by the commit hash or name of a branch or tag.
     * <p>
     * GET /projects/:id/repository/commits/:sha
     *
     * @param projectId  (required) - The ID of a project
     * @param commitHash (required) - The commit SHA
     * @return
     * @throws IOException
     */
    public GitLabCommit getCommit(Serializable projectId, String commitHash) throws IOException {
        String tailUrl = String.format("/projects/%s/repository/commits/%s", gitLabAPI.sanitize(projectId), commitHash);
        return gitLabAPI.retrieve().to(tailUrl, GitLabCommit.class);
    }

    /**
     * Get the diff of a commit
     * <p>
     * Get the diff of a commit in a project.
     * <p>
     * GET /projects/:id/repository/commits/:sha/diff
     *
     * @param projectId  (required) - The ID of a project
     * @param commitHash (required) - The commit SHA
     * @param pagination (optional) - number of projects to return per page
     * @return
     * @throws IOException
     */
    public Paged<GitLabCommitDiff> getCommitDiffs(Serializable projectId, String commitHash, Pagination pagination) throws IOException {
        Query query;
        if (pagination != null) {
            query = pagination.asQuery();
        } else {
            query = Query.newQuery();
        }
        String tailUrl = String.format("/projects/%s/repository/commits/%s/diff%s", gitLabAPI.sanitize(projectId), commitHash, query.build());
        return gitLabAPI.retrieve().toPaged(tailUrl, GitLabCommitDiff[].class);
    }

    /**
     * Get the comments of a commit
     * <p>
     * Get the comments of a commit in a project.
     * <p>
     * GET /projects/:id/repository/commits/:sha/comments
     *
     * @param projectId  (required) - The ID of a project
     * @param commitHash (required) - The commit SHA
     * @param pagination (optional) - number of projects to return per page
     * @return
     * @throws IOException
     */
    public Paged<GitLabCommitComments> getCommitComments(Serializable projectId, String commitHash, Pagination pagination) throws IOException {
        Query query;
        if (pagination != null) {
            query = pagination.asQuery();
        } else {
            query = Query.newQuery();
        }
        String tailUrl = String.format("/projects/%s/repository/commits/%s/comments%s", gitLabAPI.sanitize(projectId), commitHash, query.build());
        return gitLabAPI.retrieve().toPaged(tailUrl, GitLabCommitComments[].class);
    }

    /**
     * Post comment to commit
     * <p>
     * Adds a comment to a commit. Optionally you can post comments on a specific line of a commit. Therefor both path, line_new and line_old are required.
     * <p>
     * POST /projects/:id/repository/commits/:sha/comments
     *
     * @param projectId  (required) - The ID of a project
     * @param commitHash (required) - The commit SHA
     * @param note       (required) - Text of comment
     * @param path       (optional) - The file path
     * @param line       (optional) - The line number
     * @param lineType   (optional) - The line type (new or old)
     * @return
     * @throws IOException
     */
    public GitLabCommitComments postCommitComments(Serializable projectId, String commitHash, String note, String path, Integer line, String lineType) throws IOException {
        String tailUrl = String.format("/projects/%s/repository/commits/%s/comments", gitLabAPI.sanitize(projectId), commitHash);
        return gitLabAPI.dispatch().with("note", note).with("path", path).with("line", line).with("line_type", lineType).to(tailUrl, GitLabCommitComments.class);
    }

    /**
     * Get the status of a commit
     * <p>
     * Get the statuses of a commit in a project.
     * <p>
     * GET /projects/:id/repository/commits/:sha/statuses
     *
     * @param projectId  (required) - The ID of a project
     * @param commitHash (required) - The commit SHA
     * @param pagination (optional) - number of projects to return per page
     * @return
     * @throws IOException
     */
    public Paged<GitLabCommitStatus> getCommitStatuses(Serializable projectId, String commitHash, Pagination pagination) throws IOException {
        Query query;
        if (pagination != null) {
            query = pagination.asQuery();
        } else {
            query = Query.newQuery();
        }
        String tailUrl = String.format("/projects/%s/repository/commits/%s/statuses%s", gitLabAPI.sanitize(projectId), commitHash, query.build());
        return gitLabAPI.retrieve().toPaged(tailUrl, GitLabCommitStatus[].class);
    }

    /**
     * Post the status to commit
     * <p>
     * Adds or updates a status of a commit.
     * <p>
     * POST /projects/:id/statuses/:sha
     *
     * @param projectId   (required) - The ID of a project
     * @param commitHash  (required) - The commit SHA
     * @param state       (required) - The state of the status. Can be: pending, running, success, failed, canceled
     * @param ref         (optional) - The ref (branch or tag) to which the status refers
     * @param name        (optional) - The label to differentiate this status from the status of other systems. Default: "default"
     * @param targetUrl   (optional) - The target URL to associate with this status
     * @param description (optional) - The short description of the status
     * @return
     * @throws IOException
     */
    public GitLabCommitStatus postCommitStatus(Serializable projectId, String commitHash, String state, String ref, String name, String targetUrl, String description) throws IOException {
        String tailUrl = String.format("/projects/%s/statuses/%s", gitLabAPI.sanitize(projectId), commitHash);
        return gitLabAPI.dispatch().with("state", state).with("ref", ref).with("name", name).with("target_url", targetUrl).with("description", description).to(tailUrl, GitLabCommitStatus.class);
    }
}
