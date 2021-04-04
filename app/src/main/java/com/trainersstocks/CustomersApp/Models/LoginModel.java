package com.trainersstocks.CustomersApp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginModel extends ResponseModel {

    @SerializedName("data")
    @Expose
    UserDetailsModel data;

    public UserDetailsModel getData() {
        return data;
    }

}
