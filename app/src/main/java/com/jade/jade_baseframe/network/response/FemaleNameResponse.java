package com.jade.jade_baseframe.network.response;

import com.google.gson.annotations.SerializedName;
import com.jade.jade_baseframe.network.bean.Introduce;

import java.util.List;

public class FemaleNameResponse {

    @SerializedName("data")
    private List<Introduce> mIntroduceList;

    public List<Introduce> getIntroduceList() {
        return mIntroduceList;
    }

    public void setIntroduceList(List<Introduce> introduceList) {
        this.mIntroduceList = introduceList;
    }
}
