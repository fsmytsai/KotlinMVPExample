package com.fsmytsai.kotlin_retrofit_rxjava_sample.service.presenter

import com.fsmytsai.kotlin_retrofit_rxjava_sample.model.MyMarkers
import com.fsmytsai.kotlin_retrofit_rxjava_sample.service.retrofit.ApiCallback
import com.fsmytsai.kotlin_retrofit_rxjava_sample.service.view.GenericView

/**
 * Created by tsaiminyuan on 2017/12/15.
 */
class MainPresenter(private val myMarkersView: GenericView<MyMarkers>,
                    private val test400ErrorView: GenericView<String>) : BasePresenter() {
    fun getMyMarkers() {
        addSubscription(apiStores.getMyMarkers(), ApiCallback(myMarkersView))
    }

    fun get400ErrorByPost(DataList: List<String>) {
        addSubscription(apiStores.get400ErrorByPost(DataList), ApiCallback(test400ErrorView))
    }
}