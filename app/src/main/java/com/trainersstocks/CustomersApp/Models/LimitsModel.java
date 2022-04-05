package com.trainersstocks.CustomersApp.Models;

import java.util.ArrayList;

public class LimitsModel {
    String desc;
    String trading,mf;
    boolean showArrow,isBold;
    ArrayList<LimitsModel> limitsList;

    public LimitsModel(String desc, String trading, String mf,boolean showArrow,boolean isBold,ArrayList<LimitsModel> limitsList) {
        this.desc = desc;
        this.trading = trading;
        this.mf = mf;
        this.showArrow=showArrow;
        this.isBold = isBold;
        this.limitsList = limitsList;
    }

    public boolean isBold() {
        return isBold;
    }

    public void setBold(boolean bold) {
        isBold = bold;
    }

    public boolean isShowArrow() {
        return showArrow;
    }

    public void setShowArrow(boolean showArrow) {
        this.showArrow = showArrow;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTrading() {
        return trading;
    }

    public void setTrading(String trading) {
        this.trading = trading;
    }

    public String getMf() {
        return mf;
    }

    public void setMf(String mf) {
        this.mf = mf;
    }

    public ArrayList<LimitsModel> getLimitsList() {
        return limitsList;
    }

    public void setLimitsList(ArrayList<LimitsModel> limitsList) {
        this.limitsList = limitsList;
    }
}
