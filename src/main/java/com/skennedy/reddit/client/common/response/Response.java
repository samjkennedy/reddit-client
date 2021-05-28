package com.skennedy.reddit.client.common.response;

public class Response<T> {

    T data;
    Fail<T> error;

    public static <T> Response<T> success(T data) {
        return new Response<>(data);
    }

    public static <T> Response<T> error(Fail<T> fail) {
        return new Response<>(fail);
    }

    Response(T data) {
        this.data = data;
    }

    Response(Fail<T> error) {
        this.error = error;
    }

    public boolean hasData() {
        return data != null;
    }

    public boolean hasError() {
        return error != null;
    }

    public T getData() {
        return data;
    }

    public Fail<T> getError() {
        return error;
    }
}
