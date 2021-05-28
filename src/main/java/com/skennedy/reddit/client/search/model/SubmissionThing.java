package com.skennedy.reddit.client.search.model;

import com.skennedy.reddit.client.common.model.Thing;

public class SubmissionThing implements Thing<Submission> {

    private String kind;
    private Submission data;

    @Override
    public String getKind() {
        return kind;
    }

    @Override
    public Submission getData() {
        return data;
    }
}
