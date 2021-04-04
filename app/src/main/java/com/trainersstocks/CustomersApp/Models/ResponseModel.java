package com.trainersstocks.CustomersApp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponseModel {

    @SerializedName("error")
    @Expose
    boolean error;

    @SerializedName("message")
    @Expose
    ArrayList<String> msg;


    public boolean getError() {
        return error;
    }

    public ArrayList<String> getMsg() {
        return msg;
    }
}
