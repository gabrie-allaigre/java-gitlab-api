package com.talanlabs.gitlab.api.services;

import com.talanlabs.gitlab.api.GitLabAPI;
import com.talanlabs.gitlab.api.Paged;
import com.talanlabs.gitlab.api.Pagination;
import com.talanlabs.gitlab.api.http.Query;
import com.talanlabs.gitlab.api.models.users.GitLabUser;

import java.io.IOException;

/**
 * Users API
 * <p>
 * http://doc.gitlab.com/ce/api/users.html
 */
public class GitLabAPIUsers {

    private final GitLabAPI gitLabAPI;

    public GitLabAPIUsers(GitLabAPI gitLabAPI) {
        super();
        this.gitLabAPI = gitLabAPI;
    }

    /**
     * Current user
     * <p>
     * Gets currently authenticated user.
     * <p>
     * GET /user
     *
     * @return user
     * @throws IOException
     */
    public GitLabUser getUser() throws IOException {
        String tailUrl = "/user";
        return gitLabAPI.retrieve().to(tailUrl, GitLabUser.class);
    }

    /**
     * Search users by email or username
     *
     * @param emailOrUsername
     * @param pagination      number of projects to return per page
     *                        <p>
     *                        GET /users?search=
     * @return
     * @throws IOException
     */
    public Paged<GitLabUser> getUsers(String emailOrUsername, Pagination pagination) throws IOException {
        Query q;
        if (pagination != null) {
            q = pagination.asQuery();
        } else {
            q = Query.newQuery();
        }
        String parameters = q.appendIf("search", emailOrUsername).build();
        String tailUrl = "/users" + parameters;
        return gitLabAPI.retrieve().toPaged(tailUrl, GitLabUser[].class);
    }
}
