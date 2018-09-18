package com.talanlabs.gitlab.api.v4.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class GitlabDiscussion {

    public static final String URL = "/discussions";

    private String id;
    private List<GitlabNote> notes = new ArrayList<>();
    @JsonProperty("individual_note")
    private boolean individualNote;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<GitlabNote> getNotes() {
        return notes;
    }

    public void setNotes(List<GitlabNote> notes) {
        this.notes = notes;
    }

    public boolean isIndividualNote() {
        return individualNote;
    }

    public void setIndividualNote(boolean individualNote) {
        this.individualNote = individualNote;
    }

}
