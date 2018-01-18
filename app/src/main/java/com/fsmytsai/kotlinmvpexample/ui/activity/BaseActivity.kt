package com.fsmytsai.kotlinmvpexample.ui.activity

import android.graphics.Color
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import com.fsmytsai.kotlinmvpexample.service.app.SharedService
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

    fun handleErrorMessage(errorList: ArrayList<String>) {
        if (errorList.size == 1) {
            val rootView = findViewById<ViewGroup>(android.R.id.content).getChildAt(0)
            val snackBar = Snackbar.make(rootView, errorList[0], Snackbar.LENGTH_LONG)
            SharedService.setSnackbarColor(snackBar, Color.WHITE, Color.RED)
            snackBar.show()
        } else {
            var msg = ""

            for (i in 0 until errorList.size) {
                msg += errorList[i]
                if (i != errorList.size - 1) {
                    msg += "\n"
                }
            }

            AlertDialog.Builder(this)
                    .setTitle("錯誤訊息")
                    .setMessage(msg)
                    .setPositiveButton("知道了", null)
                    .show()
        }
    }

}