package com.talanlabs.gitlab.api.v4.services;

import com.talanlabs.gitlab.api.Paged;
import com.talanlabs.gitlab.api.v4.GitLabAPI;
import com.talanlabs.gitlab.api.v4.GitlabMergeRequestDiff;

import java.io.IOException;
import java.io.Serializable;

public class GitLabAPIMergeRequestDiff {

    private static final String BASE_URL = "/projects/%s/merge_requests/%d/versions";

    private final GitLabAPI gitLabAPI;

    public GitLabAPIMergeRequestDiff(GitLabAPI gitLabAPI) {
        super();
        this.gitLabAPI = gitLabAPI;
    }

    /**
     * Gets a list of merge request diffs.
     * <p>
     * GET /projects/:id/merge_requests/:merge_request_iid/versions
     *
     * @param projectId (required) - The ID of the project
     * @param iid       (required) - The internal ID of the merge request
     * @return Instance of {@link GitlabMergeRequestDiff}
     * @throws IOException
     */
    public Paged<GitlabMergeRequestDiff> getMergeRequestDiff(Serializable projectId, Integer iid) throws IOException {
        String tailUrl = String.format(BASE_URL, gitLabAPI.sanitize(projectId), iid);
        return gitLabAPI.retrieve().toPaged(tailUrl, GitlabMergeRequestDiff[].class);
    }

    /**
     * Returns a single merge request diff filtered by merge request diff version id.
     * <p>
     * GET /projects/:id/merge_requests/:merge_request_iid/versions/:version_id
     *
     * @param projectId (required) - The ID of the project
     * @param iid       (required) - The internal ID of the merge request
     * @param versionId (required) - The ID of the merge request diff version
     * @return Instance of {@link GitlabMergeRequestDiff}
     * @throws IOException
     */
    public GitlabMergeRequestDiff getMergeRequestDiffByVersion(Serializable projectId, Integer iid, Integer versionId) throws IOException {
        String tailUrl = String.format(BASE_URL + "/%d", gitLabAPI.sanitize(projectId), iid, versionId);
        return gitLabAPI.retrieve().to(tailUrl, GitlabMergeRequestDiff.class);
    }

}
