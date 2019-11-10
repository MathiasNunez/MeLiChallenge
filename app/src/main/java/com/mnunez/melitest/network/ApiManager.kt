package com.mnunez.melitest.network

import com.mnunez.core.network.BaseRestApi
import com.mnunez.melitest.BuildConfig
import com.mnunez.melitest.models.FullItem
import com.mnunez.melitest.network.interfaces.SearchApi
import com.mnunez.melitest.network.models.SearchResult
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ApiManager : BaseRestApi() {

    companion object {
        @Volatile
        private var instance: ApiManager? = null

        fun getInstance(): ApiManager =
            instance ?: synchronized(this) {
                instance ?: ApiManager()
            }
    }

    fun searchItems(searchWord: String, offset: Int): Observable<SearchResult> {
        return getApi(BuildConfig.URL_BASE, SearchApi::class.java)
            .searchItems(searchWord = searchWord, offset = offset)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getItemDetails(itemId: String): Observable<FullItem> {
        return getApi(BuildConfig.URL_BASE, SearchApi::class.java)
            .getItemDetails(itemId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getMoreSellerProducts(sellerId: Int): Observable<SearchResult> {
        return getApi(BuildConfig.URL_BASE, SearchApi::class.java)
            .getMoreSellerProducts(sellerId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}