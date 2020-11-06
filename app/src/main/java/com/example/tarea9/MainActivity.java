package com.example.tarea9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Connection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//= new ConeccionSQLiteHelper(this,"bd_comida", null, 1);
    ArrayList<Comida> listDatos;
    RecyclerView recycler;
    ConeccionSQLiteHelper connection;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connection = new ConeccionSQLiteHelper(getApplicationContext(),"bd_comida", null, 1);
        listDatos = new ArrayList<>();

        recycler = (RecyclerView)findViewById(R.id.recyclerv);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false ));

        consultar();

        Adapter adapter = new com.example.tarea9.Adapter(listDatos);

        ((com.example.tarea9.Adapter) adapter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,
                        listDatos.get(recycler.getChildAdapterPosition(v)).getFood(), Toast.LENGTH_SHORT).show();
            }
        });

        recycler.setAdapter(adapter);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Agregar.class));
            }
        });


    }

    private void consultar() {
        SQLiteDatabase db = connection.getReadableDatabase();

        Comida comida = null;
        listDatos = new ArrayList<Comida>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_COMIDA, null);


        while(cursor.moveToNext()){

            comida = new Comida();
            comida.setID(cursor.getInt(0));
            comida.setFood(cursor.getString(1));

            listDatos.add(comida);


        }

    }
}
