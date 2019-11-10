package com.mnunez.core.base

interface BaseView {

    fun startLoading()
    fun stopLoading()
    fun showNoConnectionError(retryAction: () -> Unit)
    fun showDefaultError(retryAction: () -> Unit)
}