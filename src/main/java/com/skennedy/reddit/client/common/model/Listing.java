package com.skennedy.reddit.client.common.model;

import java.util.List;

public interface Listing<T extends Thing<?>> {

    String getAfter();

    String getBefore();

    List<T> getChildren();
}
