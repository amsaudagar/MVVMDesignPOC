package com.android.mvvmdesignpoc.core.platform

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.mvvmdesignpoc.R
import com.android.mvvmdesignpoc.R.id
import com.android.mvvmdesignpoc.R.layout
import com.android.mvvmdesignpoc.core.extension.inTransaction

/**
 * Represents the base activity which will provide common features to all child activities
 */
abstract class BaseActivity : AppCompatActivity() {

    private var dialog : ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(layoutId())

        init()

        addFragment()
    }

    /**
     * Returns the instance of fragment to be loaded in activity
     */
    open fun fragment(): BaseFragment? = null

    /**
     * Returns the layout resource id
     */
    open fun layoutId(): Int = layout.activity_layout

    /**
     * Initializes the view and resources
     */
    abstract fun init()

    /**
     * Adds the default fragment
     */
    open fun addFragment() {
        val fragment = fragment()
        if(fragment != null) {
            supportFragmentManager.inTransaction {
                add(id.fragmentContainer, fragment)
            }
        }
    }

    /**
     * Adds the given fragment
     *
     * @return fragment - fragment to be added
     */
    fun addFragment(fragment: BaseFragment) {
        supportFragmentManager.inTransaction {
            add(id.fragmentContainer, fragment)
        }
    }

    /**
     * Displays the progress dialog
     */
    fun showProgressDialog() {
        if (dialog == null) {
            this@BaseActivity.let {
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
        dialog = ProgressDialog(this@BaseActivity)
        dialog?.setTitle(getString(R.string.loading))
        dialog?.setCancelable(false)
    }
}