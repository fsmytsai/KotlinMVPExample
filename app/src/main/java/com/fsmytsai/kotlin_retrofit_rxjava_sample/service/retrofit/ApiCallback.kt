package com.fsmytsai.kotlin_retrofit_rxjava_sample.service.retrofit

import com.google.gson.Gson
import io.reactivex.observers.DisposableObserver
import retrofit2.HttpException
import java.net.UnknownHostException


/**
* Created by fsmytsai on 2017/12/17.
*/
abstract class ApiCallback<M> : DisposableObserver<M>() {

    override fun onComplete() {
        onFinish()
    }

    override fun onNext(model: M) {
        onSuccess(model)
    }

    override fun onError(t: Throwable?) {
        val errorList = ArrayList<String>()
        when (t) {
            is HttpException -> {
                val code = t.code()
                var msg = t.response().errorBody()?.string()
                if (code == 502 || code == 404)
                    msg = "伺服器異常，請稍後再試"
                else if (code == 500)
                    msg = "伺服器錯誤，請聯絡開發人員"
                errorList.add(msg!!)
                onFailure(errorList)
            }
            is UnknownHostException -> {
                errorList.add("請檢查網路連線")
                onFailure(errorList)
            }
            else -> onFailure(Gson().fromJson(t.toString(), ArrayList<String>()::class.java))
        }
    }
    abstract fun onSuccess(model: M)

    abstract fun onFailure(errorList: ArrayList<String>)

    abstract fun onFinish()
}