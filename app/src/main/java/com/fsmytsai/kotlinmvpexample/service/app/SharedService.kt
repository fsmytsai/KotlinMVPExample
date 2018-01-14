package com.fsmytsai.kotlinmvpexample.service.app

import android.content.SharedPreferences
import android.support.design.widget.Snackbar
import android.widget.TextView
import com.fsmytsai.kotlinmvpexample.R

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