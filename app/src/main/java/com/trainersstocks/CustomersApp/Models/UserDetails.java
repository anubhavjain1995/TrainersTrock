package com.trainersstocks.CustomersApp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class UserDetails {


    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("user_id")
    @Expose
    public String user_id;
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
