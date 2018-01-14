package com.fsmytsai.kotlinmvpexample.service.view

/**
* Created by fsmytsai on 2018/1/13.
*/
interface BaseView {
    fun onFailure(errorList: ArrayList<String>)
}