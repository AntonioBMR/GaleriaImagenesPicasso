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
/**
 * Producto realizado por AntonioBMR on 24/01/2015.
 */

public class MainActivity extends Activity {

    private GridView gv;
    Adaptador ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciarComponentes();
    }

    public void iniciarComponentes(){

        Uri ur= MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] proyeccion = {MediaStore.Images.Thumbnails._ID, MediaStore.Images.Media.DATA};
        String condicion = null;
        String[] parametros = null;
        String orden = null;
        Cursor cursor=getContentResolver().query(
                ur,
                proyeccion,
                condicion,
                parametros,
                orden);
        ad=new Adaptador(this,cursor);
        gv = (GridView)findViewById(R.id.gridView);
        gv.setAdapter(ad);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cursor cursor = (Cursor)gv.getItemAtPosition(i);
                int ruta=cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                Intent in = new Intent(MainActivity.this, Imagen.class);
                in.putExtra("ruta", ruta);
                startActivity(in);
            }
        });
        registerForContextMenu(gv);

    }
}
