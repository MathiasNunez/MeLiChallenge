package com.mnunez.melitest.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.mnunez.core.extensions.hide
import com.mnunez.core.extensions.toCurrencyString
import com.mnunez.melitest.R
import com.mnunez.melitest.adapters.ItemDescriptionAdapter
import com.mnunez.melitest.adapters.ItemImagesPager
import com.mnunez.melitest.adapters.SearchResultItemsAdapter
import com.mnunez.melitest.base.MLBaseActivity
import com.mnunez.melitest.custom_views.PageIndicatorHelper
import com.mnunez.melitest.models.FullItem
import com.mnunez.melitest.models.Item
import com.mnunez.melitest.presenters.ItemDetailsPresenter
import kotlinx.android.synthetic.main.activity_item_details.*


class ItemDetailsActivity : MLBaseActivity<ItemDetailsActivity, ItemDetailsPresenter>(), SearchResultItemsAdapter.SearchResultListener {

    private lateinit var pageIndicatorHelper: PageIndicatorHelper
    private var item: Item? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_details)

        pageIndicatorHelper = PageIndicatorHelper(images_page_indicator)
        item = intent?.getParcelableExtra(SearchActivity.ITEM_BUNDLE_KEY)
        item?.id?.let {
            mPresenter.getFullItem(it)
        }

    }

    fun onItemResult(fullItem: FullItem, sellerItems: ArrayList<Item>?) {
        val images = ArrayList(fullItem.images?.map { it.url } ?: arrayListOf())
        items_image_pager?.adapter = ItemImagesPager(images = images, context = this)
        pageIndicatorHelper.updateData(images.size)

        items_image_pager?.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                pageIndicatorHelper.pageIndicator.updateDots(position)
            }
        })

        price_name?.text = item?.title
        price_text?.text = item?.price?.toCurrencyString()
        price_installments?.text = getString(
            R.string.installments_amount,
            item?.installments?.quantity,
            item?.installments?.amount?.toCurrencyString()
        )
        description_recycler?.adapter = ItemDescriptionAdapter(fullItem.attributes ?: arrayListOf())
        description_recycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        if (sellerItems == null || sellerItems.isEmpty()) {
            more_seller_label?.hide()
        } else {
            more_seller_items_recycler?.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            val adapter = SearchResultItemsAdapter(sellerItems, this)
            more_seller_items_recycler?.adapter = adapter
        }
    }

    override fun onItemSelected(item: Item) {
        val intent = Intent(this, ItemDetailsActivity::class.java)
        intent.putExtra(SearchActivity.ITEM_BUNDLE_KEY, item)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
    }

    override fun getFullscreenLoadingView(): View? {
        return full_screen_progress_bar
    }

    override fun buildPresenter() {
        mPresenter = ItemDetailsPresenter()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)

    }
}