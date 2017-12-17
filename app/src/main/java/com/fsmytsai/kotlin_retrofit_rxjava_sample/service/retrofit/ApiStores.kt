package com.fsmytsai.kotlin_retrofit_rxjava_sample.service.retrofit

import com.fsmytsai.kotlin_retrofit_rxjava_sample.model.MyMarkers
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by tsaiminyuan on 2017/12/15.
 */
interface ApiStores {
    //    @GET("book/search")
//    fun getMarkers(@Query("q")  name: String,
//                       @Query("tag")  tag: String, @Query("start")  start: Int,
//                       @Query("count")  count: Int): Observable<Book>
    @GET("MarkerApi/GetMarkerList")
    fun getMyMarkers(): Observable<MyMarkers>

    @FormUrlEncoded
    @POST("MarkerApi/get400ErrorByPost")
    fun get400ErrorByPost(@Field("DataList[]") DataList: List<String>): Observable<String>
}