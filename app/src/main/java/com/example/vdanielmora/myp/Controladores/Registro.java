package com.example.vdanielmora.myp.Controladores;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vdanielmora.myp.Modelo.Usuario;
import com.example.vdanielmora.myp.Helper.ValidacionEntradas;
import com.example.vdanielmora.myp.Persistencia.BaseDatos;
import com.example.vdanielmora.myp.R;

public class Registro extends AppCompatActivity {

    private EditText contraConfirmar,contraRegistro,correoRegistro;
    private Button crearCuentaBtn ;
    private BaseDatos baseDatos = new BaseDatos(this);
    private ValidacionEntradas validacionEntradas;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        contraConfirmar = (EditText) findViewById(R.id.contraConfirmar);
        correoRegistro=(EditText) findViewById(R.id.correoRegistro);
        contraRegistro =(EditText) findViewById(R.id.contraRegistro);
        crearCuentaBtn = (Button) findViewById(R.id.crearCuentaBtn);
        validacionEntradas = new ValidacionEntradas(this);
        usuario = new Usuario();

        crearCuentaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                llenadoBD();


            }
        });


    }

    private void llenadoBD(){
        if(!validacionEntradas.validacionCamposVacios(correoRegistro, "Ingrese un correo valido")){
            return;

        }
        if(!validacionEntradas.validacionCorreo(correoRegistro, "Ingrese un correo valido")){
            return;
        }
        if(!validacionEntradas.validacionCamposVacios(contraRegistro, "Ingrese una contraseña")){
            return;
        }
        if(!validacionEntradas.validacionCamposContraseña(contraRegistro, contraConfirmar)){
            return;
        }if(!baseDatos.validarUsuario(correoRegistro.getText().toString().trim())){
            usuario.setNombreUsuario(correoRegistro.getText().toString().trim());
            usuario.setContraseña(contraRegistro.getText().toString().trim());

            baseDatos.añadirUsuario(usuario);

            Toast.makeText(this, "Usuario registrado", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), MYPmain.class);
            startActivity(intent);

        }else{
            contraRegistro.setText(null);
            correoRegistro.setText(null);
            contraConfirmar.setText(null);
            Toast.makeText(this, "Ya existe una cuenta vinculada a este correo", Toast.LENGTH_SHORT).show();
        }


    }





}
