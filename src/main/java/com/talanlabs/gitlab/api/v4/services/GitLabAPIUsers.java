package com.talanlabs.gitlab.api.v4.services;

import com.talanlabs.gitlab.api.Paged;
import com.talanlabs.gitlab.api.v4.GitLabAPI;
import com.talanlabs.gitlab.api.v4.Pagination;
import com.talanlabs.gitlab.api.v4.http.Query;
import com.talanlabs.gitlab.api.v4.models.users.GitLabUser;
import com.talanlabs.gitlab.api.v4.utils.QueryHelper;

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
        Query query = QueryHelper.getQuery(pagination);
        String parameters = query.appendIf("search", emailOrUsername).build();
        String tailUrl = "/users" + parameters;
        return gitLabAPI.retrieve().toPaged(tailUrl, GitLabUser[].class);
    }
}
