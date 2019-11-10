package com.mnunez.melitest.custom_views

import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import com.mnunez.melitest.R

class PageIndicator(private val flIndicator: FrameLayout) {

    private lateinit var llDots: LinearLayout
    var savedPos: Int = -1

    fun init() {
        val params = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.gravity = Gravity.CENTER
        llDots = LinearLayout(flIndicator.context)
        llDots.layoutParams = params
        this.flIndicator.addView(llDots)
    }

    fun initDots() {
        savedPos = -1
        updateDots(0)
    }

     fun updateDots(pos: Int) {
        if (savedPos == pos) return
        savedPos = pos
        for (i in 0 until llDots.childCount)
            (llDots.getChildAt(i) as ImageView).setImageResource(if (i == pos) R.drawable.page_indicator_selected else R.drawable.page_indicator_default)
    }

    fun setDots(itemCount: Int) {
        if (itemCount <= 0)
            return
        llDots.removeAllViews()
        val margin = llDots.context.resources.getDimension(R.dimen.margin_minimum).toInt()
        val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        params.setMargins(margin, margin, margin, margin)
        for (i in 0 until itemCount) {
            val imageView = ImageView(llDots.context)
            imageView.setImageResource(R.drawable.page_indicator_default)
            imageView.layoutParams = params
            llDots.addView(imageView)
        }
    }

}