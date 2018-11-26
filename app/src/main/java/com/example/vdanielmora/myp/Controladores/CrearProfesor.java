package com.example.vdanielmora.myp.Controladores;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.vdanielmora.myp.Helper.ValidacionEntradas;
import com.example.vdanielmora.myp.Modelo.Materia;
import com.example.vdanielmora.myp.Modelo.Profesor;
import com.example.vdanielmora.myp.Persistencia.BaseDatos;
import com.example.vdanielmora.myp.R;

import java.util.ArrayList;

public class CrearProfesor extends AppCompatActivity {

    private EditText mId, mNombre, mFacultad;
    private Button btnCrear;
    private ValidacionEntradas validacionEntradas;
    private BaseDatos baseDatos = new BaseDatos(this);
    private Profesor profesor;
    private Spinner comboMaterias;
    private ArrayList<String> listaNombres;
    private ArrayList<Materia> listaObjetos;
    private ArrayList<Materia> materiasDelProfesor;
    private Materia materia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_profesor);
        mId = (EditText) findViewById(R.id.txtIdP);
        mNombre = (EditText) findViewById(R.id.txtNombreP);
        mFacultad = (EditText) findViewById(R.id.txtFacultadP);
        btnCrear = (Button) findViewById(R.id.btnCrearP);
        comboMaterias = (Spinner) findViewById(R.id.comboMaterias);

        listaNombres = new ArrayList<>();

        validacionEntradas = new ValidacionEntradas(this);
        profesor = new Profesor();
        materia = null;
        listaObjetos = baseDatos.obtenerTodasLasMaterias();
        materiasDelProfesor = new ArrayList<>();
        listaNombres.add("Seleccione (Grupo - Materia");
        btnCrear.setEnabled(false);

        for (int i = 0; i<listaObjetos.size();i++){
            listaNombres.add(listaObjetos.get(i).getGrupo()+" - "+listaObjetos.get(i).getNombre());
        }

        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,android.R.layout.simple_spinner_item, listaNombres);
        comboMaterias.setAdapter(adaptador);



        comboMaterias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0){
                    btnCrear.setEnabled(true);
                    materia = listaObjetos.get(position-1);
                    materiasDelProfesor.add(materia);

                }else {
                    Toast.makeText(CrearProfesor.this, "SELECCIONE ALGUNA MATERIA", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llenarBD();
                profesor.setMaterias(materiasDelProfesor);
                String mensaje = baseDatos.añadirMateriaProfesor(materia.getId(), Integer.parseInt(mId.getText().toString()));
                Toast.makeText(CrearProfesor.this, mensaje, Toast.LENGTH_LONG).show();
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
