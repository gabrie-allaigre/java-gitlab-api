package com.talanlabs.gitlab.api.v4.services;

import com.talanlabs.gitlab.api.Paged;
import com.talanlabs.gitlab.api.v4.GitLabAPI;
import com.talanlabs.gitlab.api.v4.Pagination;
import com.talanlabs.gitlab.api.v4.http.Query;
import com.talanlabs.gitlab.api.v4.models.GitlabMergeRequest;

import java.io.IOException;
import java.io.Serializable;

/**
 * Merge requests API
 * <p>
 * http://doc.gitlab.com/ce/api/merge_requests.html
 */
public class GitLabAPIMergeRequests {

    private final GitLabAPI gitLabAPI;

    public GitLabAPIMergeRequests(GitLabAPI gitLabAPI) {
        super();
        this.gitLabAPI = gitLabAPI;
    }

    /**
     * Get all merge requests for this project.
     *
     * @param projectId    (required) - The ID of a project
     * @param pagination   (optional) - number of projects to return per page
     * @param state        (optional) - Return all merge requests or just those that are opened, closed, locked, or merged
     * @param sourceBranch (optional) - Return merge requests with the given source branch
     * @param targetBranch (optional) - Return merge requests with the given target branch
     * @return merge requests
     * @throws IOException
     */
    public Paged<GitlabMergeRequest> getAllProjectMergeRequests(Serializable projectId, Pagination pagination, String state, String sourceBranch, String targetBranch) throws IOException {
        Query query;
        if (pagination != null) {
            query = pagination.asQuery();
        } else {
            query = Query.newQuery();
        }

        if (state != null) {
            query.append("state", state).build();
        }

        if (sourceBranch != null) {
            query.append("source_branch", sourceBranch).build();
        }

        if (targetBranch != null) {
            query.append("target_branch", targetBranch).build();
        }

        String tailUrl = String.format("/projects/%s/merge_requests%s", gitLabAPI.sanitize(projectId), query.build());
        return gitLabAPI.retrieve().toPaged(tailUrl, GitlabMergeRequest[].class);
    }
}
