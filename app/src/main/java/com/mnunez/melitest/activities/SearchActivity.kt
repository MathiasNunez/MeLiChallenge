package com.mnunez.melitest.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.SearchView
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mnunez.core.extensions.*
import com.mnunez.melitest.R
import com.mnunez.melitest.adapters.RecentSearchAdapter
import com.mnunez.melitest.adapters.SearchResultItemsAdapter
import com.mnunez.melitest.base.MLBaseActivity
import com.mnunez.melitest.models.Item
import com.mnunez.melitest.presenters.SearchPresenter
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : MLBaseActivity<SearchActivity, SearchPresenter>(),
    RecentSearchAdapter.RecentSearchListener, SearchResultItemsAdapter.SearchResultListener {

    companion object {
        const val PREFERENCES_NAME = "recent_searches"
        const val RECENT_SEARCHES = "recent_searches_key"
        const val ITEM_BUNDLE_KEY = "item_bundle"
    }

    private lateinit var searchResultItemsAdapter: SearchResultItemsAdapter
    private lateinit var recentSearchAdapter: RecentSearchAdapter
    private var recentSearches = arrayListOf<String>()
    private lateinit var sharedPreferences: SharedPreferences
    private var isLoadingMoreItems = false
    private var itemsSize: Int = 0
    private var totalResults = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        sharedPreferences = getSharedPreferences(PREFERENCES_NAME, 0)

        recentSearches = ArrayList(
            sharedPreferences.getString(RECENT_SEARCHES, null)?.split(",") ?: listOf()
        )
        recentSearchAdapter = RecentSearchAdapter(this)
        recent_search_recycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, true)
        recent_search_recycler.adapter = recentSearchAdapter
        recentSearchAdapter.loadData(ArrayList(recentSearches))

        search_result_recycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        search_view.setOnQueryTextFocusChangeListener { _, focused ->
            if (focused) {
                recent_search_recycler?.show()
                search_result_container?.hide()
                items_displayed_text?.hide()
            } else {
                recent_search_recycler?.hide()
                search_result_container?.show()
                items_displayed_text?.show()
            }
        }

        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    if (it.isNotEmpty()) {
                        recentSearches.add(query)
                        val editor = sharedPreferences.edit()
                        editor.clear()
                        editor.putString(RECENT_SEARCHES, recentSearches.joinToString(","))
                        editor.apply()
                        recentSearchAdapter.loadData(ArrayList(recentSearches))
                        mPresenter.search(it)
                    }
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                recentSearchAdapter.filter(newText ?: "")
                return false
            }

        })

        parent_scroll_view.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, _ ->
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                if (!isLoadingMoreItems && itemsSize < totalResults ) {
                    isLoadingMoreItems = true
                    mPresenter.loadMoreItems()
                }
            }
        })

        search_view.requestFocus()
        showKeyboard()
    }

    fun onSearchResult(items: ArrayList<Item>, _totalResults: Int?) {
        recent_search_recycler?.hide()
        search_result_container?.show()
        items_displayed_text?.show()
        window.decorView.clearFocus()
        itemsSize = items.size
        totalResults = _totalResults ?: 0
        total_results_text.text = getString(R.string.total_results, totalResults)
        items_displayed_text.text = getString(R.string.displaying_items_result, itemsSize, totalResults)

        val controller =
            AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_fall)
        search_result_recycler?.layoutAnimation = controller

        searchResultItemsAdapter = SearchResultItemsAdapter(items, this)
        search_result_recycler.adapter = searchResultItemsAdapter
        search_result_recycler?.scheduleLayoutAnimation()
    }

    fun onLoadMoreItemsResult(items: ArrayList<Item>, totalResults: Int?){
        isLoadingMoreItems = false
        itemsSize += items.size
        items_displayed_text.text = getString(R.string.displaying_items_result, itemsSize, totalResults)
        searchResultItemsAdapter.addItems(items)
    }

    override fun onRecentSearchClicked(query: String) {
        hideKeyboard()
        search_view.queryHint = query
        mPresenter.search(query)
    }

    override fun onItemSelected(item: Item) {
        val intent = Intent(this, ItemDetailsActivity::class.java)
        intent.putExtra(ITEM_BUNDLE_KEY, item)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
    }

    fun showPagingLoading(){
        paging_progress_bar?.show()
    }

    fun hidePagingLoading(){
        paging_progress_bar.hide()
    }

    override fun getFullscreenLoadingView(): View? {
        return full_screen_progress_bar
    }

    override fun buildPresenter() {
        mPresenter = SearchPresenter()
    }
}
