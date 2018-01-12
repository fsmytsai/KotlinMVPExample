package com.fsmytsai.kotlin_retrofit_rxjava_sample.service.retrofit

import com.fsmytsai.kotlin_retrofit_rxjava_sample.model.User
import io.reactivex.Observable
import retrofit2.http.*

/**
* Created by fsmytsai on 2017/12/15.
*/
interface ApiStores {
    @GET("api/getUserData")
    fun getUserData(): Observable<User>

    @FormUrlEncoded
    @POST("api/login")
    fun login(@Field("account") account: String,
              @Field("password") password: String): Observable<String>
}