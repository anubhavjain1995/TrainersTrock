package com.trainersstocks.CustomersApp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDetails {


    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("role")
    @Expose
    public String role;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("user_name")
    @Expose
    public String userName;
    @SerializedName("created_at")
    @Expose
    public Integer createdAt;

    public String getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }
}
