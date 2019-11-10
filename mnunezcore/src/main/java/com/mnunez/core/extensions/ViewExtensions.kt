package com.mnunez.core.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 *
 * Views useful extensions
 *
 */

/**
 *
 * @return true if view is visible, false otherwise
 *
 */
fun View.isVisible() = visibility == View.VISIBLE

/**
 *
 * Shows a view
 *
 */
fun View.show() {
    visibility = View.VISIBLE
}

/**
 *
 * Hides a view
 *
 */
fun View.hide() {
    visibility = View.GONE
}

/**
 *
 * Hides a view using [View.INVISIBLE]
 *
 */
fun View.invisible() {
    visibility = View.INVISIBLE
}

/**
 *
 * Shows a list of views
 *
 * @param views list of views to show at once
 *
 */
fun showViews(vararg views: View) {
    for (v in views)
        v.show()
}

/**
 *
 * Hides a list of views
 *
 * @param views list of views to hide at once
 *
 */
fun hideViews(vararg views: View) {
    for (v in views)
        v.hide()
}

/**
 * Inflates a layout
 *
 * @param layoutRes
 *
 */
fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

/**
 * Show the view if [condition] returns true
 * (visibility = View.VISIBLE)
 */
fun View.showIf(condition: () -> Boolean): View {
    if (visibility != View.VISIBLE && condition()) {
        visibility = View.VISIBLE
    }
    return this
}

/**
 * Hide the view if [condition] returns true
 * (visibility = View.INVISIBLE)
 */
fun View.hideIf(condition: () -> Boolean): View {
    if (visibility != View.INVISIBLE && condition()) {
        visibility = View.INVISIBLE
    }
    return this
}

/**
 * Remove the view if [condition] returns true
 * (visibility = View.GONE)
 */
inline fun View.removeIf(condition: () -> Boolean): View {
    if (visibility != View.GONE && condition()) {
        visibility = View.GONE
    }
    return this
}
