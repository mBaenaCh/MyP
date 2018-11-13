package com.example.vdanielmora.myp.Controladores;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vdanielmora.myp.Helper.ValidacionEntradas;
import com.example.vdanielmora.myp.Modelo.Profesor;
import com.example.vdanielmora.myp.Persistencia.BaseDatos;
import com.example.vdanielmora.myp.R;

public class CrearProfesor extends AppCompatActivity {

    private EditText mId,mNombre,mFacultad;
    private Button btnCrear;
    private ValidacionEntradas validacionEntradas;
    private BaseDatos baseDatos = new BaseDatos(this);
    private Profesor profesor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_profesor);
        mId = (EditText) findViewById(R.id.txtIdP);
        mNombre = (EditText) findViewById(R.id.txtNombreP);
        mFacultad = (EditText) findViewById(R.id.txtFacultadP);
        btnCrear = (Button) findViewById(R.id.btnCrearProfesor);

        validacionEntradas = new ValidacionEntradas(this);
        profesor = new Profesor();

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llenarBD();
            }
        });



    }

    private void llenarBD(){
        if (!validacionEntradas.validacionCamposVacios(mId,"Ingrese el id del profesor")) {
            return;
        }
        if(!validacionEntradas.validacionCamposVacios(mNombre,"Ingrese el nombre del profesor")){
            return;
        }
        if(!validacionEntradas.validacionCamposVacios(mFacultad, "Ingrese la facultad del profesor")){
            return;
        }else{
            profesor.setId(Integer.parseInt(mId.getText().toString().trim()));
            profesor.setNombre(mNombre.getText().toString().trim());
            profesor.setFacultad(mFacultad.getText().toString().trim());
            baseDatos.añadirProfesor(profesor);
        }

        Toast.makeText(this, "Profesor creado", Toast.LENGTH_SHORT).show();

    }

    private void limpiarCamposDeTexto(){
        mId.setText(null);
        mNombre.setText(null);
        mFacultad.setText(null);
        }

    }


