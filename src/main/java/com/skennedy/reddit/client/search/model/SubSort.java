package com.skennedy.reddit.client.search.model;

public enum SubSort {
    HOT("hot"),
    NEW("new"),
    RANDOM("random"),
    RISING("rising");

    private final String path;

    SubSort(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
