package com.talanlabs.gitlab.api.v4;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.talanlabs.gitlab.api.v4.http.GitLabHTTPRequestor;
import com.talanlabs.gitlab.api.v4.services.GitLabAPIBranches;
import com.talanlabs.gitlab.api.v4.services.GitLabAPIBuildVariables;
import com.talanlabs.gitlab.api.v4.services.GitLabAPIBuilds;
import com.talanlabs.gitlab.api.v4.services.GitLabAPICommits;
import com.talanlabs.gitlab.api.v4.services.GitLabAPIDiscussions;
import com.talanlabs.gitlab.api.v4.services.GitLabAPIGroups;
import com.talanlabs.gitlab.api.v4.services.GitLabAPIMergeRequests;
import com.talanlabs.gitlab.api.v4.services.GitLabAPIProjects;
import com.talanlabs.gitlab.api.v4.services.GitLabAPIRepositories;
import com.talanlabs.gitlab.api.v4.services.GitLabAPIUsers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Gitlab API Wrapper class
 *
 * @author &#064;timols (Tim O)
 */
public class GitLabAPI {

    public static final ObjectMapper MAPPER = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private static final String API_NAMESPACE = "/api/v4";

    private final String hostUrl;
    private final String apiToken;
    private final TokenType tokenType;
    private final AuthMethod authMethod;

    private final GitLabAPICommits gitLabAPICommits;
    private final GitLabAPIProjects gitLabAPIProjects;
    private final GitLabAPIUsers gitLabAPIUsers;
    private final GitLabAPIBuilds gitLabAPIBuilds;
    private final GitLabAPIRepositories gitLabAPIRepositories;
    private final GitLabAPIBuildVariables gitLabAPIBuildVariables;
    private final GitLabAPIGroups gitLabAPIGroups;
    private final GitLabAPIMergeRequests gitLabAPIMergeRequests;
    private final GitLabAPIDiscussions gitLabAPIDiscussions;
    private final GitLabAPIBranches gitLabAPIBranches;

    private boolean ignoreCertificateErrors = false;
    private Proxy proxy = null;
    private int requestTimeout = 0;

    private GitLabAPI(String hostUrl, String apiToken, TokenType tokenType, AuthMethod method) {
        this.hostUrl = hostUrl.endsWith("/") ? hostUrl.replaceAll("/$", "") : hostUrl;
        this.apiToken = apiToken;
        this.tokenType = tokenType;
        this.authMethod = method;

        this.gitLabAPICommits = new GitLabAPICommits(this);
        this.gitLabAPIProjects = new GitLabAPIProjects(this);
        this.gitLabAPIUsers = new GitLabAPIUsers(this);
        this.gitLabAPIBuilds = new GitLabAPIBuilds(this);
        this.gitLabAPIRepositories = new GitLabAPIRepositories(this);
        this.gitLabAPIBuildVariables = new GitLabAPIBuildVariables(this);
        this.gitLabAPIGroups = new GitLabAPIGroups(this);
        this.gitLabAPIMergeRequests = new GitLabAPIMergeRequests(this);
        this.gitLabAPIDiscussions = new GitLabAPIDiscussions(this);
        this.gitLabAPIBranches = new GitLabAPIBranches(this);
    }

    public static GitLabAPI connect(String hostUrl, String apiToken) {
        return new GitLabAPI(hostUrl, apiToken, TokenType.PRIVATE_TOKEN, AuthMethod.HEADER);
    }

    public static GitLabAPI connect(String hostUrl, String apiToken, TokenType tokenType) {
        return new GitLabAPI(hostUrl, apiToken, tokenType, AuthMethod.HEADER);
    }

    public static GitLabAPI connect(String hostUrl, String apiToken, TokenType tokenType, AuthMethod method) {
        return new GitLabAPI(hostUrl, apiToken, tokenType, method);
    }

    public boolean isIgnoreCertificateErrors() {
        return ignoreCertificateErrors;
    }

    public GitLabAPI setIgnoreCertificateErrors(boolean ignoreCertificateErrors) {
        this.ignoreCertificateErrors = ignoreCertificateErrors;
        return this;
    }

    public Proxy getProxy() {
        return proxy;
    }

    public GitLabAPI setProxy(Proxy proxy) {
        this.proxy = proxy;
        return this;
    }

    public int getRequestTimeout() {
        return requestTimeout;
    }

    public GitLabAPI setRequestTimeout(int requestTimeout) {
        this.requestTimeout = requestTimeout;
        return this;
    }

    public GitLabHTTPRequestor retrieve() {
        return new GitLabHTTPRequestor(this).authenticate(apiToken, tokenType, authMethod);
    }

    public GitLabHTTPRequestor dispatch() {
        return new GitLabHTTPRequestor(this).authenticate(apiToken, tokenType, authMethod).method("POST");
    }

    public URL getAPIUrl(String tailAPIUrl) throws IOException {
        if (!tailAPIUrl.startsWith("/")) {
            tailAPIUrl = "/" + tailAPIUrl;
        }
        return new URL(hostUrl + API_NAMESPACE + tailAPIUrl);
    }

    public URL getUrl(String tailAPIUrl) throws IOException {
        if (!tailAPIUrl.startsWith("/")) {
            tailAPIUrl = "/" + tailAPIUrl;
        }

        return new URL(hostUrl + tailAPIUrl);
    }

    public String removeAPIUrl(String url) {
        String withoutHost = url.substring(hostUrl.length());
        return withoutHost.substring(withoutHost.indexOf(API_NAMESPACE) + API_NAMESPACE.length());
    }

    public String sanitize(Object value) {
        if (value == null) {
            return null;
        }
        try {
            return URLEncoder.encode(String.valueOf(value), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException((e));
        }
    }

    public GitLabAPICommits getGitLabAPICommits() {
        return gitLabAPICommits;
    }

    public GitLabAPIProjects getGitLabAPIProjects() {
        return gitLabAPIProjects;
    }

    public GitLabAPIUsers getGitLabAPIUsers() {
        return gitLabAPIUsers;
    }

    public GitLabAPIBuilds getGitLabAPIBuilds() {
        return gitLabAPIBuilds;
    }

    public GitLabAPIRepositories getGitLabAPIRepositories() {
        return gitLabAPIRepositories;
    }

    public GitLabAPIBuildVariables getGitLabAPIBuildVariables() {
        return gitLabAPIBuildVariables;
    }

    public GitLabAPIGroups getGitLabAPIGroups() {
        return gitLabAPIGroups;
    }

    public GitLabAPIMergeRequests getGitLabAPIMergeRequests() {
        return gitLabAPIMergeRequests;
    }

    public GitLabAPIDiscussions getGitLabAPIDiscussions() {
        return gitLabAPIDiscussions;
    }

    public GitLabAPIBranches getGitLabAPIBranches() {
        return gitLabAPIBranches;
    }
}
