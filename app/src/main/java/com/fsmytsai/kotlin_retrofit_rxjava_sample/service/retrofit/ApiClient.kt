package com.fsmytsai.kotlin_retrofit_rxjava_sample.service.retrofit

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by tsaiminyuan on 2017/12/15.
 */
class ApiClient private constructor() {
    private val client = OkHttpClient()
    private val factory = GsonConverterFactory.create(GsonBuilder().create())
    private val retrofit: Retrofit

    companion object {
        val instance: ApiClient by lazy { ApiClient() }
    }

    init {
        retrofit = Retrofit.Builder ()
                .baseUrl("https://frogcroak.azurewebsites.net/api/")
                .client(client)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    fun getServer(): ApiStores {
        return retrofit.create(ApiStores::class.java)
    }
}