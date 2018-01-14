package com.fsmytsai.kotlinmvpexample.service.retrofit

import com.fsmytsai.kotlinmvpexample.service.app.SharedService
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

/**
 * Created by fsmytsai on 2017/12/15.
 */
class ApiClient private constructor() {
    private val mOkHttpClient = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .build()

    private val mRetrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://laravelcommunity.azurewebsites.net/")
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    companion object {
        val instance: ApiClient by lazy { ApiClient() }
    }

    fun getServer(): ApiStores {
        return mRetrofit.create(ApiStores::class.java)
    }

    private class AuthInterceptor : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request()
            val token = SharedService.mHttpDataSP?.getString("token", "")
            try {
                request = request.newBuilder().addHeader("Authorization", "Bearer " + token).build()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return chain.proceed(request)
        }
    }
}