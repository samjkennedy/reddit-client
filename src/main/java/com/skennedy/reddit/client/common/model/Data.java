package com.skennedy.reddit.client.common.model;

import java.util.List;

public class Data<T extends Thing<?>> {

    private String kind;
    private int dist;
    private String after;
    private String before;
    private List<T> children;

    public String getKind() {
        return kind;
    }

    public int getDist() {
        return dist;
    }

    public String getAfter() {
        return after;
    }

    public String getBefore() {
        return before;
    }

    public List<T> getChildren() {
        return children;
    }
}
