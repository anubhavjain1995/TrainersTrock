package com.trainersstocks.CustomersApp.adapters;

public class PricingModel {

    String stock_name;
    String Monthly_price;
    String Quarterly_price;
    String Half_yearly_price;
    String Yearly_price;



    public String getStock_name() {
        return stock_name;
    }

    public String getMonthly_price() {
        return Monthly_price;
    }

    public String getQuarterly_price() {
        return Quarterly_price;
    }

    public String getHalf_yearly_price() {
        return Half_yearly_price;
    }

    public String getYearly_price() {
        return Yearly_price;
    }

    public PricingModel() {
    }

    public PricingModel(String stock_name, String monthly_price, String quarterly_price, String half_yearly_price, String yearly_price) {
        this.stock_name = stock_name;
        Monthly_price = monthly_price;
        Quarterly_price = quarterly_price;
        Half_yearly_price = half_yearly_price;
        Yearly_price = yearly_price;
    }
}
