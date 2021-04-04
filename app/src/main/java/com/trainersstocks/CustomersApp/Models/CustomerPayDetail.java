package com.trainersstocks.CustomersApp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerPayDetail extends ResponseModel {

    @SerializedName("data")
    @Expose
    public CustomerPayDetailModel data;

    public CustomerPayDetailModel getData() {
        return data;
    }

}
