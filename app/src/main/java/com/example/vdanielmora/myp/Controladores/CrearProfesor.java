package com.example.vdanielmora.myp.Controladores;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.vdanielmora.myp.Helper.ValidacionEntradas;
import com.example.vdanielmora.myp.Modelo.Materia;
import com.example.vdanielmora.myp.Modelo.Profesor;
import com.example.vdanielmora.myp.Persistencia.BaseDatos;
import com.example.vdanielmora.myp.R;

import java.util.ArrayList;
import java.util.List;

public class CrearProfesor extends AppCompatActivity {

    private EditText mId, mNombre, mFacultad;
    private Button btnCrear, btnGuardar;
    private ValidacionEntradas validacionEntradas;
    private BaseDatos baseDatos = new BaseDatos(this);
    private Profesor profesor;
    private ListView listaMaterias;
    private ArrayList<String> listaNombres;
    private ArrayList<Materia> listaObjetos;
    private Materia materia;
    private ArrayAdapter adaptador;
    private int idSeleccionada;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_profesor);
        mId = (EditText) findViewById(R.id.txtIdP);
        mNombre = (EditText) findViewById(R.id.txtNombreP);
        mFacultad = (EditText) findViewById(R.id.txtFacultadP);
        btnCrear = (Button) findViewById(R.id.btnCrearP);
        btnGuardar = (Button) findViewById(R.id.btnGuardarNombre);
        listaMaterias = (ListView) findViewById(R.id.listMateriasP);



        validacionEntradas = new ValidacionEntradas(this);
        profesor = new Profesor();
        materia = null;
        listaObjetos = baseDatos.obtenerTodasLasMaterias();
        idSeleccionada = 0;
        listaNombres = new ArrayList<>();

        for (int i = 0; i<listaObjetos.size();i++){
            listaNombres.add(listaObjetos.get(i).getGrupo()+" - "+listaObjetos.get(i).getNombre());
        }

        adaptador = new ArrayAdapter(this ,android.R.layout.simple_list_item_1, listaNombres);
        listaMaterias.setAdapter(adaptador);

        listaMaterias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                materia = listaObjetos.get(position);
                idSeleccionada = Integer.parseInt(mId.getText().toString().trim());
                String mensaje = baseDatos.añadirMateriaProfesor(materia.getId(),idSeleccionada);
                Toast.makeText(CrearProfesor.this,  mensaje, Toast.LENGTH_SHORT).show();
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llenarBD();
                btnGuardar.setEnabled(false);
            }
        });

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CrearProfesor.this, "Profesor creado", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),MYPmain.class);
                startActivity(intent);
            }
        });

    }

    private void llenarBD(){
        if (!validacionEntradas.validacionCamposVacios(mId, "Ingrese el ID del profesor")){
            return;
        }
        if(!validacionEntradas.validacionCamposVacios(mNombre, "Ingrese el nombre del profesor")){
            return;
        }
        if(!validacionEntradas.validacionCamposVacios(mFacultad, "Ingrese la facultad a la cual pertenece el profesor")) {
            return;
        }else{
            profesor.setId(Integer.parseInt(mId.getText().toString().trim()));
            profesor.setNombre(mNombre.getText().toString().trim().toLowerCase());
            profesor.setFacultad(mFacultad.getText().toString().trim());
            baseDatos.añadirProfesor(profesor);
        }

        Toast.makeText(this, "Profesor creado", Toast.LENGTH_SHORT).show();
    }

}
