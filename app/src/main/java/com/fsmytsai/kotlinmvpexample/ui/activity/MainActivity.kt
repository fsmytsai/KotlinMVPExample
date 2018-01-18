package com.fsmytsai.kotlinmvpexample.ui.activity

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.fsmytsai.kotlinmvpexample.R
import com.fsmytsai.kotlinmvpexample.model.User
import com.fsmytsai.kotlinmvpexample.service.app.SharedService
import com.fsmytsai.kotlinmvpexample.service.presenter.MainPresenter
import com.fsmytsai.kotlinmvpexample.service.view.MainView
import kotlin.collections.ArrayList

class MainActivity : BaseActivity<MainPresenter>(), MainView {

    var etMainAccount: EditText? = null
    var etMainPassword: EditText? = null
    var pbMainLoad: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SharedService.mHttpDataSP = getSharedPreferences("HttpData", MODE_PRIVATE)
        initViews()
        mPresenter.getUserData()
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
        mPresenter.login(etMainAccount?.text.toString(), etMainPassword?.text.toString())
    }

    fun logout(v: View) {
        pbMainLoad?.visibility = View.VISIBLE
        mPresenter.logout()
    }

    override fun onFailure(errorList: ArrayList<String>) {
        pbMainLoad?.visibility = View.GONE

        handleErrorMessage(errorList)
    }

    override fun loginSuccess(token: String) {
        SharedService.mHttpDataSP?.edit()?.putString("token", token)?.apply()
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

    override fun logoutSuccess(resMessage: String) {
        Toast.makeText(this, resMessage, Toast.LENGTH_SHORT).show()
    }

    override fun logoutFinish() {
        pbMainLoad?.visibility = View.GONE
    }
}
