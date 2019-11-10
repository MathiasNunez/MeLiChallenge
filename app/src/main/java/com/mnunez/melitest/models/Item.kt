package com.mnunez.melitest.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(
    @SerializedName("id") var id: String?,
    @SerializedName("thumbnail") var thumbnail: String?,
    @SerializedName("title") var title: String?,
    @SerializedName("price") var price: Double?,
    @SerializedName("installments") var installments: Installments?
) : Parcelable

@Parcelize
data class Installments(
    @SerializedName("quantity") var quantity: Int?,
    @SerializedName("amount") var amount: Double?
) : Parcelable