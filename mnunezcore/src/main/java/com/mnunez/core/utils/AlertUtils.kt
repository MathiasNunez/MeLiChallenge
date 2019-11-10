package com.mnunez.core.utils


import android.app.AlertDialog
import android.content.Context
import android.view.ContextThemeWrapper
import android.view.View
import com.mnunez.core.core.R

/**
 *
 * Helper class intended to show any type of custom alert containing a companion object (statics methods)
 *
 */
class AlertUtils {

    companion object {

        /**
         *
         * Shows an Alert with one POSITIVE button
         *
         * @param context Context to show the alert
         * @param title Alert title (optional -> default does not displays any title)
         * @param message Alert message body (optional -> default does not shows any message in alert body)
         * @param btnText Alert POSITIVE button text (optional -> default shows "OK")
         * @param cancelable Allows to cancel the alert (optional -> default is true)
         * @param onPositiveClicked Method to be executed when POSITIVE button is clicked
         * @param icon Resource id of icon to show in the title (optional -> default does not shows icon)
         * @param customView Custom View to display in the Alert body (optional -> default does not include any custom view in body)
         *
         */
        fun showOneButtonAlert(
            context: Context,
            title: String? = null, message: String? = null, btnText: String = "OK",
            onPositiveClicked: () -> Unit,
            cancelable: Boolean = true,
            icon: Int = -1,
            customView: View? = null
        ) {

            val alert = build(context, title, message, icon, customView, cancelable)
            alert.setButton(AlertDialog.BUTTON_POSITIVE, btnText) { _, _ -> onPositiveClicked() }
            alert.show()
        }

        /**
         *
         * Shows an Alert with two buttons: POSITIVE and NEGATIVE
         *
         * @param context Context to show the alert
         * @param title Alert title (optional -> default does not displays any title)
         * @param message Alert message body (optional -> default does not shows any message in alert body)
         * @param btnPositive Alert POSITIVE button text (optional -> default shows "Yes")
         * @param btnNegative Alert NEGATIVE button text (optional -> default shows "No")
         * @param onPositiveClicked Method to be executed when POSITIVE button is clicked
         * @param onNegativeClicked Method to be executed when NEGATIVE button is clicked
         * @param cancelable Allows to cancel the alert (optional -> default is true)
         * @param icon Resource id of icon to show in the title (optional -> default does not shows icon)
         * @param customView Custom View to display in the Alert body (optional -> default does not include any custom view in body)
         *
         */
        fun showTwoButtonsAlert(
            context: Context,
            title: String? = null, message: String? = null, btnPositive: String = "Yes", btnNegative: String = "No",
            onPositiveClicked: () -> Unit, onNegativeClicked: () -> Unit,
            cancelable: Boolean = true,
            icon: Int = -1,
            customView: View? = null
        ) {

            val alert = build(context, title, message, icon, customView, cancelable)
            alert.setButton(AlertDialog.BUTTON_POSITIVE, btnPositive) { _, _ -> onPositiveClicked() }
            alert.setButton(AlertDialog.BUTTON_NEGATIVE, btnNegative) { _, _ -> onNegativeClicked() }
            alert.show()
        }

        /**
         *
         * Shows an Alert with three buttons: POSITIVE, NEGATIVE and NEUTRAL
         *
         * @param context Context to show the alert
         * @param title Alert title (optional -> default does not displays any title)
         * @param message Alert message body (optional -> default does not shows any message in alert body)
         * @param btnPositive Alert POSITIVE button text (optional -> default shows "Yes")
         * @param btnNegative Alert NEGATIVE button text (optional -> default shows "No")
         * @param btnNeutral Alert NEUTRAL button text
         * @param onPositiveClicked Method to be executed when POSITIVE button is clicked
         * @param onNegativeClicked Method to be executed when NEGATIVE button is clicked
         * @param onNeutralClicked Method to be executed when NEUTRAL button is clicked
         * @param cancelable Allows to cancel the alert (optional -> default is true)
         * @param icon Resource id of icon to show in the title (optional -> default does not shows icon)
         * @param customView Custom View to display in the Alert body (optional -> default does not include any custom view in body)
         *
         */
        fun showThreeButtonsAlert(
            context: Context,
            title: String? = null,
            message: String? = null,
            btnPositive: String = "Yes",
            btnNegative: String = "No",
            btnNeutral: String,
            onPositiveClicked: () -> Unit,
            onNegativeClicked: () -> Unit,
            onNeutralClicked: () -> Unit,
            cancelable: Boolean = true,
            icon: Int = -1,
            customView: View? = null
        ) {

            val alert = build(context, title, message, icon, customView, cancelable)
            alert.setButton(AlertDialog.BUTTON_POSITIVE, btnPositive) { _, _ -> onPositiveClicked() }
            alert.setButton(AlertDialog.BUTTON_NEGATIVE, btnNegative) { _, _ -> onNegativeClicked() }
            alert.setButton(AlertDialog.BUTTON_NEUTRAL, btnNeutral) { _, _ -> onNeutralClicked() }
            alert.show()
        }

        private fun build(
            context: Context,
            title: String?,
            message: String?,
            icon: Int,
            customView: View?,
            cancelable: Boolean
        ): AlertDialog {
            val alert =
                AlertDialog.Builder(ContextThemeWrapper(context, android.R.style.Theme_Material_Light_Dialog)).create()
            title.let { alert.setTitle(title) }
            message.let { alert.setMessage(message) }
            if (icon != -1) alert.setIcon(icon)
            customView?.let { alert.setView(customView) }
            alert.setCancelable(cancelable)
            return alert
        }

    }

}