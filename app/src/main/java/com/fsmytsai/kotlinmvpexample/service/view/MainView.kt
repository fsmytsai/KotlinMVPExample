package com.fsmytsai.kotlinmvpexample.service.view

import com.fsmytsai.kotlinmvpexample.model.User

/**
 * Created by fsmytsai on 2017/12/15.
 */
interface MainView : BaseView {
    fun loginSuccess(token: String)
    fun loginFinish()
    fun getUserDataSuccess(userData: User)
    fun logoutSuccess(resMessage: String)
    fun logoutFinish()
}