package com.trainersstocks.CustomersApp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentModel extends ResponseModel{
    @SerializedName("data")
    @Expose
    Payment data;

    public Payment getData() {
        return data;
    }

    public void setData(Payment data) {
        this.data = data;
    }
}

