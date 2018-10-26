package com.example.vdanielmora.myp.Controladores;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.vdanielmora.myp.Modelo.Materia;
import com.example.vdanielmora.myp.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PerfilMateria extends AppCompatActivity {

    private TextView mNombre, mGrupo, mHorario, mAula;
    private Button btnRegresar;
    private ListView mLista;

    private Materia materia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_materia);
        mNombre = (TextView) findViewById(R.id.txtPMNombre);
        mGrupo = (TextView) findViewById(R.id.txtPMGrupo);
        mHorario = (TextView) findViewById(R.id.txtPMHorario);
        mAula = (TextView) findViewById(R.id.txtPMAula);
        btnRegresar = (Button) findViewById(R.id.btnRegresar);

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), ListadoMaterias.class);
                startActivity(intent);

            }
        });


        materia = new Materia();

        Intent intent = getIntent();
        String mensaje = intent.getStringExtra(ListadoMaterias.EXTRA_MESSAGE);
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        materia = deStringAObjeto(mensaje);

        mNombre.setText(materia.getNombre());
        mGrupo.setText("Grupo: "+materia.getGrupo());
        mHorario.setText("Horario: "+materia.getHorario());
        mAula.setText("Aula: "+materia.getAula());

    }

    private Materia deStringAObjeto(String mensaje){
        Materia m = new Materia();
        String nombre, grupo, horario, aula;
        int primeraComa, segundaComa,terceraComa;

        primeraComa = mensaje.indexOf(",");
        segundaComa = mensaje.indexOf(",",primeraComa+1);
        terceraComa = mensaje.indexOf(",",segundaComa+1);

        nombre = mensaje.substring(0,primeraComa);
        m.setNombre(nombre);

        grupo = mensaje.substring(primeraComa+1,segundaComa);
        m.setGrupo(grupo);

        horario = mensaje.substring(segundaComa+1,terceraComa);
        m.setHorario(horario);

        aula = mensaje.substring(terceraComa+1,mensaje.length());
        m.setAula(aula);

        return m;
    }



}
