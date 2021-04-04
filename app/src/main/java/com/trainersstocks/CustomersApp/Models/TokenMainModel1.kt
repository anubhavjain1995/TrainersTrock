package com.trainersstocks.CustomersApp.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

public class TokenMainModel1 {

    @SerializedName("error")
    @Expose
    var error = false

    @SerializedName("message")
    @Expose
    var message: List<String>? = null

    @SerializedName("data")
    @Expose
    var data: CFTokenModel? = null
}