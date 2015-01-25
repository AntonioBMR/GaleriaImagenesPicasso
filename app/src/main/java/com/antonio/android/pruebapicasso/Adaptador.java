package com.antonio.android.pruebapicasso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

/**
 * Producto realizado por AntonioBMR corp.
 */
public class Adaptador extends ArrayAdapter<String> {
    private Context context;
     private int recurso;
    private ArrayList<String> rutas;
    private static LayoutInflater li;

    public Adaptador(Context context, int resource, ArrayList<String> objects) {
        super(context, resource, objects);
        this.context = context;
        this.rutas = objects;
        this.recurso = resource;
        this.li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public static class ViewHolder {
        public ImageView iv;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = li.inflate(recurso, null);
            vh = new ViewHolder();
            vh.iv = (ImageView) convertView.findViewById(R.id.iv);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        String ruta;
        ruta = rutas.get(position);
        File f=new File(ruta);
        Picasso.with(context).load(f).into(vh.iv);
        return convertView;
    }
}