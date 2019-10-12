package com.jade.jade_baseframe.network;

public class Response<T> {
    private T mData;
    private Throwable mError;

    public Response(T data, Throwable error) {
        this.mData = data;
        this.mError = error;
    }

    public T getData() {
        return mData;
    }

    public void setData(T mData) {
        this.mData = mData;
    }

    public Throwable getError() {
        return mError;
    }

    public void setError(Throwable mError) {
        this.mError = mError;
    }
}
