package com.mnunez.melitest.network.interfaces

import com.mnunez.melitest.models.FullItem
import com.mnunez.melitest.network.models.SearchResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface SearchApi {

    @GET("sites/MLA/search")
    fun searchItems(
        @Query("q", encoded = true) searchWord: String,
        @Query("limit", encoded = true) limit: Int = 20,
        @Query("offset", encoded = true) offset: Int
    ): Observable<SearchResult>

    @GET("items/{itemId}")
    fun getItemDetails(@Path("itemId") itemId: String): Observable<FullItem>

    @GET("sites/MLA/search")
    fun getMoreSellerProducts(@Query("seller_id", encoded = true) sellerId: Int,
                              @Query("limit", encoded = true) limit: Int = 2
    ): Observable<SearchResult>
}






