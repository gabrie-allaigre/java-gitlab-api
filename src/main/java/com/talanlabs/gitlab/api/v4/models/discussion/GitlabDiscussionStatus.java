package com.talanlabs.gitlab.api.v4.models.discussion;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.talanlabs.gitlab.api.v4.models.GitlabNote;

import java.util.List;

public class GitlabDiscussionStatus {

    private String id;

    @JsonProperty("individual_note")
    private boolean individualNote;

    private List<GitlabNote> notes;

    public String getId() {
        return id;
    }

    public boolean getIndividualNote() {
        return individualNote;
    }

    public List<GitlabNote> getNotes() {
        return notes;
    }
}
