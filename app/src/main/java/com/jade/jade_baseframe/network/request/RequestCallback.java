package com.jade.jade_baseframe.network.request;

import com.jade.jade_baseframe.network.Response;

public interface RequestCallback<T> {
    void onResult(Response<T> response);
}
