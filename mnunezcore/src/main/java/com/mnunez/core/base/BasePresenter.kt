package com.mnunez.core.base

import android.util.Log
import androidx.annotation.CallSuper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.concurrent.ConcurrentHashMap

/**
 *
 * This is the BasePresenter, it already have the Activity link and error handling from api
 * Extend from this class in any presenter
 *
 */
abstract class BasePresenter<V : BaseView> {

    private val compositionMap: ConcurrentHashMap<Any, Disposable> = ConcurrentHashMap()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    open var view: V? = null

    /**
     * Attaches a [BaseView] to this Presenter.
     */
    fun onAttachView(v: V) {
        this.view = v
    }

    /**
     * Removes a [BaseView] from this Presenter.
     */
    fun onDetachView() {
        this.view = null
        disposeSubscriptions()
    }

    /**
     * Adds a new disposable object to the [CompositeDisposable] handled by this class
     *
     * @param disposable The disposable to add
     */
    @CallSuper
    protected open fun addDisposableToComposition(disposable: Disposable): Boolean {
        if (!compositeDisposable.isDisposed && !disposable.isDisposed) {
            return compositeDisposable.add(disposable)
        } else {
            if (compositeDisposable.isDisposed) {
                Log.w("BasePresenter",
                        "Attempted to add a disposable to this mPresenter but the composite disposable " +
                                "handled by it was previosuly disposed"
                )
            } else {
                Log.w("BasePresenter", "Attempted to add a disposable to this mPresenter but the disposable itself was already disposed")
            }

        }
        return false
    }

    /**
     * Dispose of all disposable objects added through [addDisposableToComposition]
     */
    @CallSuper
    protected open fun disposeSubscriptions() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
            compositionMap.clear()
        }
    }

    abstract fun handleApiError(it: Throwable, retryAction: () -> Unit = {},
                                customErrorAction: () -> Unit = {},
                                showDefaultError: Boolean = true)

}