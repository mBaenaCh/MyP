package com.example.vdanielmora.myp.Controladores;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.vdanielmora.myp.Modelo.Profesor;
import com.example.vdanielmora.myp.R;

import java.util.ArrayList;

public class ListaProfesor extends AppCompatActivity {
    private ListView mLista;
    private ArrayList<String> listaImprimible;
    private ArrayAdapter adapter;
    private Button btnRegresar;
    private ArrayList<Profesor> listaObjetos;
    private Profesor profesorElegido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_profesor);
        mLista = (ListView) findViewById(R.id.listaP);
        btnRegresar = (Button) findViewById(R.id.btnRegresarBusquedaP);
        listaImprimible = new ArrayList<>();
        listaObjetos = null;
        profesorElegido = new Profesor();
        Bundle objetoEnviado = getIntent().getExtras();



        if (objetoEnviado!=null){
            listaObjetos = (ArrayList<Profesor>) objetoEnviado.getSerializable("objetosP");
        }

        for(int i = 0; i<listaObjetos.size();i++){
            listaImprimible.add(listaObjetos.get(i).toString());
        }

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaImprimible);
        mLista.setAdapter(adapter);

        mLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                profesorElegido = listaObjetos.get(i);
                Intent intent = new Intent(getApplicationContext(), PerfilProfesor.class);
                Bundle bundleObjeto = new Bundle();
                bundleObjeto.putSerializable("objetoElegido",profesorElegido);
                intent.putExtras(bundleObjeto);
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
