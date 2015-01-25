package com.antonio.android.pruebapicasso;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
/**
 * Producto realizado por AntonioBMR on 24/01/2015.
 */
public class Adaptador extends CursorAdapter {



    public Adaptador(Context context,Cursor data) {
        super(context, data, true);
    }
    public static class ViewHolder {
        public ImageView iv;
    }

    @Override
    public void bindView(View convertView, Context context, Cursor cursor) {
        ViewHolder vh = null;
        if (convertView != null) {
            vh = new ViewHolder();
            vh.iv = (ImageView) convertView.findViewById(R.id.iv);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        int image_path_index = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
        Picasso.with(context).load(new File(cursor.getString(image_path_index))).into(vh.iv);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater i = LayoutInflater.from(parent.getContext());
        View v = i.inflate(R.layout.detalle, parent, false);
        return v;    }


}