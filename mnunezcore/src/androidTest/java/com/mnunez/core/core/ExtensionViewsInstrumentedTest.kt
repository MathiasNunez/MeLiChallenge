package com.mnunez.core.core

import android.view.View
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.mnunez.core.extensions.hide
import com.mnunez.core.extensions.show
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class ExtensionViewsInstrumentedTest {

    val appContext = InstrumentationRegistry.getTargetContext()

    @Test
    fun test_ViewsVisibility() {
        val view = View(appContext)
        view.hide()
        assertEquals(view.visibility, View.GONE)
        view.show()
        assertEquals(view.visibility, View.VISIBLE)
    }
}
