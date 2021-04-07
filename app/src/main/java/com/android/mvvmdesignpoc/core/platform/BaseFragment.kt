package com.android.mvvmdesignpoc.core.platform

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.mvvmdesignpoc.R

/**
 * Represents the base fragment which will provide the common features to all child fragments
 */
abstract class BaseFragment : Fragment() {

    /**
     * Holds the instance of progress dialog
     */
    private var dialog: ProgressDialog? = null

    /**
     * Returns the resource id for the layout to render in the fragment
     */
    abstract fun layoutId(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(layoutId(), container, false)
    }

    /**
     * Handles the back press event
     */
    open fun onBackPressed() {
        var count = 0
        count = parentFragmentManager.backStackEntryCount ?: 0
        if (count == 0) {
            activity?.onBackPressed()
        } else {
            parentFragmentManager.popBackStack()
        }
    }

    override fun onPause() {
        super.onPause()
        dialog?.dismiss()
    }

    /**
     * Displays the progress dialog
     */
    fun showProgressDialog() {
        if (dialog == null) {
            activity?.let {
                createDialog()
                if (!it.isFinishing && !it.isDestroyed) {
                    dialog?.show()
                }
            }
        }
    }

    /**
     * Hides the progress dialog
     */
    fun hideProgressDialog() {
        if (dialog?.isShowing == true) {
            dialog?.dismiss()
        }
        dialog = null
    }

    private fun createDialog() {
        dialog = ProgressDialog(requireActivity())
        dialog?.setTitle(getString(R.string.loading))
        dialog?.setCancelable(false)
    }

    /**
     * Returns the title for the fragment
     */
    open fun getTitle(): String = ""

    /**
     * Displays the error message if API fails
     */
    open fun renderFailure(message: String) {
        //TO Show generic failure message
        Toast.makeText(requireActivity(), message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        hideProgressDialog()
        super.onDestroy()
    }
}