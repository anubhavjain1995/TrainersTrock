package com.trainersstocks.CustomersApp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CustomerPayListModel extends ResponseModel {

    @SerializedName("data")
    @Expose
    ArrayList<CustomerPayModel> listModels;

    public ArrayList<CustomerPayModel> getListModels() {
        return listModels;
    }
}
