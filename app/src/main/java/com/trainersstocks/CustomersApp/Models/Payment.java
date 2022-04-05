package com.trainersstocks.CustomersApp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Payment {
    @SerializedName("mkey")
    @Expose
    public String mkey;
    @SerializedName("tid")
    @Expose
    public String tid;
    @SerializedName("hash")
    @Expose
    public String hash;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("productinfo")
    @Expose
    public String productinfo;
    @SerializedName("mailid")
    @Expose
    public String mailid;
    @SerializedName("phoneno")
    @Expose
    public String phoneno;
    @SerializedName("sucess")
    @Expose
    public String surl;
    @SerializedName("failure")
    @Expose
    public String furl;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductinfo() {
        return productinfo;
    }

    public void setProductinfo(String productinfo) {
        this.productinfo = productinfo;
    }

    public String getMailid() {
        return mailid;
    }

    public void setMailid(String mailid) {
        this.mailid = mailid;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getMkey() {
        return mkey;
    }

    public void setMkey(String mkey) {
        this.mkey = mkey;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getSurl() {
        return surl;
    }

    public void setSurl(String surl) {
        this.surl = surl;
    }

    public String getFurl() {
        return furl;
    }

    public void setFurl(String furl) {
        this.furl = furl;
    }
}
