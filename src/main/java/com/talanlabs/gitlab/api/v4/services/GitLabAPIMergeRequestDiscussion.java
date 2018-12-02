package com.talanlabs.gitlab.api.v4.services;

import com.talanlabs.gitlab.api.Paged;
import com.talanlabs.gitlab.api.v4.GitLabAPI;
import com.talanlabs.gitlab.api.v4.Pagination;
import com.talanlabs.gitlab.api.v4.http.Query;
import com.talanlabs.gitlab.api.v4.models.GitlabPosition;
import com.talanlabs.gitlab.api.v4.models.discussion.GitlabDiscussion;
import com.talanlabs.gitlab.api.v4.models.discussion.GitlabDiscussionStatus;
import com.talanlabs.gitlab.api.v4.utils.QueryHelper;

import java.io.IOException;
import java.io.Serializable;

public class GitLabAPIMergeRequestDiscussion {

    private static final String BASE_URL = "/projects/%s/merge_requests/%d/discussions";

    private final GitLabAPI gitLabAPI;

    public GitLabAPIMergeRequestDiscussion(GitLabAPI gitLabAPI) {
        super();
        this.gitLabAPI = gitLabAPI;
    }

    /**
     * Gets a list of all discussions for a single merge request.
     * <p>
     * GET /projects/:id/merge_requests/:merge_request_iid/discussions
     *
     * @param projectId  (required) - The ID or URL-encoded path of the project
     * @param iid        (required) - The IID of a merge request
     * @param pagination (optional) - The ID of a discussion
     * @return Paged object of {@link GitlabDiscussionStatus} instances
     * @throws IOException
     */
    public Paged<GitlabDiscussionStatus> getAllDiscussions(Serializable projectId, Integer iid, Pagination pagination) throws IOException {
        Query query = QueryHelper.getQuery(pagination);
        String tailUrl = String.format(BASE_URL + "%s", gitLabAPI.sanitize(projectId), iid, query.build());
        return gitLabAPI.retrieve().toPaged(tailUrl, GitlabDiscussionStatus[].class);
    }

    /**
     * Returns a single discussion for a specific project merge request
     * <p>
     * GET /projects/:id/merge_requests/:merge_request_iid/discussions/:discussion_id
     *
     * @param projectId    (required) - The ID or URL-encoded path of the project
     * @param iid          (required) - The IID of a merge request
     * @param discussionId (required) - The ID of a discussion
     * @return @return Instance of {@link GitlabDiscussionStatus}
     * @throws IOException
     */
    public GitlabDiscussionStatus getDiscussion(Serializable projectId, Integer iid, Integer discussionId) throws IOException {
        String tailUrl = String.format(BASE_URL + "/%d", gitLabAPI.sanitize(projectId), iid, discussionId);
        return gitLabAPI.retrieve().to(tailUrl, GitlabDiscussionStatus.class);
    }

    /**
     * Creates a new discussion to a single project merge request. This is similar to creating a note but other comments (replies) can be added to it later.
     * <p>
     * POST /projects/:id/merge_requests/:merge_request_iid/discussions
     *
     * @param projectId  (required) - The ID or URL-encoded path of the project
     * @param iid        (required) - The IID of a merge request
     * @param discussion (required) - Discussion content
     * @return @return Instance of {@link GitlabDiscussionStatus}
     * @throws IOException
     */
    public GitlabDiscussionStatus createDiscussion(Serializable projectId, Integer iid, GitlabDiscussion discussion) throws IOException {
        GitlabPosition position = discussion.getPosition();
        String parameters = Query.newQuery()
                .append("body", discussion.getBody())
                .append("position[base_sha]", position.getBaseSha())
                .append("position[start_sha]", position.getStartSha())
                .append("position[head_sha]", position.getHeadSha())
                .append("position[position_type]", position.getPositionType().getValue())
                .append("position[new_line]", position.getNewLine())
                .append("position[old_path]", position.getOldPath())
                .append("position[new_path]", position.getNewPath())
                .build();

        String tailUrl = String.format(BASE_URL + "%s", gitLabAPI.sanitize(projectId), iid, parameters);

        return gitLabAPI.dispatch().to(tailUrl, GitlabDiscussionStatus.class);
    }

    /**
     * Resolve a merge request discussion
     *
     * @param projectId       (required) - The ID or URL-encoded path of the project
     * @param mergeRequestIid (required) - The IID of a merge request
     * @param discussionId    (required) - The ID of a discussion
     * @return @return Instance of {@link GitlabDiscussionStatus}
     * @throws IOException
     */
    public GitlabDiscussionStatus resolveMergeRequestDiscussions(Serializable projectId, Integer mergeRequestIid, Integer discussionId) throws IOException {
        return solveMergeRequestDiscussions(projectId, mergeRequestIid, discussionId, true);
    }

    /**
     * Unresolve a merge request discussion
     *
     * @param projectId       (required) - The ID or URL-encoded path of the project
     * @param mergeRequestIid (required) - The IID of a merge request
     * @param discussionId    (required) - The ID of a discussion
     * @return @return Instance of {@link GitlabDiscussionStatus}
     * @throws IOException
     */
    public GitlabDiscussionStatus unresolveMergeRequestDiscussions(Serializable projectId, Integer mergeRequestIid, Integer discussionId) throws IOException {
        return solveMergeRequestDiscussions(projectId, mergeRequestIid, discussionId, false);
    }

    /**
     * Change resolve status of a merge request discussion
     * <p>
     * PUT /projects/:id/merge_requests/:merge_request_iid/discussions/:discussion_id
     *
     * @param projectId       (required) - The ID or URL-encoded path of the project
     * @param mergeRequestIid (required) - The IID of a merge request
     * @param discussionId    (required) - The ID of a discussion
     * @param resolved        (required) - Resolve/unresolve the discussion
     * @return Instance of {@link GitlabDiscussionStatus}
     * @throws IOException
     */
    private GitlabDiscussionStatus solveMergeRequestDiscussions(Serializable projectId, Integer mergeRequestIid, Integer discussionId, boolean resolved) throws IOException {
        String query = Query.newQuery().append("resolved", resolved).build();
        String tailUrl = String.format(BASE_URL + "/%d" + "%s", gitLabAPI.sanitize(projectId), mergeRequestIid, discussionId, query);
        return gitLabAPI.update().to(tailUrl, GitlabDiscussionStatus.class);
    }

}
