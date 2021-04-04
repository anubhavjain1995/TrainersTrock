package com.trainersstocks.CustomersApp.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SellStockModel implements Parcelable {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("stock_name")
    @Expose
    public String stockName;
    @SerializedName("sell_price")
    @Expose
    public String sellPrice;
    @SerializedName("sell_quantity")
    @Expose
    public String sellQuantity;
    @SerializedName("buy_price")
    @Expose
    public String buyPrice;
    @SerializedName("buy_quantity")
    @Expose
    public String buyQuantity;
    @SerializedName("date")
    @Expose
    public String date;
    @SerializedName("created_at")
    @Expose
    public Object createdAt;
    @SerializedName("updated_at")
    @Expose
    public Object updatedAt;


    protected SellStockModel(Parcel in) {
        id = in.readString();
        userId = in.readString();
        stockName = in.readString();
        sellPrice = in.readString();
        sellQuantity = in.readString();
        buyPrice = in.readString();
        buyQuantity = in.readString();
        date = in.readString();
    }

    public static final Creator<SellStockModel> CREATOR = new Creator<SellStockModel>() {
        @Override
        public SellStockModel createFromParcel(Parcel in) {
            return new SellStockModel(in);
        }

        @Override
        public SellStockModel[] newArray(int size) {
            return new SellStockModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(userId);
        parcel.writeString(stockName);
        parcel.writeString(sellPrice);
        parcel.writeString(sellQuantity);
        parcel.writeString(buyPrice);
        parcel.writeString(buyQuantity);
        parcel.writeString(date);
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getStockName() {
        return stockName;
    }

    public String getSellPrice() {
        return sellPrice;
    }

    public String getSellQuantity() {
        return sellQuantity;
    }

    public String getBuyPrice() {
        return buyPrice;
    }

    public String getBuyQuantity() {
        return buyQuantity;
    }

    public String getDate() {
        return date;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public static Creator<SellStockModel> getCREATOR() {
        return CREATOR;
    }
}
