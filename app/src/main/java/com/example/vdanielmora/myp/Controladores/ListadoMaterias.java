package com.example.vdanielmora.myp.Controladores;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.vdanielmora.myp.R;

import java.util.ArrayList;

public class ListadoMaterias extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.vdanielmora.myp.controladores.MESSAGE";

    private ListView mLista;
    private ArrayList<String> lista;
    private ArrayAdapter adapter;
    private Button btnRegresar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_materias);
        mLista = (ListView) findViewById(R.id.listaM);
        btnRegresar = (Button) findViewById(R.id.btnRegresarBusquedaM);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras!= null){
            lista = extras.getStringArrayList("registrosEncontradosM");
        }else {

            Intent noDatos = new Intent(this ,Busqueda.class);
            startActivity(noDatos);
        }

       // lista = llenadoArrayListText(baseDatos.obtenerTodasLasMaterias());

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
        mLista.setAdapter(adapter);

        //Evento para ir al perfil de la materia que se eligio de la lista
        mLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String elementoEnLista = lista.get(i);
                Intent intent = new Intent(getApplicationContext(), PerfilMateria.class);
                intent.putExtra(EXTRA_MESSAGE, elementoEnLista);
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
