package com.example.vdanielmora.myp.Controladores;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.vdanielmora.myp.Modelo.Materia;
import com.example.vdanielmora.myp.R;

import java.util.ArrayList;

public class ListadoMaterias extends AppCompatActivity {



    private ListView mLista;
    private ArrayList<String> listaImprimible;
    private ArrayAdapter adapter;
    private Button btnRegresar;
    private ArrayList<Materia> listaDeObjetos;
    private Materia materiaElegida;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_materias);
        mLista = (ListView) findViewById(R.id.listaM);
        btnRegresar = (Button) findViewById(R.id.btnRegresarBusquedaM);
        Intent intent = getIntent();
        Bundle objetoEnviado = intent.getExtras();
        listaDeObjetos = new ArrayList<>();
        materiaElegida = new Materia();
        listaImprimible = new ArrayList<>();

        if(objetoEnviado!= null){
            listaDeObjetos = (ArrayList<Materia>) objetoEnviado.getSerializable("objetosM");

        }

        for(int i = 0;i<listaDeObjetos.size();i++) {
            listaImprimible.add(listaDeObjetos.get(i).toString());

        }

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaImprimible);
        mLista.setAdapter(adapter);

        //Evento para ir al perfil de la materia que se eligio de la lista
        mLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                materiaElegida = listaDeObjetos.get(i);
                Intent intent = new Intent(getApplicationContext(), PerfilMateria.class);
                Bundle bundleObjeto = new Bundle();
                bundleObjeto.putSerializable("objetoElegido",materiaElegida);
                intent.putExtras(bundleObjeto);
                startActivity(intent);

            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext() , Busqueda.class);
                startActivity(intent);
            }
        });

    }


}
