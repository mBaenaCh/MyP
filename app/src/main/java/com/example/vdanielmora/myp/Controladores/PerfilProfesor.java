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

import org.w3c.dom.Text;

public class PerfilProfesor extends AppCompatActivity {

    private TextView mNombre, mFacultad;
    private Button btnRegresar;
    private ListView mLista;
    private Profesor profesor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesor_perfil);
        mNombre = (TextView) findViewById(R.id.txtPPNombre);
        mFacultad = (TextView) findViewById(R.id.txtPPFacultad);
        btnRegresar = (Button) findViewById(R.id.btnRegresarListaP);

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListaProfesor.class);
            }
        });

        profesor = new Profesor();

        Intent intent = getIntent();
        String mensaje=intent.getStringExtra(ListaProfesor.EXTRA_MESSAGE);
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        profesor = deStringAObjeto(mensaje);

        mNombre.setText("Nombre: "+profesor.getNombre());
        mFacultad.setText("Facultad: "+profesor.getFacultad());

    }

    private Profesor deStringAObjeto(String mensaje){
        Profesor m = new Profesor();
        String nombre, facultad;
        int primeraComa;

        primeraComa = mensaje.indexOf(",");

        nombre = mensaje.substring(0,primeraComa);
        m.setNombre(nombre);

        facultad = mensaje.substring(primeraComa+1,mensaje.length()-1);
        m.setFacultad(facultad);

        return m;
    }
}
