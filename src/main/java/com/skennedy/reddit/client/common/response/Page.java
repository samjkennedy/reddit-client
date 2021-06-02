package com.skennedy.reddit.client.common.response;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Stream;

public class Page<T> implements Iterable<T> {

    private final Collection<T> results;
    private String before;
    private String after;

    public Page(String before, String after, T... results) {
        this(before, after, Arrays.asList(results));
    }

    public Page(String before, String after, Collection<T> results) {
        this.results = CollectionUtils.emptyIfNull(results);
        this.before = before;
        this.after = after;
    }

    public boolean isEmpty() {
        return results.isEmpty();
    }

    public int size() {
        return results.size();
    }

    public String getBefore() {
        return before;
    }

    public String getAfter() {
        return after;
    }

    @Override
    public Iterator<T> iterator() {
        return results.iterator();
    }

    public Stream<T> stream() {
        return results.stream();
    }
}
