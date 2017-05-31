package com.talanlabs.gitlab.api.v4.services;

import com.talanlabs.gitlab.api.v4.GitLabAPI;
import com.talanlabs.gitlab.api.v4.Paged;
import com.talanlabs.gitlab.api.v4.Pagination;
import com.talanlabs.gitlab.api.v4.http.Query;
import com.talanlabs.gitlab.api.v4.models.projects.GitLabProject;

import java.io.IOException;
import java.io.Serializable;

/**
 * Commits API
 * <p>
 * http://doc.gitlab.com/ce/api/commits.html
 */
public class GitLabAPIProjects {

    private final GitLabAPI gitLabAPI;

    public GitLabAPIProjects(GitLabAPI gitLabAPI) {
        super();
        this.gitLabAPI = gitLabAPI;
    }

    /**
     * List projects
     * <p>
     * Get a list of projects accessible by the authenticated user.
     * <p>
     * GET /projects
     *
     * @param pagination     (optional) - number of projects to return per page
     * @param archived       (optional) - if passed, limit by archived status
     * @param orderBy        (optional) - Return requests ordered by id, name, path, created_at, updated_at or last_activity_at fields. Default is created_at
     * @param sort           (optional) - Return requests sorted in asc or desc order. Default is desc
     * @param search         (optional) - Return list of authorized projects according to a search criteria
     * @param ciEnabledFirst (optional) - Return projects ordered by ci_enabled flag. Projects with enabled GitLab CI go first
     * @return
     * @throws IOException
     */
    public Paged<GitLabProject> getProjects(Pagination pagination, Boolean archived, String orderBy, String sort, String search, Boolean ciEnabledFirst) throws IOException {
        return getProjects(null, pagination, archived, orderBy, sort, search, ciEnabledFirst);
    }

    /**
     * List owned projects
     * <p>
     * Get a list of projects which are owned by the authenticated user.
     * <p>
     * GET /projects/owned
     *
     * @param pagination     (optional) - number of projects to return per page
     * @param archived       (optional) - if passed, limit by archived status
     * @param orderBy        (optional) - Return requests ordered by id, name, path, created_at, updated_at or last_activity_at fields. Default is created_at
     * @param sort           (optional) - Return requests sorted in asc or desc order. Default is desc
     * @param search         (optional) - Return list of authorized projects according to a search criteria
     * @param ciEnabledFirst (optional) - Return projects ordered by ci_enabled flag. Projects with enabled GitLab CI go first
     * @return
     * @throws IOException
     */
    public Paged<GitLabProject> getProjectOwneds(Pagination pagination, Boolean archived, String orderBy, String sort, String search, Boolean ciEnabledFirst) throws IOException {
        return getProjects("/owned", pagination, archived, orderBy, sort, search, ciEnabledFirst);
    }

    /**
     * List ALL projects
     * <p>
     * Get a list of all GitLab projects (admin only).
     * <p>
     * GET /projects/all
     *
     * @param pagination     (optional) - number of projects to return per page
     * @param archived       (optional) - if passed, limit by archived status
     * @param orderBy        (optional) - Return requests ordered by id, name, path, created_at, updated_at or last_activity_at fields. Default is created_at
     * @param sort           (optional) - Return requests sorted in asc or desc order. Default is desc
     * @param search         (optional) - Return list of authorized projects according to a search criteria
     * @param ciEnabledFirst (optional) - Return projects ordered by ci_enabled flag. Projects with enabled GitLab CI go first
     * @return
     * @throws IOException
     */
    public Paged<GitLabProject> getProjectAlls(Pagination pagination, Boolean archived, String orderBy, String sort, String search, Boolean ciEnabledFirst) throws IOException {
        return getProjects("/all", pagination, archived, orderBy, sort, search, ciEnabledFirst);
    }

    private Paged<GitLabProject> getProjects(String prefix, Pagination pagination, Boolean archived, String orderBy, String sort, String search, Boolean ciEnabledFirst) throws IOException {
        Query q;
        if (pagination != null) {
            q = pagination.asQuery();
        } else {
            q = Query.newQuery();
        }
        String parameters = q.appendIf("archived", archived).appendIf("order_by", orderBy).appendIf("sort", sort).appendIf("search", gitLabAPI.sanitize(search))
                .appendIf("ciEnabledFirst", ciEnabledFirst).build();

        String tailUrl = "/projects";
        if (prefix != null) {
            tailUrl += prefix;
        }
        tailUrl += parameters;
        return gitLabAPI.retrieve().toPaged(tailUrl, GitLabProject[].class);
    }

    /**
     * Get single project
     * <p>
     * Get a specific project, identified by project ID or NAMESPACE/PROJECT_NAME, which is owned by the authenticated user. If using namespaced projects call make sure that the NAMESPACE/PROJECT_NAME is URL-encoded, eg. /api/v3/projects/diaspora%2Fdiaspora (where / is represented by %2F).
     * <p>
     * GET /projects/:id
     *
     * @param projectId (required) - The ID of a project
     * @return
     * @throws IOException
     */
    public GitLabProject getProject(Serializable projectId) throws IOException {
        String tailUrl = String.format("/projects/%s", gitLabAPI.sanitize(projectId));
        return gitLabAPI.retrieve().to(tailUrl, GitLabProject.class);
    }

    /**
     * Search for projects by name
     * <p>
     * Search for projects by name which are accessible to the authenticated user.
     * <p>
     * GET /projects/search/:query
     *
     * @param query      (required) - A string contained in the project name
     * @param pagination (optional) - number of projects to return per page
     * @param orderBy    (optional) - Return requests ordered by id, name, created_at or last_activity_at fields
     * @param sort       (optional) - Return requests sorted in asc or desc order
     * @return
     * @throws IOException
     */
    public Paged<GitLabProject> getProjectsSearchByName(String query, Pagination pagination, String orderBy, String sort) throws IOException {
        Query q;
        if (pagination != null) {
            q = pagination.asQuery();
        } else {
            q = Query.newQuery();
        }
        String parameters = q.appendIf("orderBy", orderBy).appendIf("sort", sort).build();
        String tailUrl = String.format("/projects/search/%s%s", gitLabAPI.sanitize(query), parameters);
        return gitLabAPI.retrieve().toPaged(tailUrl, GitLabProject[].class);
    }
}
