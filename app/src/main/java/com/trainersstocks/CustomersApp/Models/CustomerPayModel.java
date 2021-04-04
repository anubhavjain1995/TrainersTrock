package com.trainersstocks.CustomersApp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerPayModel {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("customer_pay")
    @Expose
    public String customerPay;
    @SerializedName("profit")
    @Expose
    public String profit;
    @SerializedName("net_profit")
    @Expose
    public String netProfit;
    @SerializedName("loss")
    @Expose
    public String loss;
    @SerializedName("net_loss")
    @Expose
    public String netLoss;
    @SerializedName("balance_amount")
    @Expose
    public String balanceAmount;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public Object updatedAt;

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getCustomerPay() {
        return customerPay;
    }

    public String getProfit() {
        return profit;
    }

    public String getNetProfit() {
        return netProfit;
    }

    public String getLoss() {
        return loss;
    }

    public String getNetLoss() {
        return netLoss;
    }

    public String getBalanceAmount() {
        return balanceAmount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }
}
