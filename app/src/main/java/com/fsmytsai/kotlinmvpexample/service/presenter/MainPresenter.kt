package com.fsmytsai.kotlinmvpexample.service.presenter

import com.fsmytsai.kotlinmvpexample.model.User
import com.fsmytsai.kotlinmvpexample.service.retrofit.ApiCallback
import com.fsmytsai.kotlinmvpexample.service.view.MainView

/**
* Created by fsmytsai on 2017/12/15.
*/
class MainPresenter(private val mainView: MainView) : BasePresenter() {

    fun getUserData() {
        addSubscription(mApiStores.getUserData(), object : ApiCallback<User>() {
            override fun onSuccess(model: User) {
                mainView.getUserDataSuccess(model)
            }

            override fun onFailure(errorList: ArrayList<String>) {
                mainView.onFailure(errorList)
            }

            override fun onFinish() {

            }

        })
    }

    fun login(account: String, password: String) {
        addSubscription(mApiStores.login(account, password), object : ApiCallback<String>() {
            override fun onSuccess(model: String) {
                mainView.loginSuccess(model)
            }

            override fun onFailure(errorList: ArrayList<String>) {
                mainView.onFailure(errorList)
            }

            override fun onFinish() {
                mainView.loginFinish()
            }

        })
    }

    fun logout() {
        addSubscription(mApiStores.logout(), object : ApiCallback<String>() {
            override fun onSuccess(model: String) {
                mainView.logoutSuccess(model)
            }

            override fun onFailure(errorList: ArrayList<String>) {
                mainView.onFailure(errorList)
            }

            override fun onFinish() {
                mainView.logoutFinish()
            }

        })
    }
}