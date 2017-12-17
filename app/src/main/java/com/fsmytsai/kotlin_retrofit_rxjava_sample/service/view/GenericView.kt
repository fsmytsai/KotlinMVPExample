package com.fsmytsai.kotlin_retrofit_rxjava_sample.service.view

/**
 * Created by tsaiminyuan on 2017/12/15.
 */
interface GenericView<in M> {
    fun onSuccess(m: M)
    fun onError(errorMessage: String)
}