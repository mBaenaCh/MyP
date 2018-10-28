package com.example.vdanielmora.myp.Controladores;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.vdanielmora.myp.Persistencia.BaseDatos;
import com.example.vdanielmora.myp.R;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class Busqueda extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.vdanielmora.myp.controladores.MESSAGE";
    private Button btnMateria;
    private Button btnProfesor;
    private EditText materia;
    private EditText profesor;
    private ArrayList<String> listaM;
    private ArrayList<String> listaP;
    private ArrayList<String> listaEncontradosM;
    private ArrayList<String> listaEncontradosP;
    private BaseDatos baseDatos = new BaseDatos(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);
        btnMateria = (Button)findViewById(R.id.btnMateria);
        btnProfesor= (Button)findViewById(R.id.btnProfesor);
        materia = (EditText)findViewById(R.id.txtMateria);
        profesor = (EditText)findViewById(R.id.txtProfesor);

    }

    public void onClick(View v){

        switch (v.getId()){


            /*
             * OPCION PARA BUSCAR MATERIAS
             */

            case R.id.btnMateria:

                //Cargo la lista de materias que existen en la base de datos
                listaM = llenadoArrayListText(baseDatos.obtenerTodasLasMaterias());
                /*Comparo los elementos de la lista de amterias en la base de datos para luego mostrar en la lista de encontrados
                 *todos aquellos que son similares
                 */
                listaEncontradosM = llenadoEncontrado(listaM, materia.getText().toString());
                Intent intentMateria = new Intent(getApplicationContext(),ListadoMaterias.class );
                intentMateria.putStringArrayListExtra("registrosEncontradosM",listaEncontradosM);
                startActivity(intentMateria);
                break;

            /*
             * OPCION PARA BUSCAR PROFESORES
             */

            case R.id.btnProfesor:
                listaP = llenadoArrayListText(baseDatos.obtenerTodosLosProfesores());
                listaEncontradosP = llenadoEncontrado(listaP, profesor.getText().toString());
                Intent intentProfesor = new Intent(getApplicationContext(),ListaProfesor.class);
                intentProfesor.putStringArrayListExtra("registrosEncontradosP",listaEncontradosP);
                startActivity(intentProfesor);
                break;
        }


    }

    public ArrayList llenadoArrayListText(List lista){
        ArrayList<String> listTexto = new ArrayList<>();

        for(int i =0; i<lista.size();i++){
            listTexto.add(lista.get(i).toString());
        }

        return listTexto;
    }

    public ArrayList llenadoEncontrado(List lista, String dato){
        //Se crea una nueva lista en l
        ArrayList<String> listTexto = new ArrayList<>();

        for(int i =0; i<lista.size();i++){
            String mensaje = (String) lista.get(i);
            int  primeraComa = mensaje.indexOf(",");
            String nombre = mensaje.substring(0,primeraComa).toLowerCase();
            if(nombre.equals(dato.toLowerCase())){

                listTexto.add(mensaje);

            }

        }

        return listTexto;
    }





}
