package com.fsmytsai.kotlin_retrofit_rxjava_sample.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.fsmytsai.kotlin_retrofit_rxjava_sample.R
import com.fsmytsai.kotlin_retrofit_rxjava_sample.model.MyMarkers
import com.fsmytsai.kotlin_retrofit_rxjava_sample.service.presenter.MainPresenter
import com.fsmytsai.kotlin_retrofit_rxjava_sample.service.view.GenericView
import java.util.*

class MainActivity : AppCompatActivity() {
    var tv_GetResult: TextView? = null
    var tv_PostResult: TextView? = null

    private var mainPresenter: MainPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        mainPresenter = MainPresenter(myMarkersView, test400ErrorView)
    }

    private fun initViews() {
        tv_GetResult = findViewById(R.id.tv_GetResult)
        tv_PostResult = findViewById(R.id.tv_PostResult)
    }

    override fun onStop() {
        super.onStop()
        mainPresenter?.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mainPresenter?.onDestroy()
    }

    fun testGet(v: View) {
        mainPresenter?.getMyMarkers()
    }

    fun testPost(v: View) {
        val dataList = ArrayList<String>()
        dataList.add("Hello  ")
        dataList.add("World  ")
        dataList.add("!")

        mainPresenter?.get400ErrorByPost(dataList)
    }

    private val myMarkersView = object : GenericView<MyMarkers> {
        override fun onSuccess(m: MyMarkers) {
            val rand = Random().nextInt(m.MarkerList.size)
            tv_GetResult?.text = m.MarkerList[rand].Content
        }

        override fun onError(errorMessage: String) {
            Toast.makeText(this@MainActivity, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private val test400ErrorView = object : GenericView<String> {
        override fun onSuccess(m: String) {
            //return 200
        }

        override fun onError(errorMessage: String) {
            tv_PostResult?.text = errorMessage
        }
    }
}
