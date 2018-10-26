package com.example.vdanielmora.myp.Controladores;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.vdanielmora.myp.Modelo.Materia;
import com.example.vdanielmora.myp.Persistencia.BaseDatos;
import com.example.vdanielmora.myp.R;

import java.util.ArrayList;
import java.util.List;

public class ListadoMaterias extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.vdanielmora.myp.controladores.MESSAGE";

    private ListView mLista;
    private ArrayList<String> lista;
    private BaseDatos baseDatos = new BaseDatos(this);
    private ArrayAdapter adapter;
    private Button btnRegresar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_materias);
        mLista = (ListView) findViewById(R.id.lista);
        btnRegresar = (Button) findViewById(R.id.btnRegresarBusqueda);
        Intent intent= getIntent();
        Bundle extras = intent.getExtras();
        if(extras!= null){
            lista = extras.getStringArrayList("registrosEncontrados");
        }else {

            Intent noDatos = new Intent(getApplicationContext(),Busqueda.class);
            startActivity(noDatos);
        }

       // lista = llenadoArrayListText(baseDatos.obtenerTodasLasMaterias());

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
        mLista.setAdapter(adapter);

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
