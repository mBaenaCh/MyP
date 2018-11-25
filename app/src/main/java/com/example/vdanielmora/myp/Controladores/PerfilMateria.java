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
    private ArrayList<String> lista;
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
            String mensaje = materia.toString();
            mNombre.setText("Nombre: "+materia.getNombre());
            mGrupo.setText("Grupo: "+materia.getGrupo());
            mHorario.setText("Horario: "+materia.getHorario());
            mAula.setText("Aula: "+materia.getAula());
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        }


        btnAñadirComentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearComentario(mTexto.getText().toString().trim(),myPmain.getNombreUsuario());
                lista.add(comentario.getTexto());
                mLista.setAdapter(adapter);
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), ListadoMaterias.class);
                startActivity(intent);

            }
        });






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

    private void crearComentario(String texto, String nombreUsuario){
        comentario.setTexto(texto);
        comentario.setNombreUsuario(nombreUsuario);
        baseDatos.añadirComentario(comentario);
        materia.añadirComentario(comentario);
    }

}
