package com.talanlabs.gitlab.api.v4.services;

import com.talanlabs.gitlab.api.Paged;
import com.talanlabs.gitlab.api.v4.GitLabAPI;
import com.talanlabs.gitlab.api.v4.Pagination;
import com.talanlabs.gitlab.api.v4.http.Query;
import com.talanlabs.gitlab.api.v4.models.GitlabBranch;

import java.io.IOException;
import java.io.Serializable;

/**
 * Branches API
 * <p>
 * http://doc.gitlab.com/ce/api/branches.html
 */
public class GitLabAPIBranches {

    private final GitLabAPI gitLabAPI;

    public GitLabAPIBranches(GitLabAPI gitLabAPI) {
        super();
        this.gitLabAPI = gitLabAPI;
    }

    /**
     * Get a list of repository branches from a project, sorted by name alphabetically. This endpoint can be accessed without authentication if the repository is publicly accessible.
     *
     * @param projectId  (required) - The ID of a project
     * @param pagination (optional) - number of projects to return per page
     * @return branches
     * @throws IOException
     */
    public Paged<GitlabBranch> getAllBranches(Serializable projectId, Pagination pagination) throws IOException {
        Query query;
        if (pagination != null) {
            query = pagination.asQuery();
        } else {
            query = Query.newQuery();
        }

        String tailUrl = String.format("/projects/%s/repository/branches%s", gitLabAPI.sanitize(projectId), query.build());
        return gitLabAPI.retrieve().toPaged(tailUrl, GitlabBranch[].class);
    }

    /**
     * Get a single project repository branch. This endpoint can be accessed without authentication if the repository is publicly accessible.
     *
     * @param projectId (required) - The ID of a project
     * @param branch    (required) - The name of the branch
     * @return branch
     * @throws IOException
     */
    public GitlabBranch getBranch(Serializable projectId, String branch) throws IOException {
        String tailUrl = String.format("/projects/%s/repository/branches/%s", gitLabAPI.sanitize(projectId), branch);
        return gitLabAPI.retrieve().to(tailUrl, GitlabBranch.class);
    }
}
