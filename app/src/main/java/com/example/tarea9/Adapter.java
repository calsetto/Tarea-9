package com.example.tarea9;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> implements View.OnClickListener {

    ArrayList<Comida> listDatos;
    private View.OnClickListener listener;

    public Adapter(ArrayList<Comida> listDatos) {
        this.listDatos = listDatos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.asignarDatos(listDatos.get(position).getID().toString());
        holder.asignarDatos(listDatos.get(position).getFood());
    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView datos;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            datos = (TextView) itemView.findViewById(R.id.comida);
        }

        public void asignarDatos(String s) {
            datos.setText(s);
        }
    }
}
