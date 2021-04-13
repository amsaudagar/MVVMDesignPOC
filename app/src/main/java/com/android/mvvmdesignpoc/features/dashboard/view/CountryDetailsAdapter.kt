package com.android.mvvmdesignpoc.features.dashboard.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.mvvmdesignpoc.R
import com.android.mvvmdesignpoc.core.extension.gone
import com.android.mvvmdesignpoc.core.extension.loadFromUrl
import com.android.mvvmdesignpoc.features.dashboard.data.remote.response.Row
import kotlinx.android.synthetic.main.row_item_view.view.*

class CountryDetailsAdapter(private val context: Context,
                            private val list: ArrayList<Row>,
                            private val itemSelectListener : IOnItemSelectListener) : RecyclerView.Adapter<CountryDetailsAdapter.CountryDetailRowViewHolder>() {

    private var inflater: LayoutInflater? = null

    /**
     * Callback interface to display country feature details
     */
    interface IOnItemSelectListener {
        fun onRowItemSelected(row : Row)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryDetailRowViewHolder {
        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?
        val layoutView = inflater!!.inflate(R.layout.row_item_view, parent, false)
        return CountryDetailRowViewHolder(layoutView)
    }

    override fun onBindViewHolder(viewHolder: CountryDetailRowViewHolder, position: Int) {
        val row = list[position]
        viewHolder.txtTitle.text = row.title?:""
        viewHolder.txtDescription.text = row.description?:""
        if(row.imageHref.isNullOrEmpty()) {
            viewHolder.image.gone()
        } else {
            viewHolder.image.loadFromUrl(row.imageHref!!, R.drawable.default_loader, R.drawable.default_image)
        }
        viewHolder.image.contentDescription = row.imageHref?:""

        viewHolder.cardView.setOnClickListener {
            itemSelectListener.onRowItemSelected(list[position])
        }
    }

    inner class CountryDetailRowViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var cardView: View = itemView.cardView
        internal var txtTitle: TextView = itemView.txtTitle
        internal var txtDescription: TextView = itemView.txtDescription
        internal var image: ImageView = itemView.image
    }
}