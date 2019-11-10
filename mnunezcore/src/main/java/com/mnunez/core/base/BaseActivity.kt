package com.mnunez.core.base

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.mnunez.core.extensions.hide
import com.mnunez.core.extensions.show
import com.mnunez.core.permissions.PermissionsCallback

/**
 *
 * This is the BaseActivity extend from this in any other Activity
 * It contains generic methods and link to the BasePresenter
 *
 */
abstract class BaseActivity<V : BaseView, P : BasePresenter<V>> : AppCompatActivity(),
    BaseView{

    companion object {
        private const val PERMISSION_REQUEST_CODE: Int = 665
    }

    private lateinit var callback: PermissionsCallback
    private var neededPermissions: MutableList<String> = mutableListOf()

    lateinit var mPresenter: P

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buildPresenter()
        mPresenter.onAttachView(this as V)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDetachView()
    }

    fun showFullscreenLoadingView(view: View?) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        view?.show()
    }

    fun hideFullscreenLoadingView(view: View?) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        view?.hide()
    }

    abstract fun buildPresenter()

    /**
     *
     * It request all of the given permissions, result will be handled here as well
     *
     * @param permissions the list of permissions to request
     * @param permissionsCallback callback to be used for handling result
     *
     */
    fun requestPermission(permissions: Array<String>, permissionsCallback: PermissionsCallback) {
        callback = permissionsCallback
        neededPermissions.clear()
        permissions
            .filter { ActivityCompat.checkSelfPermission(applicationContext, it) != PackageManager.PERMISSION_GRANTED }
            .forEach { neededPermissions.add(it) }
        when (neededPermissions.size) {
            0 -> {
                callback.granted()
            }
            else -> {
                ActivityCompat.requestPermissions(this, neededPermissions.toTypedArray(), PERMISSION_REQUEST_CODE)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (grantResults.isNotEmpty()) {
            when (requestCode) {
                PERMISSION_REQUEST_CODE -> {
                    var cantDenied = 0
                    grantResults.filter { it != PackageManager.PERMISSION_GRANTED }.forEach { _ -> cantDenied += 1 }
                    if (cantDenied > 0) {
                        val rationalePermission: MutableList<String> = mutableListOf()
                        permissions.filter { !shouldShowRequestPermissionRationale(it) }.forEach { rationalePermission.add(it) }
                        if (rationalePermission.size > 0) {
                            callback.requestRationale(rationalePermission)
                        } else {
                            callback.denied(cantDenied)
                        }
                    } else {
                        callback.granted()
                    }
                }
            }
        }
    }
}