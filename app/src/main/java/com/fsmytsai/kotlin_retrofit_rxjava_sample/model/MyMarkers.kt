package com.fsmytsai.kotlin_retrofit_rxjava_sample.model

/**
 * Created by tsaiminyuan on 2017/12/15.
 */
data class MyMarkers(val MarkerList: List<MyMarker>) {
    data class MyMarker
    (
            val MarkerId: Int,
            val Latitude: Double,
            val Longitude: Double,
            val Title: String,
            val Content: String
    )
}