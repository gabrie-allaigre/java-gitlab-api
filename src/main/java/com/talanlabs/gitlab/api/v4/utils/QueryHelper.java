package com.talanlabs.gitlab.api.v4.utils;

import com.talanlabs.gitlab.api.v4.Pagination;
import com.talanlabs.gitlab.api.v4.http.Query;

import java.io.UnsupportedEncodingException;

public class QueryHelper {

    private QueryHelper() {
        throw new AssertionError("Do not instantiate!");
    }

    public static Query getQuery(Pagination pagination) throws UnsupportedEncodingException {
        return pagination != null ? pagination.asQuery() : Query.newQuery();
    }
}
