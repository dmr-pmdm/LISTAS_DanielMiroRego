package com.example.listas_danielmirorego;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView txtCiclo;
    private Spinner spinCursos, spinCiclos;
    private EditText edtNombre;
    private Button btnGuardar, btnListar;
    private String[] cursos, ciclos;
    private String curso, ciclo, nombre, seleccion_menu;
    private int imgAlumno;
    private List<Alumno> alumnos = new ArrayList<>();
    private ListView listAlumnos, menu_contextual;
    private AdaptadorPersonalizado adaptadorLista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCiclo = findViewById(R.id.txtCiclo);
        spinCursos = findViewById(R.id.spinCursos);
        spinCiclos = findViewById(R.id.spinCiclos);
        edtNombre = findViewById(R.id.edtNombre);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnListar = findViewById(R.id.btnListar);
        cursos = getResources().getStringArray(R.array.cursos);
        ciclos = getResources().getStringArray(R.array.ciclos);
        listAlumnos = findViewById(R.id.listAlumnos);
        menu_contextual = findViewById(R.id.listAlumnos);
        registerForContextMenu(menu_contextual);

        ArrayAdapter<String> adaptadorCur = new ArrayAdapter<>(
                MainActivity.this,
                android.R.layout.simple_spinner_item,
                cursos
        );
        ArrayAdapter<String> adaptadorCic = new ArrayAdapter<>(
                MainActivity.this,
                android.R.layout.simple_spinner_item,
                ciclos
        );
        spinCursos.setAdapter(adaptadorCur);
        spinCiclos.setAdapter(adaptadorCic);

        spinCursos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                curso = parent.getItemAtPosition(position).toString();
                if (curso.equalsIgnoreCase("Ciclos")) {
                    txtCiclo.setVisibility(View.VISIBLE);
                    spinCiclos.setVisibility(View.VISIBLE);
                } else {
                    ciclo = null;
                    txtCiclo.setVisibility(View.GONE);
                    spinCiclos.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinCiclos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ciclo = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = edtNombre.getText().toString();
                if (!nombre.isEmpty()) {
                    if (curso.equalsIgnoreCase("ESO")) {
                        imgAlumno = R.drawable.eso;
                    } else {
                        imgAlumno = R.drawable.otros;
                    }
                    Alumno a = new Alumno(nombre, curso, ciclo, imgAlumno);
                    alumnos.add(a);
                    Toast.makeText(MainActivity.this, R.string.nuevo_elemento, Toast.LENGTH_SHORT).show();
                    edtNombre.setText("");
                } else {
                    Toast.makeText(MainActivity.this, R.string.no_nombre, Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!alumnos.isEmpty()) {
                    adaptadorLista = new AdaptadorPersonalizado(
                            MainActivity.this, alumnos);
                    listAlumnos.setAdapter(adaptadorLista);
                    btnGuardar.setEnabled(false);
                } else {
                    Toast.makeText(MainActivity.this, R.string.lista_vacia, Toast.LENGTH_SHORT).show();
                }
            }
        });
        listAlumnos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (alumnos.get(position).getCiclo() != null) {
                    Toast.makeText(MainActivity.this,
                            "Alumnno: " + alumnos.get(position).getNombre() +
                                    "\nCurso: " + alumnos.get(position).getCurso() +
                                    "\nCiclo: " + alumnos.get(position).getCiclo(),
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this,
                            "Alumnno: " + alumnos.get(position).getNombre() +
                                    "\nCurso: " + alumnos.get(position).getCurso(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        int id = v.getId();
        MenuInflater inflater = getMenuInflater();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        Alumno a = (Alumno) listAlumnos.getAdapter().getItem(info.position);
        seleccion_menu = a.getNombre();
        menu.setHeaderTitle(seleccion_menu);
        inflater.inflate(R.menu.menu_contextual, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        ContextMenu.ContextMenuInfo contextMenuInfo = item.getMenuInfo();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) contextMenuInfo;
        switch (id) {
            case R.id.opEliminar:
                alumnos.remove(adaptadorLista.getItem(info.position));
                if (alumnos.isEmpty()) {
                    Toast.makeText(this, R.string.lista_vacia, Toast.LENGTH_SHORT).show();
                }
                adaptadorLista = new AdaptadorPersonalizado(
                        MainActivity.this, alumnos);
                listAlumnos.setAdapter(adaptadorLista);
                return true;
            case R.id.opOtro:
                Toast.makeText(this, R.string.construccion, Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
