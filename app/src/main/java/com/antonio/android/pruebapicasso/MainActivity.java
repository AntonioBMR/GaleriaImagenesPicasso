package com.antonio.android.pruebapicasso;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * Producto realizado por AntonioBMR on 24/01/2015.
 */

public class MainActivity extends Activity {

    private GridView gv;
    private ArrayList<String> rutas;
    private Adaptador ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Uri uri= MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        rutas=new ArrayList<String>();
        String[] proyeccion =null;
        String condicion = null;
        String[] parametros = null;
        String orden = null;
        Cursor cursor=getContentResolver().query(
                uri,
                proyeccion,
                condicion,
                parametros,
                orden);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String nombre = cursor.getString(1);
            rutas.add(nombre);
            cursor.moveToNext();
        }
        gv=(GridView)findViewById(R.id.gridView);
        ad = new Adaptador(this,R.layout.detalle,rutas);
        gv.setAdapter(ad);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String ruta=rutas.get(i);
                Intent in = new Intent(MainActivity.this, Imagen.class);
                in.putExtra("ruta", ruta);
                startActivity(in);
            }
        });

    }
}
