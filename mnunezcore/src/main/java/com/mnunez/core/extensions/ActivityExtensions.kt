package com.mnunez.core.extensions

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Hides the keyboard of the active Activity
 */
fun Activity.hideKeyboard() {
    hideKeyboard(if (currentFocus == null) View(this) else currentFocus)
}

/**
 * Hides the keyboard of an specific view
 * @param view actual view to hide keyboard at
 */
fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

/**
 * Shows the keyboard in the active Activity
 */
fun Activity.showKeyboard() {
    showKeyboard(if (currentFocus == null) View(this) else currentFocus)
}

/**
 * Shows the keyboard of an specific view
 * @param view actual view to show keyboard at
 */
fun Context.showKeyboard(view: View) {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    view.requestFocus()
    val handler = Handler()
    handler.postDelayed({
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }, 100)
}