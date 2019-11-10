package com.mnunez.melitest.custom_views

import android.widget.FrameLayout


class PageIndicatorHelper(viewContainer: FrameLayout) {

    var pageIndicator: PageIndicator = PageIndicator(viewContainer)

    init {
        pageIndicator.init()
    }

    fun updateData(num: Int) {
        pageIndicator.setDots(num)
        pageIndicator.initDots()
    }

}