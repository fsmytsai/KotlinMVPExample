package com.fsmytsai.kotlinmvpexample.ui.activity

import android.support.v7.app.AppCompatActivity
import com.fsmytsai.kotlinmvpexample.service.presenter.BasePresenter

/**
* Created by fsmytsai on 2018/1/13.
*/
abstract class BaseActivity<out P : BasePresenter> : AppCompatActivity() {
    protected val mPresenter: P

    init {
        mPresenter = this.createPresenter()
    }

    protected abstract fun createPresenter(): P

    override fun onStop() {
        super.onStop()
        mPresenter.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestroy()
    }


}