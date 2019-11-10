package com.mnunez.melitest.base

import android.view.View
import com.mnunez.core.base.BaseActivity
import com.mnunez.core.base.BasePresenter
import com.mnunez.core.base.BaseView
import com.mnunez.core.utils.AlertUtils
import com.mnunez.melitest.R

abstract class MLBaseActivity<V : BaseView, P : BasePresenter<V>> : BaseActivity<V, P>() {

    abstract fun getFullscreenLoadingView(): View?

    override fun startLoading() {
        showFullscreenLoadingView(getFullscreenLoadingView())
    }

    override fun stopLoading() {
        hideFullscreenLoadingView(getFullscreenLoadingView())
    }

    override fun showNoConnectionError(retryAction: () -> Unit) {
        AlertUtils.showTwoButtonsAlert(this,
            getString(R.string.no_connection_error_title),
            getString(R.string.no_connection_error_message),
            getString(R.string.general_retry),
            getString(R.string.general_exit),
            retryAction, {})
    }

    override fun showDefaultError(retryAction: () -> Unit) {
        AlertUtils.showTwoButtonsAlert(this,
            getString(R.string.api_error_header),
            getString(R.string.api_error_description),
            getString(R.string.general_retry),
            getString(R.string.general_exit),
            retryAction, {})
    }

}