package com.fsmytsai.kotlin_retrofit_rxjava_sample.service.app

import android.content.SharedPreferences
import android.support.design.widget.Snackbar
import android.widget.TextView
import com.fsmytsai.kotlin_retrofit_rxjava_sample.R

/**
 * Created by fsmytsai on 2018/1/14.
 */
class SharedService {
    companion object {
        var mHttpDataSP: SharedPreferences? = null

        fun setSnackbarColor(snackbar: Snackbar, messageColor: Int, backgroundColor: Int) {
            val view = snackbar.view
            view.setBackgroundColor(backgroundColor)
            view.findViewById<TextView>(R.id.snackbar_text).setTextColor(messageColor)
        }
    }
}