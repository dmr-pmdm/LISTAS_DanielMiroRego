package com.example.listas_danielmirorego;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorPersonalizado extends ArrayAdapter {
    private Activity context;
    private List<Alumno> alumnos;

    public AdaptadorPersonalizado(@NonNull Activity context, List<Alumno> alumnos) {
        super(context, R.layout.layout_lista, alumnos);
        this.context = context;
        this.alumnos = alumnos;
    }

    static class ViewHolder {
        LinearLayout llLista;
        TextView txtNombre;
        ImageView imgAlumno;
        TextView txtCurso;
        TextView txtCiclo;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View fila = convertView;
        ViewHolder holder;

        if (fila == null) {
            LayoutInflater inflador = context.getLayoutInflater();
            fila = inflador.inflate(R.layout.layout_lista, null);

            holder = new ViewHolder();
            holder.llLista = fila.findViewById(R.id.llLista);
            holder.txtNombre = fila.findViewById(R.id.txtNombre);
            holder.imgAlumno = fila.findViewById(R.id.imgAlumno);
            holder.txtCurso = fila.findViewById(R.id.txtCurso);
            holder.txtCiclo = fila.findViewById(R.id.txtCiclo);

            fila.setTag(holder);
        } else {
            holder = (ViewHolder) fila.getTag();
        }

        holder.txtNombre.setText(alumnos.get(position).getNombre());
        holder.imgAlumno.setImageResource(alumnos.get(position).getImagen());
        holder.txtCurso.setText(alumnos.get(position).getCurso());
        holder.txtCiclo.setText(alumnos.get(position).getCiclo());

        return fila;
    }
}
