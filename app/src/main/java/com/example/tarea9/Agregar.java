package com.example.tarea9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;

public class Agregar extends AppCompatActivity {

    EditText comida, ID;
    Button btn_agregar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        comida = (EditText) findViewById(R.id.et_comida);
        ID = (EditText) findViewById(R.id.et_id);
        btn_agregar = (Button) findViewById(R.id.btn_add);
        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar();
            }
        });
    }

    private void registrar() {
        ConeccionSQLiteHelper connection = new ConeccionSQLiteHelper(this,"bd_comida", null, 1);

        SQLiteDatabase db = connection.getWritableDatabase();

        String insert = "INSERT INTO " +Utilidades.TABLA_COMIDA + "(" + Utilidades.CAMPO_ID + ", " +
                Utilidades.CAMPO_TIPO + ") VALUES (" + ID.getText().toString() + ", '" +comida.getText().toString()+ "')";


        db.execSQL(insert);

        db.close();

        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}
