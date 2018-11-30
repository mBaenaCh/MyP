package com.example.vdanielmora.myp.Controladores;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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
import java.util.Random;

public class PerfilMateria extends AppCompatActivity {
    private EditText mTexto;
    private TextView mNombre, mGrupo, mHorario, mAula;
    private Button btnRegresar, btnAñadirComentario;
    private ListView mLista;
    private ArrayAdapter adapter;
    private BaseDatos baseDatos = new BaseDatos(this);
    private Materia materia;
    private ArrayList<String> listaImprimible;
    private ArrayList<Comentario> listaOriginal;
    private ArrayList<Comentario> listaComentarioMateria;
    private ArrayList<String> comentariosRegistrados;
    private ValidacionEntradas validacionEntradas;
    private Comentario comentario;
    private Random random;
    private String nombreUsuario;
    private MYPmain myPmain;
    private String idSeleccionada;

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

        myPmain = new MYPmain();
        nombreUsuario = myPmain.getNombreUsuario();
        validacionEntradas = new ValidacionEntradas(this);
        listaOriginal = new ArrayList<>();
        listaImprimible = new ArrayList<>();
        listaComentarioMateria = new ArrayList<>();
        comentariosRegistrados = new ArrayList<>();
        myPmain = new MYPmain();
        random = new Random();

        listaOriginal = baseDatos.obtenerTodosLosComentarios();

        comentario = new Comentario();
        materia = null;
        Bundle objetoRecibido = getIntent().getExtras();

        if(objetoRecibido!=null) {
            materia = (Materia) objetoRecibido.getSerializable("objetoElegido");

            mNombre.setText(materia.getNombre());
            mGrupo.setText("Grupo: "+materia.getGrupo());
            mHorario.setText("Horario: "+materia.getHorario());
            mAula.setText("Aula: "+materia.getAula());


            idSeleccionada = Integer.toString(materia.getId());

            comentariosRegistrados = baseDatos.listaComentariosMateria(idSeleccionada);
            listaComentarioMateria = seleccionDeMaterias(listaOriginal, comentariosRegistrados);

            listaImprimible = deObjAString(listaComentarioMateria);
            adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,listaImprimible);
            mLista.setAdapter(adapter);
            mLista.setBackgroundColor(Color.GRAY);
            
            Toast.makeText(this, "EL OBJETO LLEGO", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "EL OBJETO NO LLEGO", Toast.LENGTH_SHORT).show();
        }

        btnAñadirComentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                llenarBD();
                mTexto.setText("");
                finish();
                startActivity(getIntent());
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), Busqueda.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

            }
        });

    }

    private ArrayList<Comentario> seleccionDeMaterias(ArrayList<Comentario> listaOriginal, ArrayList<String> listaComparacion){
        ArrayList<Comentario> listaRetorno = new ArrayList<>();

        for(int i = 0; i <listaComparacion.size();i++){
            for(int j=0;j<listaOriginal.size();j++){
                if(listaComparacion.get(i).equals(Integer.toString(listaOriginal.get(j).getId()))){
                    listaRetorno.add(listaOriginal.get(j));
                }
            }
        }
        return listaRetorno;
    }

    private ArrayList<String> deObjAString(ArrayList<Comentario> listaObj){
        ArrayList<String> listaStrings = new ArrayList<>();
        for (int i = 0; i < listaObj.size(); i++){
            listaStrings.add(listaObj.get(i).toString());
        }
        return listaStrings;
    }

    public void llenarBD(){
        if(!validacionEntradas.validacionCamposVacios(mTexto,"")){
            return;
        }else{
            comentario.setId(random.nextInt(100));
            comentario.setTexto(mTexto.getText().toString().trim());
            comentario.setNombreUsuario(nombreUsuario);
            String mensaje = baseDatos.añadirComentario(comentario);
            String mensaje2 = baseDatos.añadirComentarioMateria(comentario.getId(),materia.getId());
            Toast.makeText(this, mensaje + mensaje2, Toast.LENGTH_SHORT).show();
        }
    }
}
