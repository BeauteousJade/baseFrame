package com.jade.jade_baseframe.network.bean;

import com.google.gson.annotations.SerializedName;

public class Introduce {

    @SerializedName("femalename")
    private String femaleName;

    public String getFemaleName() {
        return femaleName;
    }

    public void setFemaleName(String femaleName) {
        this.femaleName = femaleName;
    }
}
