package com.example.vdanielmora.myp.Controladores;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.vdanielmora.myp.Helper.ValidacionEntradas;
import com.example.vdanielmora.myp.Persistencia.BaseDatos;
import com.example.vdanielmora.myp.R;

public class MYPmain extends AppCompatActivity {



   private  EditText mCorreo, mContra;
   private  Button registro,inicio, btnMateria, btnProfesor;
   private BaseDatos baseDatos = new BaseDatos(this);
   private ValidacionEntradas validacionEntradas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypmain);
        mCorreo = (EditText) findViewById(R.id.mCorreo);
        mContra = (EditText) findViewById(R.id.mContra);
        registro = (Button)findViewById(R.id.registrarseBtn);
        btnMateria = (Button) findViewById(R.id.btnMateria);
        btnProfesor = (Button) findViewById(R.id.btnProfesor);
        inicio = (Button)findViewById(R.id.loginBtn);
        validacionEntradas = new ValidacionEntradas(this);


        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),Registro.class);
                startActivity(intent);

            }
        });

        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validacionDesdeBD();


            }
        });

        btnMateria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CrearMaterias.class);
                startActivity(intent);
            }
        });

        btnProfesor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CrearProfesor.class);
                startActivity(intent);
            }
        });
    }

    private void validacionDesdeBD(){

        if (!validacionEntradas.validacionCamposVacios(mCorreo,"Ingrese un correo valido")){

            return;
        }
        if (!validacionEntradas.validacionCamposVacios(mContra,"Ingrese contraseña")){

            return;
        }
        if(!validacionEntradas.validacionCorreo(mCorreo,"Ingrese un correo valido")){

            return;
        }
        if (baseDatos.validarUsuario(mCorreo.getText().toString().trim(),
                mContra.getText().toString().trim())){
            Intent goBusqueda = new Intent(this,Busqueda.class);
            startActivity(goBusqueda);
            overridePendingTransition(R.anim.left_in, R.anim.left_out);


        }else{
            Toast.makeText(this, "Cuenta no registrada", Toast.LENGTH_SHORT).show();
        }



    }

    public String getNombreUsuario(){
        return this.mCorreo.getText().toString().trim();
    }



}
