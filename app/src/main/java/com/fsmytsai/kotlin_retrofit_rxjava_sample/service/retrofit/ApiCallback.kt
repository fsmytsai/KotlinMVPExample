package com.fsmytsai.kotlin_retrofit_rxjava_sample.service.retrofit

import com.fsmytsai.kotlin_retrofit_rxjava_sample.service.view.GenericView
import io.reactivex.observers.DisposableObserver
import retrofit2.HttpException
import java.net.UnknownHostException

/**
 * Created by tsaiminyuan on 2017/12/17.
 */
class ApiCallback<M>(private val genericView: GenericView<M>) : DisposableObserver<M>() {

    override fun onComplete() {

    }

    override fun onNext(t: M) {
        if (t != null) {
            genericView.onSuccess(t)
        }
    }

    override fun onError(t: Throwable?) {
        when (t) {
            is HttpException -> {
                val code = t.code()
                var msg = t.response().errorBody()?.string()
                if (code == 502 || code == 404)
                    msg = "伺服器異常，請稍後再試"
                else if (code == 500)
                    msg = "伺服器錯誤，請聯絡開發人員"
                genericView.onError(msg!!)
            }
            is UnknownHostException -> genericView.onError("請檢查網路連線")
            else -> genericView.onError(t.toString())
        }
    }
}