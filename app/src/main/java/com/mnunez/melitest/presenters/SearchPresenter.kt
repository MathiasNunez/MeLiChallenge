package com.mnunez.melitest.presenters

import com.mnunez.melitest.activities.SearchActivity
import com.mnunez.melitest.base.MLBasePresenter
import com.mnunez.melitest.network.ApiManager

class SearchPresenter : MLBasePresenter<SearchActivity>() {

    private var searchWord: String = ""
    private var offset: Int = 0

    fun search(_searchWord: String) {
        offset = 0
        searchWord = _searchWord
        view?.startLoading()
        val disposable = ApiManager.getInstance().searchItems(searchWord, offset).subscribe({
            view?.stopLoading()
            view?.onSearchResult(it.results ?: arrayListOf(), it.paging?.total)
        }, {
            handleApiError(it, retryAction = { search(searchWord) })
        })

        addDisposableToComposition(disposable)

    }

    fun loadMoreItems() {
        view?.showPagingLoading()
        offset++
        val disposable = ApiManager.getInstance().searchItems(searchWord, offset).subscribe({
            view?.onLoadMoreItemsResult(it.results ?: arrayListOf(), it.paging?.total)
            view?.hidePagingLoading()
        }, {
            handleApiError(it, retryAction = { loadMoreItems() })
        })

        addDisposableToComposition(disposable)
    }

}