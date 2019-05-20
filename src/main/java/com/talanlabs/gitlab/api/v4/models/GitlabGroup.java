package com.talanlabs.gitlab.api.v4.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.talanlabs.gitlab.api.v3.models.GitlabAccessLevel;

public class GitlabGroup {

    public static final String URL = "/groups";

    private Integer id;
    private String name;
    private String path;

    @JsonProperty("full_path")
    private String fullPath;

    @JsonProperty("ldap_cn")
    private String ldapCn;

    @JsonProperty("ldap_access")
    private Integer ldapAccess;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public String getLdapCn() {
        return ldapCn;
    }

    public void setLdapCn(String ldapCn) {
        this.ldapCn = ldapCn;
    }

    public com.talanlabs.gitlab.api.v3.models.GitlabAccessLevel getLdapAccess() {
        return ldapAccess != null ? com.talanlabs.gitlab.api.v3.models.GitlabAccessLevel.fromAccessValue(ldapAccess) : null;
    }

    public void setLdapAccess(GitlabAccessLevel ldapGitlabAccessLevel) {
        this.ldapAccess = ldapGitlabAccessLevel != null ? ldapGitlabAccessLevel.accessValue : null;
    }
}
