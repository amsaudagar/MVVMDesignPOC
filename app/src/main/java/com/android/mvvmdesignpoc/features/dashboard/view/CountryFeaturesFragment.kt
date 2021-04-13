package com.android.mvvmdesignpoc.features.dashboard.view

import android.os.Bundle
import android.view.View
import com.android.mvvmdesignpoc.R
import com.android.mvvmdesignpoc.core.extension.gone
import com.android.mvvmdesignpoc.core.extension.loadFromUrl
import com.android.mvvmdesignpoc.core.platform.BaseFragment
import com.android.mvvmdesignpoc.features.dashboard.data.remote.response.Row
import com.android.mvvmdesignpoc.features.dashboard.view.HomeFragment.Companion.ROW_ITEM
import kotlinx.android.synthetic.main.country_feature_fragment.*

/**
 * Represents the fragment for country feature
 */
class CountryFeaturesFragment : BaseFragment() {

    override fun layoutId() = R.layout.country_feature_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(arguments != null) {
            val row = requireArguments().getParcelable<Row>(ROW_ITEM) as Row
            txtTitle.text = row.title
            txtDescription.text = row.description
            if(row.imageHref.isNullOrEmpty()) {
                image.gone()
            } else {
                image.loadFromUrl(row.imageHref!!, R.drawable.default_loader, R.drawable.default_image)
            }
            image.contentDescription = row.imageHref?:""
        }
    }
}