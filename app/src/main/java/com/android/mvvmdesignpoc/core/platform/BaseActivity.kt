package com.android.mvvmdesignpoc.core.platform

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.mvvmdesignpoc.R
import com.android.mvvmdesignpoc.R.layout
import com.android.mvvmdesignpoc.core.extension.gone
import com.android.mvvmdesignpoc.core.extension.visible
import kotlinx.android.synthetic.main.activity_layout.*

/**
 * Represents the base activity which will provide common features to all child activities
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(layoutId())

        GlideApp.with(this).asGif()
            .load(R.drawable.loader)
            .into(ivProgress)

        init()
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
     * Displays the progress dialog
     */
    fun showProgress() {
        ivProgress?.visible()
    }

    /**
     * Hides the progress dialog
     */
    fun hideProgress() {
        ivProgress?.gone()
    }
}