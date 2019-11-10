package com.mnunez.core.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mnunez.core.core.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class BaseRestApi {

    companion object {

        private val retrofitInstances = mutableMapOf<String, Retrofit>()

        fun buildGson(): Gson {
            return GsonBuilder().create()
        }
    }

    fun <T> getApi(baseUrl: String, apiInterface: Class<out T>): T {
        return get(baseUrl)!!.create(apiInterface)
    }

    private fun buildLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    private fun get(baseUrl: String): Retrofit? {
        if (retrofitInstances[baseUrl] != null) {
            return retrofitInstances[baseUrl]
        }
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(buildGson()))
            .client(buildClient()).build()
        retrofitInstances[baseUrl] = retrofit
        return retrofit
    }


    private fun buildClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(10, TimeUnit.SECONDS)
        builder.readTimeout(30, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(buildLoggingInterceptor())
        }
        return builder.build()
    }

}