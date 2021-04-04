package com.trainersstocks.CustomersApp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserListModel extends ResponseModel {

    @SerializedName("data")
    @Expose
    ArrayList<UserModel> userList;

    public ArrayList<UserModel> getUserList() {
        return userList;
    }
}
