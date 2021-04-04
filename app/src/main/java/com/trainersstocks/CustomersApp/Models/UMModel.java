package com.trainersstocks.CustomersApp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UMModel extends ResponseModel {

    @SerializedName("data")
    @Expose
    UserDetailsMModel model;

    public UserDetailsMModel getModel() {
        return model;
    }
}
