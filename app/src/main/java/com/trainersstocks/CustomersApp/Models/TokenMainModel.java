package com.trainersstocks.CustomersApp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TokenMainModel {
    @SerializedName("error")
    @Expose
    boolean error;

    @SerializedName("message")
    @Expose
    public List<String> message = null;
    @SerializedName("data")
    @Expose
    CFTokenModel2 data;
    public CFTokenModel2 getData() {
        return data;
    }

    public boolean isError() {
        return error;
    }
}
