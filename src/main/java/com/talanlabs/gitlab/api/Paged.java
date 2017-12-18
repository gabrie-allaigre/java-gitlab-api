package com.talanlabs.gitlab.api;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public interface Paged<T> extends Iterable {

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

    @Override
    default Iterator<Paged<T>> iterator() {
        return new Iterator<Paged<T>>() {
            @Override
            public boolean hasNext() {
                return getPage().equals(getTotalPage());
            }

            @Override
            public Paged<T> next() {
                try {
                    return nextPage();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

}
