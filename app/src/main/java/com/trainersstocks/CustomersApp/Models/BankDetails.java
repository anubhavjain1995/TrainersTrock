package com.trainersstocks.CustomersApp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BankDetails extends ResponseModel{

    @SerializedName("data")
    @Expose
    BankModel model;

    public BankModel getModel() {
        return model;
    }

    public void setModel(BankModel model) {
        this.model = model;
    }
}
