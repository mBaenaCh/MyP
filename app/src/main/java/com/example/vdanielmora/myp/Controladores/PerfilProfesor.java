package com.example.vdanielmora.myp.Controladores;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vdanielmora.myp.Modelo.Materia;
import com.example.vdanielmora.myp.Modelo.Profesor;
import com.example.vdanielmora.myp.Persistencia.BaseDatos;
import com.example.vdanielmora.myp.R;

import java.util.ArrayList;

public class PerfilProfesor extends AppCompatActivity {

    private TextView mNombre,mFacultad;
    private Button btnRegresar;
    private BaseDatos baseDatos = new BaseDatos(this);
    private Profesor profesor;
    private ArrayList<String>listaImprimible;
    private ArrayList<Materia>listaMaterias;
    private ArrayList<Materia>listaMateriasProfesor;
    private ArrayList<Materia>listaAux;
    private ArrayAdapter adapter;
    private ListView mLista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesor_perfil);
        mNombre = (TextView) findViewById(R.id.txtPPNombre);
        mFacultad = (TextView) findViewById(R.id.txtPPFacultad);
        btnRegresar = (Button) findViewById(R.id.btnRegresarListaP);
        mLista = (ListView) findViewById(R.id.listaMaterias);

        listaImprimible = new ArrayList<>();
        listaMaterias = new ArrayList<>();
        listaMateriasProfesor = new ArrayList<>();


        listaMaterias = baseDatos.obtenerTodasLasMaterias();

        Bundle objetoEnviado = getIntent().getExtras();
        profesor = null;
        listaAux = null;
        if(objetoEnviado!=null){
            profesor = (Profesor) objetoEnviado.getSerializable("objetoElegido");
            mNombre.setText("Nombre: "+profesor.getNombre());
            mFacultad.setText("Facultad: "+profesor.getFacultad());
            //listaMateriasProfesor = profesor.getMaterias();
            //listaAux = seleccionDeMaterias(listaMaterias,listaMateriasProfesor);

            listaImprimible = deObjAString(listaMaterias);

            adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, listaImprimible);
            mLista.setAdapter(adapter);

            mLista.setBackgroundColor(Color.GRAY);

            Toast.makeText(this, "EL OBJETO LLEGO", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "EL OBJETO LLEGO VACIO", Toast.LENGTH_LONG).show();
        }

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MYPmain.class);
                startActivity(intent);
            }
        });


    }


    private ArrayList<Materia> seleccionDeMaterias(ArrayList<Materia> listaOriginal, ArrayList<Materia> listaComparacion){
        ArrayList<Materia> listaRetorno = new ArrayList<>();

        for (int i = 0; i < listaComparacion.size(); i++){
            if(listaOriginal.get(i).getId() == listaComparacion.get(i).getId()){
                listaRetorno.add(listaOriginal.get(i));
            }
        }
        return listaRetorno;
    }

    private ArrayList<String> deObjAString(ArrayList<Materia> listaObj){
        ArrayList<String> listaStrings = new ArrayList<>();
        for (int i = 0; i < listaObj.size(); i++){
            listaStrings.add(listaObj.get(i).toString());
        }
        return listaStrings;
    }



}
