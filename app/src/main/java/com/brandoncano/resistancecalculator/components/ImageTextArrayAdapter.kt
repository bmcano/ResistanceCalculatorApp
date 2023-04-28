package com.brandoncano.resistancecalculator.components

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
 * Job: Custom array adapter to hold an ImageView and a TextView.
 */
class ImageTextArrayAdapter(context: Context, private val items: Array<SpinnerItem>) :
    ArrayAdapter<SpinnerItem>(context, R.layout.spinner_value_layout, items) {

    private val inflater: LayoutInflater = (context as Activity).layoutInflater

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }

    private fun getCustomView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val (name, logo) = items[position]
        val row = convertView ?: inflater.inflate(R.layout.spinner_value_layout, parent, false)
        val holder = row.tag as? ViewHolder ?: ViewHolder(
            row.findViewById(R.id.spinner_name),
            row.findViewById(R.id.spinner_img)
        )
        holder.name?.text = name
        holder.img?.setBackgroundResource(logo)
        return row
    }

    data class ViewHolder(val name: TextView?, val img: ImageView?)
}
