package com.talanlabs.gitlab.api.v4.services;

import com.talanlabs.gitlab.api.Paged;
import com.talanlabs.gitlab.api.v4.GitLabAPI;
import com.talanlabs.gitlab.api.v4.Pagination;
import com.talanlabs.gitlab.api.v4.http.Query;
import com.talanlabs.gitlab.api.v4.models.GitlabMergeRequest;
import com.talanlabs.gitlab.api.v4.utils.QueryHelper;

import java.io.IOException;
import java.io.Serializable;

/**
 * Merge Request API
 * <p>
 * https://docs.gitlab.com/ee/api/merge_requests.html
 */
public class GitLabAPIMergeRequest {

    private static final String BASE_URL = "/projects/%s/merge_requests";

    private final GitLabAPI gitLabAPI;

    public GitLabAPIMergeRequest(GitLabAPI gitLabAPI) {
        super();
        this.gitLabAPI = gitLabAPI;
    }

    /**
     * List project merge requests
     * <p>
     * GET /projects/:id/merge_requests?state=opened&source_branch=sourceBranch&sha=commit-sha
     *
     * @param projectId    (required) - The ID of the project
     * @param sourceBranch (required) - The name of the merge request
     * @param commitHash   (required) - The commit hash in the merge request
     * @param pagination   (optional) - number of projects to return per page
     * @return List of {@link GitlabMergeRequest}
     * @throws IOException
     */
    public Paged<GitlabMergeRequest> getAllProjectMergeRequestsBySourceBranchAndCommitShaWithStateOpen(Serializable projectId, String sourceBranch, String commitHash, Pagination pagination) throws IOException {
        Query query = QueryHelper.getQuery(pagination);
        String parameters = query
                .append("state", "opened")
                .append("source_branch", sourceBranch)
                .append("sha", commitHash)
                .build();

        String tailUrl = String.format(BASE_URL + "%s", gitLabAPI.sanitize(projectId), parameters);

        return gitLabAPI.retrieve().toPaged(tailUrl, GitlabMergeRequest[].class);
    }

}
