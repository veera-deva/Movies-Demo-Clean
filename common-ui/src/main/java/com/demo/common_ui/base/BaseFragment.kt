package com.demo.common_ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<T : ViewBinding>(private val inflateMethod: (LayoutInflater, ViewGroup?, Boolean) -> T) :
    Fragment() {
    private var _binding: T? = null
    val binding: T get() = _binding!!
    private var snackbar: Snackbar? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflateMethod.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setStatusBarColor()
        setUpView()
        subscribeUI()
    }

    protected open fun setStatusBarColor() {
        activity?.run {
            window.statusBarColor = getColor(androidx.appcompat.R.color.background_material_dark)
        }
    }

    abstract fun setUpView()
    abstract fun subscribeUI()

    /**
     * Method to display error message to the user
     * */
    fun showError(msg: String) {
        view?.run {
            snackbar = Snackbar.make(this, msg, Snackbar.LENGTH_INDEFINITE)
            snackbar!!.setAction("Dismiss") {
                snackbar!!.dismiss()

            }
            snackbar!!.show()
        }
    }

    /**
     * Method to display a message to the user  via SnackBar
     * */
    fun showMessage(msg: String) {
        view?.run {
            hideError()
            snackbar = Snackbar.make(this, msg, Snackbar.LENGTH_SHORT)
            snackbar!!.show()
        }
    }

    /**
     * Method to dismiss the displayed message
     * */
    private fun hideError() {
        snackbar?.dismiss()
    }
}