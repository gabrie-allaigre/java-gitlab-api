package com.talanlabs.gitlab.api.v4.services;

import com.talanlabs.gitlab.api.Paged;
import com.talanlabs.gitlab.api.v4.GitLabAPI;
import com.talanlabs.gitlab.api.v4.Pagination;
import com.talanlabs.gitlab.api.v4.http.Query;
import com.talanlabs.gitlab.api.v4.models.buildvariables.GitLabVariable;
import java.io.IOException;
import java.io.Serializable;

/**
 * Repositories API
 * <p>
 * http://doc.gitlab.com/ce/api/repositories.html
 */
public class GitLabAPIBuildVariables {

    private final GitLabAPI gitLabAPI;

    public GitLabAPIBuildVariables(GitLabAPI gitLabAPI) {
        super();
        this.gitLabAPI = gitLabAPI;
    }

    /**
     * List project variables
     * <p>
     * Get list of a project's build variables.
     * <p>
     * GET /projects/:id/variables
     *
     * @param projectId (required) - The ID of a project
     * @return list of project variables
     * @throws IOException
     */
    public Paged<GitLabVariable> getProjectVariables(Serializable projectId, Pagination pagination) throws IOException {
        Query q;
        if (pagination != null) {
            q = pagination.asQuery();
        } else {
            q = Query.newQuery();
        }
        String parameters = q.build();
        String tailUrl = String.format("/projects/%s/variables%s", gitLabAPI.sanitize(projectId), parameters);
        return gitLabAPI.retrieve().toPaged(tailUrl, GitLabVariable[].class);
    }

    /**
     * Show variable details
     * <p>
     * Get the details of a project's specific build variable.
     * <p>
     * GET /projects/:id/variables/:key
     *
     * @param projectId (required) - The ID of a project
     * @param key       (required) - The commit or branch name
     * @return variable
     */
    public GitLabVariable getProjectVariable(Serializable projectId, String key) throws IOException {
        String tailUrl = String.format("/projects/%s/variables/%s", gitLabAPI.sanitize(projectId), gitLabAPI.sanitize(key));
        return gitLabAPI.retrieve().to(tailUrl, GitLabVariable.class);
    }
}
