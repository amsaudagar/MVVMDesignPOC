package com.android.mvvmdesignpoc.core.extension

import android.view.View

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