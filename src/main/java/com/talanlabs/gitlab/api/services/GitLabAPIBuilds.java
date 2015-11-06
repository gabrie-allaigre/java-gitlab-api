package com.talanlabs.gitlab.api.services;

import com.talanlabs.gitlab.api.GitLabAPI;
import com.talanlabs.gitlab.api.Paged;
import com.talanlabs.gitlab.api.Pagination;
import com.talanlabs.gitlab.api.http.Query;
import com.talanlabs.gitlab.api.models.builds.GitLabBuild;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.StringJoiner;

/**
 * Builds API
 * <p>
 * http://doc.gitlab.com/ce/api/builds.html
 */
public class GitLabAPIBuilds {

    private final GitLabAPI gitLabAPI;

    public GitLabAPIBuilds(GitLabAPI gitLabAPI) {
        super();
        this.gitLabAPI = gitLabAPI;
    }

    /**
     * List project builds
     * <p>
     * Get a list of builds in a project.
     * <p>
     * GET /projects/:id/builds
     *
     * @param projectId  (required) - The ID of a project
     * @param pagination (optional) - number of projects to return per page
     * @param scopes     (optional) - he scope of builds to show, one or array of: pending, running, failed, success, canceled; showing all builds if none provided
     * @return builds
     * @throws IOException
     */
    public Paged<GitLabBuild> getAllBuilds(Serializable projectId, Pagination pagination, String... scopes) throws IOException {
        Query query;
        if (pagination != null) {
            query = pagination.asQuery();
        } else {
            query = Query.newQuery();
        }

        if (scopes != null && scopes.length > 0) {
            StringJoiner stringJoiner = new StringJoiner(",");
            Arrays.stream(scopes).forEach(stringJoiner::add);
            query.append("scope", stringJoiner.toString()).build();
        }
        String tailUrl = String.format("/projects/%s/builds%s", gitLabAPI.sanitize(projectId), query.build());
        return gitLabAPI.retrieve().toPaged(tailUrl, GitLabBuild[].class);
    }

    /**
     * Get build artifacts
     * <p>
     * Get build artifacts of a project
     * <p>
     * GET /projects/:id/builds/:build_id/artifacts
     *
     * @param projectId (required) - The ID of a project
     * @param buildId   (required) - The ID of a build
     * @return file
     * @throws IOException
     */
    public byte[] getBuildArtifacts(Serializable projectId, Integer buildId) throws IOException {
        String tailUrl = String.format("/projects/%s/builds/%s/artifacts", gitLabAPI.sanitize(projectId), buildId);
        return gitLabAPI.retrieve().to(tailUrl, byte[].class);
    }
}
