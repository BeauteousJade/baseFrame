package com.jade.jade_baseframe.recyclerfragment;

import com.jade.jade_baseframe.network.NetWorkManager;
import com.jade.jade_baseframe.network.Service;
import com.jade.jade_baseframe.network.request.BaseRequest;
import com.jade.jade_baseframe.network.response.FemaleNameResponse;

import io.reactivex.Observable;

public class FemaleNameRequest extends BaseRequest<FemaleNameResponse> {

    @Override
    public Observable<FemaleNameResponse> createObservable() {
        return NetWorkManager.getService(Service.class).getFemaleNameList(0);
    }
}
