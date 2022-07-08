package com.brandoncano.resistancecalculator.spinner;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.brandoncano.resistancecalculator.R;

/**
 * Job: customer array adapter to have an ImageView and a TextView
 *
 * @author Brandon
 */

// TODO - convert to Kotlin

public class ImageTextArrayAdapter extends ArrayAdapter<SpinnerItem> {
    LayoutInflater inflater;
    SpinnerItem[] objects;
    ViewHolder holder = null;

    public ImageTextArrayAdapter(Context context, SpinnerItem[] objects) {
        super(context, R.layout.spinner_value_layout, objects);
        inflater = ((Activity) context).getLayoutInflater();
        this.objects = objects;
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        SpinnerItem listItemAddProg = objects[position];
        View row = convertView;

        if (null == row) {
            holder = new ViewHolder();
            row = inflater.inflate(R.layout.spinner_value_layout, parent, false);
            holder.name = row.findViewById(R.id.spinner_name);
            holder.img = row.findViewById(R.id.spinner_img);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        holder.name.setText(listItemAddProg.getName());
        holder.img.setBackgroundResource(listItemAddProg.getLogo());
        return row;
    }

    static class ViewHolder {
        TextView name;
        ImageView img;
    }
}
