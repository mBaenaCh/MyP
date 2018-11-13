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

public class ListaProfesor extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.vdanielmora.myp.controladores.MESSAGE";

    private ListView mLista;
    private ArrayList<String> lista;
    private ArrayAdapter adapter;
    private Button btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_profesor);
        mLista = (ListView) findViewById(R.id.listaP);
        btnRegresar = (Button) findViewById(R.id.btnRegresarBusquedaP);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if(extras!= null){
            lista = extras.getStringArrayList("registrosEncontradosP");
        }else {

            Intent noDatos = new Intent(this ,Busqueda.class);
            startActivity(noDatos);
        }

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,lista);
        mLista.setAdapter(adapter);

        mLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String elementoEnLista = lista.get(i);
                Intent intent = new Intent(getApplicationContext(), PerfilProfesor.class);
                intent.putExtra(EXTRA_MESSAGE, elementoEnLista);
                startActivity(intent);

            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Busqueda.class);
                startActivity(intent);
            }
        });


    }
}
