package com.fsmytsai.kotlin_retrofit_rxjava_sample.service.view

import com.fsmytsai.kotlin_retrofit_rxjava_sample.model.User

/**
* Created by fsmytsai on 2017/12/15.
*/
interface MainView : BaseView {
    fun loginSuccess(resMessage: String)
    fun loginFinish()
    fun getUserDataSuccess(userData: User)
    fun getUserDataFinish()
}