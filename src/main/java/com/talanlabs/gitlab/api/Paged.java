package com.talanlabs.gitlab.api;

import java.io.IOException;
import java.util.List;

public interface Paged<T> {

    List<T> getResults();

    Integer getTotal();

    Integer getTotalPage();

    Integer getPerPage();

    Integer getPage();

    Integer getNextPage();

    Integer getPrevPage();

    Paged<T> firstPage() throws IOException;

    Paged<T> nextPage() throws IOException;

    Paged<T> prevPage() throws IOException;

    Paged<T> lastPage() throws IOException;

}
