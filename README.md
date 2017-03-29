Java Gitlab API
===============

Fork de https://github.com/timols/java-gitlab-api

# Usage

``` xml
<dependency>
   <groupId>com.talanlabs</groupId>
   <artifactId>java-gitlab-api</artifactId>
   <version>1.2.0</version>
</dependency>
```

``` java
GitLabAPI gitLabAPI = GitLabAPI.connect(GITLAB_URL, USER_TOKEN);

List<GitLabCommitDiff> diffs = gitLabAPI.getGitLabAPICommits().getCommitDiffs(PROJECT_ID, COMMIT_SHA);
```