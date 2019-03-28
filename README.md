Java Gitlab API
===============

Fork de https://github.com/timols/java-gitlab-api

# Usage



``` xml
<repositories>
    <repository>
      <id>jitpack.io</id>
      <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.gabrie-allaigre</groupId>
    <artifactId>java-gitlab-api</artifactId>
    <version>1.5.0</version>
</dependency>
```

``` java
GitLabAPI gitLabAPI = GitLabAPI.connect(GITLAB_URL, USER_TOKEN);

List<GitLabCommitDiff> diffs = gitLabAPI.getGitLabAPICommits().getCommitDiffs(PROJECT_ID, COMMIT_SHA);
```
