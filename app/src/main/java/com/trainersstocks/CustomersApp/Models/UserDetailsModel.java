package com.trainersstocks.CustomersApp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDetailsModel {

    @SerializedName("user")
    @Expose
    UserDetails userDetails;

    @SerializedName("Auth-Token")
    @Expose
    String Auth_Token;


    public UserDetails getUserDetails() {
        return userDetails;
    }

    public String getAuth_Token() {
        return Auth_Token;
    }
}
