package com.zz.garbageclassification.model.bean.response;

import com.google.gson.annotations.SerializedName;

public class GarbageResponse {
    private String name;
    @SerializedName("cat_name")
    private String catName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}
