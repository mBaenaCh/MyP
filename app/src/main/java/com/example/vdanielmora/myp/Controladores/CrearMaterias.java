package com.example.vdanielmora.myp.Controladores;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vdanielmora.myp.Helper.ValidacionEntradas;
import com.example.vdanielmora.myp.Modelo.Materia;
import com.example.vdanielmora.myp.Persistencia.BaseDatos;
import com.example.vdanielmora.myp.R;

public class CrearMaterias extends AppCompatActivity {

    private EditText mNombre, mGrupo, mHorario, mAula;
    private Button btnCrear;
    private ValidacionEntradas validacionEntradas;
    private BaseDatos baseDatos = new BaseDatos(this);
    private Materia materia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_materias);
        mNombre = (EditText) findViewById(R.id.txtMNombre);
        mGrupo = (EditText) findViewById(R.id.txtMGrupo);
        mHorario = (EditText) findViewById(R.id.txtMHorario);
        mAula = (EditText) findViewById(R.id.txtMAula);
        btnCrear = (Button) findViewById(R.id.btnCrearMateria);

        validacionEntradas = new ValidacionEntradas(this);
        materia = new Materia();

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llenarBD();
            }
        });

    }

    private void llenarBD(){

        if(!validacionEntradas.validacionCamposVacios(mNombre, "Ingrese el nombre de la materia")){
            return;
        }
        if(!validacionEntradas.validacionCamposVacios(mGrupo, "Ingrese el grupo de la materia")){
            return;
        }
        if(!validacionEntradas.validacionCamposVacios(mHorario, "Ingrese un horario")){
            return;
        }
        if(!validacionEntradas.validacionCamposVacios(mAula, "Ingrese el aula de la materia")){
            return;
        }else{
            materia.setNombre(mNombre.getText().toString().trim());
            materia.setGrupo(mGrupo.getText().toString().trim());
            materia.setHorario(mHorario.getText().toString().trim());
            materia.setAula(mAula.getText().toString().trim());
            baseDatos.añadirMateria(materia);

            Toast.makeText(this, "Materia creada", Toast.LENGTH_SHORT).show();

        }
    }

    private void limpiarCamposDeTexto(){
        mNombre.setText(null);
        mGrupo.setText(null);
        mHorario.setText(null);
        mAula.setText(null);
    }

}
