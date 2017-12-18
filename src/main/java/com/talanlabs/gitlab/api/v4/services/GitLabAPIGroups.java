package com.talanlabs.gitlab.api.v4.services;

import com.talanlabs.gitlab.api.Paged;
import com.talanlabs.gitlab.api.v4.GitLabAPI;
import com.talanlabs.gitlab.api.v4.models.GitlabGroup;
import java.io.IOException;

/**
 * Groups API
 * <p>
 * http://doc.gitlab.com/ce/api/groups.html
 */
public class GitLabAPIGroups {

    private final GitLabAPI gitLabAPI;

    public GitLabAPIGroups(GitLabAPI gitLabAPI) {
        super();
        this.gitLabAPI = gitLabAPI;
    }

    /**
     * List groups
     * <p>
     * Get authenticated user's groups.
     * <p>
     * GET /groups
     *
     * @return
     * @throws IOException
     */
    public Paged<GitlabGroup> getMyGroups() throws IOException {
        return gitLabAPI.retrieve().toPaged("/groups", GitlabGroup[].class);
    }
}
