package com.mnunez.melitest.models

import com.google.gson.annotations.SerializedName

data class FullItem(
    @SerializedName("pictures") var images: ArrayList<Pictures>?,
    @SerializedName("seller_id") var sellerId: Int?,
    @SerializedName("attributes") var attributes: ArrayList<Attributes>?

)

data class Pictures(
    @SerializedName("url") var url: String?
)

data class Attributes(
    @SerializedName("name") var name: String?,
    @SerializedName("value_name") var value: String?
)