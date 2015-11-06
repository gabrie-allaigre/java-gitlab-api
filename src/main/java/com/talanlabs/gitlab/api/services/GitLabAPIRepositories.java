package com.talanlabs.gitlab.api.services;

import com.talanlabs.gitlab.api.GitLabAPI;
import com.talanlabs.gitlab.api.Paged;
import com.talanlabs.gitlab.api.Pagination;
import com.talanlabs.gitlab.api.http.Query;
import com.talanlabs.gitlab.api.models.repositories.GitLabRepositoryTree;

import java.io.IOException;
import java.io.Serializable;

/**
 * Repositories API
 * <p>
 * http://doc.gitlab.com/ce/api/repositories.html
 */
public class GitLabAPIRepositories {

    private final GitLabAPI gitLabAPI;

    public GitLabAPIRepositories(GitLabAPI gitLabAPI) {
        super();
        this.gitLabAPI = gitLabAPI;
    }

    /**
     * List repository tree
     * <p>
     * Get a list of repository files and directories in a project.
     * <p>
     * GET /projects/:id/repository/tree
     * Parameters:
     *
     * @param projectId  (required) - The ID of a project
     * @param pagination (optional) - number of projects to return per page
     * @param path       (optional) - The path inside repository. Used to get contend of subdirectories
     * @param refName    (optional) - The name of a repository branch or tag or if not given the default branch
     * @return list of repository tree
     * @throws IOException
     */
    public Paged<GitLabRepositoryTree> getRepositoryTrees(Serializable projectId, Pagination pagination, String path, String refName) throws IOException {
        Query q;
        if (pagination != null) {
            q = pagination.asQuery();
        } else {
            q = Query.newQuery();
        }
        String parameters = q.appendIf("refName", refName).appendIf("path", path).build();
        String tailUrl = String.format("/projects/%s/repository/tree%s", gitLabAPI.sanitize(projectId), parameters);
        return gitLabAPI.retrieve().toPaged(tailUrl, GitLabRepositoryTree[].class);
    }

    /**
     * Raw file content
     * <p>
     * Get the raw file contents for a file by commit SHA and path.
     * <p>
     * GET /projects/:id/repository/blobs/:sha
     *
     * @param projectId (required) - The ID of a project
     * @param sha       (required) - The commit or branch name
     * @param filepath  (required) - The path the file
     * @return file
     */
    public byte[] getRawFileContent(Serializable projectId, String sha, String filepath) throws IOException {
        String parameters = Query.newQuery().append("filepath", filepath).build();
        String tailUrl = String.format("/projects/%s/repository/blobs/%s%s", gitLabAPI.sanitize(projectId), sha, parameters);
        return gitLabAPI.retrieve().to(tailUrl, byte[].class);
    }
}
