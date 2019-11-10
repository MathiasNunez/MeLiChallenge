package com.mnunez.melitest.network.models

import com.google.gson.annotations.SerializedName
import com.mnunez.melitest.models.Item

data class SearchResult(
    @SerializedName("results") var results: ArrayList<Item>?,
    @SerializedName("paging") var paging: Paging?
)

data class Paging(
    @SerializedName("total") var total: Int?
)