package com.example.vdanielmora.myp.Controladores;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.vdanielmora.myp.Helper.ValidacionEntradas;
import com.example.vdanielmora.myp.Modelo.Comentario;
import com.example.vdanielmora.myp.Modelo.Materia;
import com.example.vdanielmora.myp.Persistencia.BaseDatos;
import com.example.vdanielmora.myp.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PerfilMateria extends AppCompatActivity {
    private EditText mTexto;
    private TextView mNombre, mGrupo, mHorario, mAula;
    private Button btnRegresar, btnAñadirComentario;
    private ListView mLista;
    private ArrayList<String> listaImprimible;
    private ArrayAdapter adapter;
    private BaseDatos baseDatos = new BaseDatos(this);
    private ValidacionEntradas validacionEntradas;
    private Materia materia;
    Comentario comentario;
    MYPmain myPmain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_materia);
        mNombre = (TextView) findViewById(R.id.txtPMNombre);
        mGrupo = (TextView) findViewById(R.id.txtPMGrupo);
        mHorario = (TextView) findViewById(R.id.txtPMHorario);
        mAula = (TextView) findViewById(R.id.txtPMAula);
        mTexto = (EditText) findViewById(R.id.txtComentario);
        btnRegresar = (Button) findViewById(R.id.btnRegresarListaM);
        btnAñadirComentario = (Button) findViewById(R.id.btnAñadirComentario);
        mLista = (ListView) findViewById(R.id.listaComentarios);
        validacionEntradas = new ValidacionEntradas(this);

        materia = null;
        Bundle objetoRecibido = getIntent().getExtras();

        if(objetoRecibido!=null) {
            materia = (Materia) objetoRecibido.getSerializable("objetoElegido");


            mNombre.setText(materia.getNombre());
            mGrupo.setText("Grupo: "+materia.getGrupo());
            mHorario.setText("Horario: "+materia.getHorario());
            mAula.setText("Aula: "+materia.getAula());


        }

        btnAñadirComentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listaImprimible.add(comentario.getTexto());
                mLista.setAdapter(adapter);

            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), Busqueda.class);
                startActivity(intent);

            }
        });

    }

    public void crearComentario(String texto, String nombreDeUsuario){




    }


}
