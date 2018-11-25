package com.example.vdanielmora.myp.Controladores;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vdanielmora.myp.Modelo.Profesor;
import com.example.vdanielmora.myp.R;

public class PerfilProfesor extends AppCompatActivity {

    private TextView mNombre, mApellido,mFacultad;
    private Button btnRegresar;
    private ListView mLista;
    private Profesor profesor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesor_perfil);
        mNombre = (TextView) findViewById(R.id.txtPPNombre);
        mApellido = (TextView) findViewById(R.id.txtPPApellido);
        mFacultad = (TextView) findViewById(R.id.txtPPFacultad);
        btnRegresar = (Button) findViewById(R.id.btnRegresarListaP);
        Bundle objetoEnviado = getIntent().getExtras();
        profesor = null;

        if(objetoEnviado!=null){
            profesor = (Profesor) objetoEnviado.getSerializable("profesorElegido");
            String mensaje = profesor.toString();
            mNombre.setText("Nombre: "+profesor.getNombre());
            mFacultad.setText("Facultad: "+profesor.getFacultad());
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        }

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListaProfesor.class);
                startActivity(intent);
            }
        });


    }

    private Profesor deStringAObjeto(String mensaje){
        Profesor m = new Profesor();
        String nombre, apellido,facultad;
        int primeraComa, segundaComa;

        primeraComa = mensaje.indexOf(",");
        segundaComa = mensaje.indexOf(",",primeraComa+1);

        nombre = mensaje.substring(0,primeraComa);
        m.setNombre(nombre);

        apellido = mensaje.substring(primeraComa+1, segundaComa);
        m.setApellido(apellido);

        facultad = mensaje.substring(segundaComa+1,mensaje.length());
        m.setFacultad(facultad);

        return m;
    }
}
