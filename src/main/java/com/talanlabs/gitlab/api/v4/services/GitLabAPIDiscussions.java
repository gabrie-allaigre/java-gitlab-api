package com.talanlabs.gitlab.api.v4.services;

import com.talanlabs.gitlab.api.Paged;
import com.talanlabs.gitlab.api.v4.GitLabAPI;
import com.talanlabs.gitlab.api.v4.Pagination;
import com.talanlabs.gitlab.api.v4.http.Query;
import com.talanlabs.gitlab.api.v4.models.GitlabDiscussion;
import com.talanlabs.gitlab.api.v4.models.GitlabNote;

import java.io.IOException;
import java.io.Serializable;

/**
 * Discussions API
 * <p>
 * http://doc.gitlab.com/ce/api/discussions.html
 */
public class GitLabAPIDiscussions {

    private final GitLabAPI gitLabAPI;

    public GitLabAPIDiscussions(GitLabAPI gitLabAPI) {
        super();
        this.gitLabAPI = gitLabAPI;
    }

    /**
     * Gets a list of all discussions for a single merge request.
     *
     * @param projectId        (required) - The ID or URL-encoded path of the project
     * @param mergeRequestsIid (required) - The IID of a merge request
     * @param pagination       (optional) - number of projects to return per page
     * @return discussions
     * @throws IOException
     */
    public Paged<GitlabDiscussion> getAllProjectMergeRequestsDiscussions(Serializable projectId, Integer mergeRequestsIid, Pagination pagination) throws IOException {
        Query query;
        if (pagination != null) {
            query = pagination.asQuery();
        } else {
            query = Query.newQuery();
        }
        String tailUrl = String.format("/projects/%s/merge_requests/%s/discussions", gitLabAPI.sanitize(projectId), mergeRequestsIid, query.build());
        return gitLabAPI.retrieve().toPaged(tailUrl, GitlabDiscussion[].class);
    }

    /**
     * Creates a new discussion to a single project merge request. This is similar to creating a note but but another comments (replies) can be added to it later.
     *
     * @param projectId        (required) - The ID of a project
     * @param mergeRequestsIid (required) - The IID of a merge request
     * @param body             (required) - The content of a discussion
     * @param position         (optional) - Position when creating a diff note
     * @param positionBaseSha  (required) - Base commit SHA in the source branch
     * @param positionStartSha (required) - SHA referencing commit in target branch
     * @param positionHeadSha  (required) - SHA referencing HEAD of this merge request
     * @param positionType     (required) - Type of the position reference', allowed values: 'text' or 'image'
     * @param positionNewPath  (optional) - File path after change
     * @param positionNewLine  (optional) - Line number after change (for 'text' diff notes)
     * @param positionOldPath  (optional) - File path before change
     * @param positionOldLine  (optional) - Line number before change (for 'text' diff notes)
     * @param positionWidth    (optional) - Width of the image (for 'image' diff notes)
     * @param positionHeight   (optional) - Height of the image (for 'image' diff notes)
     * @param positionX        (optional) - X coordinate (for 'image' diff notes)
     * @param positionY        (optional) - Y coordinate (for 'image' diff notes)
     * @return new discussion
     * @throws IOException
     */
    public GitlabDiscussion postProjectMergeRequestsDiscussions(Serializable projectId, Integer mergeRequestsIid, String body, String position, String positionBaseSha, String positionStartSha,
            String positionHeadSha, String positionType, String positionNewPath, Integer positionNewLine, String positionOldPath, Integer positionOldLine, Integer positionWidth,
            Integer positionHeight, Integer positionX, Integer positionY) throws IOException {
        String tailUrl = String.format("/projects/%s/merge_requests/%s/discussions", gitLabAPI.sanitize(projectId), mergeRequestsIid);
        return gitLabAPI.dispatch().with("body", body).with("position", position).with("position[base_sha]", positionBaseSha).with("position[start_sha]", positionStartSha)
                .with("position[head_sha]", positionHeadSha).with("position[position_type]", positionType).with("position[new_path]", positionNewPath).with("position[new_line]", positionNewLine)
                .with("position[old_path]", positionOldPath).with("position[old_line]", positionOldLine).with("position[width]", positionWidth).with("position[height]", positionHeight)
                .with("position[x]", positionX).with("position[y]", positionY).to(tailUrl, GitlabDiscussion.class);
    }

