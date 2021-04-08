package com.android.mvvmdesignpoc.core.platform

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.mvvmdesignpoc.R
import com.android.mvvmdesignpoc.R.id
import com.android.mvvmdesignpoc.R.layout
import com.android.mvvmdesignpoc.core.extension.gone
import com.android.mvvmdesignpoc.core.extension.inTransaction
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

    fun showProgress() {
        ivProgress.visible()
    }

    fun hideProgress() {
        ivProgress.gone()
    }
}