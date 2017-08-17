package com.talanlabs.gitlab.api.http;

import com.talanlabs.gitlab.api.GitLabAPI;
import com.talanlabs.gitlab.api.Paged;

import java.io.IOException;
import java.util.List;

public class PagedImpl<T> implements Paged<T> {

    private final GitLabAPI gitLabAPI;
    private final Class<T[]> type;

    private final List<T> results;
    private final Integer total;
    private final Integer totalPage;
    private final Integer perPage;
    private final Integer page;
    private final Integer nextPage;
    private final Integer prevPage;

    private String firstPageUrl;
    private String nextPageUrl;
    private String prevPageUrl;
    private String lastPageUrl;

    public PagedImpl(GitLabAPI gitLabAPI, Class<T[]> type, List<T> results, Integer total, Integer totalPage, Integer perPage, Integer page, Integer nextPage, Integer prevPage, String link) {
        super();

        this.gitLabAPI = gitLabAPI;
        this.type = type;

        this.results = results;
        this.total = total;
        this.totalPage = totalPage;
        this.perPage = perPage;
        this.page = page;
        this.nextPage = nextPage;
        this.prevPage = prevPage;

        parseLink(link);
    }

    private void parseLink(String linkHeader) {
        if (linkHeader != null && linkHeader.length() > 0) {
            String[] links = linkHeader.split(",");
            for (String link : links) {
                String[] segments = link.split(";");
                if (segments.length < 2) {
                    continue;
                }
                String linkPart = segments[0].trim();
                if (!linkPart.startsWith("<") || !linkPart.endsWith(">")) {
                    continue;
                }
                linkPart = linkPart.substring(1, linkPart.length() - 1);

                for (int i = 1; i < segments.length; i++) {
                    String[] rel = segments[i].trim().split("="); //$NON-NLS-1$
                    if (rel.length < 2 || !"rel".equals(rel[0])) {
                        continue;
                    }

                    String relValue = rel[1];
                    if (relValue.startsWith("\"") && relValue.endsWith("\"")) //$NON-NLS-1$ //$NON-NLS-2$
                        relValue = relValue.substring(1, relValue.length() - 1);

                    if ("first".equals(relValue)) {
                        firstPageUrl = linkPart;
                    } else if ("last".equals(relValue)) {
                        lastPageUrl = linkPart;
                    } else if ("next".equals(relValue)) {
                        nextPageUrl = linkPart;
                    } else if ("prev".equals(relValue)) {
                        prevPageUrl = linkPart;
                    }
                }
            }
        }
    }

    @Override
    public List<T> getResults() {
        return results;
    }

    @Override
    public Integer getTotal() {
        return total;
    }

    @Override
    public Integer getTotalPage() {
        return totalPage;
    }

    @Override
    public Integer getPerPage() {
        return perPage;
    }

    @Override
    public Integer getPage() {
        return page;
    }

    @Override
    public Integer getNextPage() {
        return nextPage;
    }

    @Override
    public Integer getPrevPage() {
        return prevPage;
    }

    @Override
    public Paged<T> firstPage() throws IOException {
        if (firstPageUrl == null) {
            return null;
        }
        return gitLabAPI.retrieve().toPaged(gitLabAPI.removeAPIUrl(firstPageUrl), type);
    }

    @Override
    public Paged<T> nextPage() throws IOException {
        if (nextPageUrl == null || results.isEmpty()) {
            return null;
        }
        return gitLabAPI.retrieve().toPaged(gitLabAPI.removeAPIUrl(nextPageUrl), type);
    }

    @Override
    public Paged<T> prevPage() throws IOException {
        if (prevPageUrl == null) {
            return null;
        }
        return gitLabAPI.retrieve().toPaged(gitLabAPI.removeAPIUrl(prevPageUrl), type);
    }

    @Override
    public Paged<T> lastPage() throws IOException {
        if (lastPageUrl == null) {
            return null;
        }
        return gitLabAPI.retrieve().toPaged(gitLabAPI.removeAPIUrl(lastPageUrl), type);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PagedImpl{");
        sb.append("gitLabAPI=").append(gitLabAPI);
        sb.append(", type=").append(type);
        sb.append(", results=").append(results);
        sb.append(", total=").append(total);
        sb.append(", totalPage=").append(totalPage);
        sb.append(", perPage=").append(perPage);
        sb.append(", page=").append(page);
        sb.append(", nextPage=").append(nextPage);
        sb.append(", prevPage=").append(prevPage);
        sb.append(", firstPageUrl='").append(firstPageUrl).append('\'');
        sb.append(", nextPageUrl='").append(nextPageUrl).append('\'');
        sb.append(", prevPageUrl='").append(prevPageUrl).append('\'');
        sb.append(", lastPageUrl='").append(lastPageUrl).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
