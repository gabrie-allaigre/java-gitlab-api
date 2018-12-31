package com.talanlabs.gitlab.api.v4.models.discussion;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.talanlabs.gitlab.api.v4.models.GitlabNote;

import java.util.List;

public class GitlabDiscussionStatus {

    private Integer id;

    @JsonProperty("individual_note")
    private boolean individualNote;

    private List<GitlabNote> notes;
}
