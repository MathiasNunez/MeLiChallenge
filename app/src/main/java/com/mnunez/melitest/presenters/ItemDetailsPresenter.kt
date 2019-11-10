package com.mnunez.melitest.presenters

import com.mnunez.melitest.activities.ItemDetailsActivity
import com.mnunez.melitest.base.MLBasePresenter
import com.mnunez.melitest.network.ApiManager

class ItemDetailsPresenter : MLBasePresenter<ItemDetailsActivity>() {

    fun getFullItem(itemId: String) {
        view?.startLoading()
        val disposable = ApiManager.getInstance().getItemDetails(itemId).subscribe({ fulItem ->
            val disposable = ApiManager.getInstance().getMoreSellerProducts(fulItem.sellerId ?: 0)
                .subscribe({
                    view?.stopLoading()
                    view?.onItemResult(fulItem, it.results)
                }, { it1 ->
                    handleApiError(it1, retryAction = { getFullItem(itemId) })
                })
            addDisposableToComposition(disposable)
        }, {
            handleApiError(it, retryAction = { getFullItem(itemId) })
        })
        addDisposableToComposition(disposable)
    }
}