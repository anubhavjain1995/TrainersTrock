package com.trainersstocks.CustomersApp.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerPayDetailModel implements Parcelable {

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


    protected CustomerPayDetailModel(Parcel in) {
        id = in.readString();
        userId = in.readString();
        customerPay = in.readString();
        profit = in.readString();
        netProfit = in.readString();
        loss = in.readString();
        netLoss = in.readString();
        balanceAmount = in.readString();
        createdAt = in.readString();
    }

    public static final Creator<CustomerPayDetailModel> CREATOR = new Creator<CustomerPayDetailModel>() {
        @Override
        public CustomerPayDetailModel createFromParcel(Parcel in) {
            return new CustomerPayDetailModel(in);
        }

        @Override
        public CustomerPayDetailModel[] newArray(int size) {
            return new CustomerPayDetailModel[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(userId);
        parcel.writeString(customerPay);
        parcel.writeString(profit);
        parcel.writeString(netProfit);
        parcel.writeString(loss);
        parcel.writeString(netLoss);
        parcel.writeString(balanceAmount);
        parcel.writeString(createdAt);
    }
}
