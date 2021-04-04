package com.trainersstocks.CustomersApp.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

public class CFTokenModel {

    @SerializedName("status")
    @Expose
    var status: String? = null




    @SerializedName("cftoken")
    @Expose
    var cftoken: String? = null
}