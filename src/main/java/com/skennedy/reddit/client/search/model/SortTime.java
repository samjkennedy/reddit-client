package com.skennedy.reddit.client.search.model;

public enum SortTime {

    HOUR("hour"),
    DAY("day"),
    WEEK("week"),
    MONTH("month"),
    YEAR("year"),
    ALL("all");

    private final String time;

    SortTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }
}
