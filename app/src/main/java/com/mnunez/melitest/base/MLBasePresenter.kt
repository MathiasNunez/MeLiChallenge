package com.mnunez.melitest.base

import android.util.Log
import com.mnunez.core.base.BasePresenter
import com.mnunez.core.base.BaseView
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class MLBasePresenter<V : BaseView> : BasePresenter<V>() {

    override fun handleApiError(
        it: Throwable,
        retryAction: () -> Unit,
        customErrorAction: () -> Unit,
        showDefaultError: Boolean
    ) {
        Log.e("BasePresenter", it.stackTrace.toString() + " - " + it.message)
        it.printStackTrace()
        view?.stopLoading()
        if (it is SocketTimeoutException || it is UnknownHostException) {
            view?.showNoConnectionError(retryAction)
        } else {
            view?.showDefaultError(retryAction)
        }
    }
}