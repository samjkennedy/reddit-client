package com.skennedy.reddit.client.common.model;

//Reddit's name for it, not mine
public interface Thing<T> {

    String getKind();
    T getData();

}