    /**
     * Resolve/unresolve whole discussion of a merge request.
     *
     * @param projectId        (required) - The ID of a project
     * @param mergeRequestsIid (required) - The IID of a merge request
     * @param discussionId     (required) - The ID of a discussion
     * @param resolved         (required) - Resolve/unresolve the discussion
     * @return discussion
     * @throws IOException
     */
    public GitlabDiscussion resolveProjectMergeRequestsDiscussions(Serializable projectId, Integer mergeRequestsIid, String discussionId, boolean resolved) throws IOException {
        String tailUrl = String.format("/projects/%s/merge_requests/%s/discussions/%s", gitLabAPI.sanitize(projectId), mergeRequestsIid, discussionId);
        return gitLabAPI.retrieve().method("PUT").with("resolved", resolved).to(tailUrl, GitlabDiscussion.class);
    }

    /**
     * Adds a new note to the discussion.
     *
     * @param projectId        (required) - The ID of a project
     * @param mergeRequestsIid (required) - The IID of a merge request
     * @param discussionId     (required) - The ID of a discussion
     * @param body             (required) - The content of a discussion
     * @return note
     * @throws IOException
     */
    public GitlabNote postProjectMergeRequestsDiscussionsNote(Serializable projectId, Integer mergeRequestsIid, String discussionId, String body) throws IOException {
        String tailUrl = String.format("/projects/%s/merge_requests/%s/discussions/%s/notes", gitLabAPI.sanitize(projectId), mergeRequestsIid, discussionId);
        return gitLabAPI.dispatch().with("body", body).to(tailUrl, GitlabNote.class);
    }

    /**
     * Modify or resolve an existing discussion note of a merge request.
     *
     * @param projectId        (required) - The ID of a project
     * @param mergeRequestsIid (required) - The IID of a merge request
     * @param discussionId     (required) - The ID of a discussion
     * @param noteId           (required) - The ID of discussion note
     * @param body             (optional) - The content of a discussion (exactly one of body or resolved must be set
     * @param resolved         (optional) - Resolve/unresolve the note (exactly one of body or resolved must be set
     * @return note
     * @throws IOException
     */
    public GitlabNote modifyProjectMergeRequestsDiscussionsNote(Serializable projectId, Integer mergeRequestsIid, String discussionId, Integer noteId, String body, Boolean resolved)
            throws IOException {
        String tailUrl = String.format("/projects/%s/merge_requests/%s/discussions/%s/notes/%s", gitLabAPI.sanitize(projectId), mergeRequestsIid, discussionId, noteId);
        return gitLabAPI.retrieve().method("PUT").with("body", body).with("resolved", resolved).to(tailUrl, GitlabNote.class);
    }

    /**
     * Deletes an existing discussion note of a merge request.
     *
     * @param projectId        (required) - The ID of a project
     * @param mergeRequestsIid (required) - The IID of a merge request
     * @param discussionId     (required) - The ID of a discussion
     * @param noteId           (required) - The ID of discussion note
     * @throws IOException
     */
    public void deleteProjectMergeRequestsDiscussionsNote(Serializable projectId, Integer mergeRequestsIid, String discussionId, Integer noteId) throws IOException {
        String tailUrl = String.format("/projects/%s/merge_requests/%s/discussions/%s/notes/%s", gitLabAPI.sanitize(projectId), mergeRequestsIid, discussionId, noteId);
        gitLabAPI.retrieve().method("DELETE").to(tailUrl, Void.class);
    }
}
