package com.skennedy.reddit.client.search.model;

public enum SubSortTimed {

    CONTROVERSIAL("controversial"),
    TOP("top");

    private final String path;

    SubSortTimed(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
