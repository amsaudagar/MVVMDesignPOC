package com.android.mvvmdesignpoc.core.extension

import android.view.View
import android.widget.ImageView
import com.android.mvvmdesignpoc.core.platform.GlideApp

/**
 * Indicates whether the view is visible or not, true if visible false otherwise
 */
fun View.isVisible() = this.visibility == View.VISIBLE

/**
 * Sets the visibility of View as visible
 */
fun View.visible() {
    this.visibility = View.VISIBLE
}

/**
 * Sets the visibility of View as gone
 */
fun View.gone() {
    this.visibility = View.GONE
}

fun ImageView.loadFromUrl(url: String, placeholder: Int, errorPlaceholder: Int) =
    GlideApp.with(this.context.applicationContext)
        .load(url)
        .circleCrop()
        .placeholder(placeholder)
        .error(errorPlaceholder)
        .into(this)