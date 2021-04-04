package com.trainersstocks.CustomersApp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SellStockListModel extends ResponseModel {

    @SerializedName("data")
    @Expose
    ArrayList<BuyStockModel> list;

    public ArrayList<BuyStockModel> getList() {
        return list;
    }
}
