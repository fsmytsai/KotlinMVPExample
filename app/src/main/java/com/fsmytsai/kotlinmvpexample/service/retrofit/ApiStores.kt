package com.fsmytsai.kotlinmvpexample.service.retrofit

import com.fsmytsai.kotlinmvpexample.model.User
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


    @GET("api/logout")
    fun logout(): Observable<String>
}