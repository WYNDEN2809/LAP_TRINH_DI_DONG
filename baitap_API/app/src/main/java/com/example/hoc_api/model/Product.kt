package com.example.hoc_api.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id")
    val id: String = "",

    @SerializedName("name")
    val name: String = "",

    @SerializedName("des")
    val des: String = "",

    @SerializedName("price")
    val price: Double = 0.0,

    @SerializedName("imgURL")
    val imgURL: String = ""
)
