package com.fsmytsai.kotlin_retrofit_rxjava_sample.service.presenter

import com.fsmytsai.kotlin_retrofit_rxjava_sample.service.retrofit.ApiClient
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

/**
 * Created by tsaiminyuan on 2017/12/15.
 */
open class BasePresenter {
    val apiStores = ApiClient.instance.getServer()
    private val compositeDisposable = CompositeDisposable()

    fun onStop(){
        compositeDisposable.clear()
    }

    fun onDestroy() {
        compositeDisposable.dispose()
    }

    fun <M> addSubscription(observable: Observable<M>, subscriber: DisposableObserver<M>) {
        compositeDisposable.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(subscriber))
    }
}