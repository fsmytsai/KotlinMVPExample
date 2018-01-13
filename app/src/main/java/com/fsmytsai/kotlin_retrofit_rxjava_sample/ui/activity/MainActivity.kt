package com.fsmytsai.kotlin_retrofit_rxjava_sample.ui.activity

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.fsmytsai.kotlin_retrofit_rxjava_sample.R
import com.fsmytsai.kotlin_retrofit_rxjava_sample.model.User
import com.fsmytsai.kotlin_retrofit_rxjava_sample.service.presenter.MainPresenter
import com.fsmytsai.kotlin_retrofit_rxjava_sample.service.view.MainView
import kotlin.collections.ArrayList

class MainActivity : BaseActivity<MainPresenter>(), MainView {
    var etMainAccount: EditText? = null
    var etMainPassword: EditText? = null
    var pbMainLoad: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        etMainAccount = findViewById(R.id.et_main_account)
        etMainPassword = findViewById(R.id.et_main_password)
        pbMainLoad = findViewById(R.id.pb_main_load)
    }

    override fun createPresenter(): MainPresenter {
        return MainPresenter(this)
    }

    fun login(v: View) {
        pbMainLoad?.visibility = View.VISIBLE
        mPresenter.login(etMainPassword?.text.toString(), etMainPassword?.text.toString())
    }

    override fun onFailure(errorList: ArrayList<String>) {
        pbMainLoad?.visibility = View.GONE
        if (errorList.size == 1)
            Toast.makeText(this, errorList[0], Toast.LENGTH_SHORT).show()
        else {
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

    override fun loginSuccess(resMessage: String) {
        Toast.makeText(this, "取得用戶資料中...", Toast.LENGTH_SHORT).show()
        mPresenter.getUserData()
    }

    override fun loginFinish() {
        pbMainLoad?.visibility = View.GONE
    }

    override fun getUserDataSuccess(userData: User) {
        val data = "${userData.account}\n${userData.email}\n${userData.name}\n${userData.profile_pic}"
        AlertDialog.Builder(this)
                .setTitle("會員資料")
                .setMessage(data)
                .setPositiveButton("知道了", null)
                .show()
    }

}
