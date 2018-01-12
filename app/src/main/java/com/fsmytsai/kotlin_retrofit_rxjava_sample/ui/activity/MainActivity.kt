package com.fsmytsai.kotlin_retrofit_rxjava_sample.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.fsmytsai.kotlin_retrofit_rxjava_sample.R
import com.fsmytsai.kotlin_retrofit_rxjava_sample.model.User
import com.fsmytsai.kotlin_retrofit_rxjava_sample.service.presenter.MainPresenter
import com.fsmytsai.kotlin_retrofit_rxjava_sample.service.view.MainView
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), MainView {
    var et_main_account: EditText? = null
    var et_main_password: EditText? = null

    private var mainPresenter: MainPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        mainPresenter = MainPresenter(this)
    }

    private fun initViews() {
        et_main_account = findViewById(R.id.et_main_account)
        et_main_password = findViewById(R.id.et_main_password)
    }

    override fun onStop() {
        super.onStop()
        mainPresenter?.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mainPresenter?.onDestroy()
    }

    fun login(v: View) {
        mainPresenter?.login(et_main_account?.text.toString(), et_main_password?.text.toString())
    }

    override fun onFailure(errorList: ArrayList<String>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loginSuccess(resMessage: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loginFinish() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUserDataSuccess(userData: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUserDataFinish() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
