package com.talanlabs.gitlab.api.services;

import com.talanlabs.gitlab.api.GitLabAPI;
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
        String tailUrl = String.format("/user");
        return gitLabAPI.retrieve().to(tailUrl, GitLabUser.class);
    }
}
