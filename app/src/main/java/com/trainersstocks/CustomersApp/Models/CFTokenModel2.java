package com.trainersstocks.CustomersApp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CFTokenModel2 {

    @SerializedName("status")
    @Expose
    String status = null;


    @SerializedName("cftoken")
    @Expose
    String cftoken = null;

    public String getStatus() {
        return status;
    }

    public String getCftoken() {
        return cftoken;
    }
}
