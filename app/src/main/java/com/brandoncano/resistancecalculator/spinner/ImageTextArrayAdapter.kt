package com.brandoncano.resistancecalculator.spinner

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.brandoncano.resistancecalculator.R

/**
 * Job: Custom array adapter to have an ImageView and a TextView.
 */
class ImageTextArrayAdapter(context: Context, private var items: Array<SpinnerItem>) :
    ArrayAdapter<SpinnerItem?>(context, R.layout.spinner_value_layout, items) {

    private var inflater: LayoutInflater = (context as Activity).layoutInflater
    private var holder: ViewHolder? = null

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }

    private fun getCustomView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val (name, logo) = items[position]
        var row = convertView
        if (row == null) {
            holder = ViewHolder()
            row = inflater.inflate(R.layout.spinner_value_layout, parent, false)
            holder?.name = row.findViewById(R.id.spinner_name)
            holder?.img = row.findViewById(R.id.spinner_img)
            row.tag = holder
        } else {
            holder = row.tag as ViewHolder
        }
        holder?.name?.text = name
        holder?.img?.setBackgroundResource(logo)
        return row!!
    }

    data class ViewHolder(
        var name: TextView? = null,
        var img: ImageView? = null
    )
}
