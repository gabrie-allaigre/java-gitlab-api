package com.talanlabs.gitlab.api;

import com.talanlabs.gitlab.api.http.Query;

import java.io.UnsupportedEncodingException;

public class Pagination {

    public static final String PARAM_PAGE = "page";
    public static final String PARAM_PER_PAGE = "per_page";
    public static final int MAX_ITEMS_PER_PAGE = 100;

    private Integer page;
    private Integer perPage;

    public Pagination() {
        super();
    }

    public static Pagination create(Integer page, Integer perPage) {
        return new Pagination().page(page).perPage(perPage);
    }

    public Pagination page(Integer page) {
        this.page = page;
        return this;
    }

    public Pagination perPage(Integer perPage) {
        if (perPage > MAX_ITEMS_PER_PAGE) {
            throw new IllegalArgumentException("Max value for perPage is " + MAX_ITEMS_PER_PAGE);
        }
        this.perPage = perPage;
        return this;
    }

    public Query asQuery() throws UnsupportedEncodingException {
        return Query.newQuery().appendIf(PARAM_PER_PAGE, perPage).appendIf(PARAM_PAGE, page);
    }
}
