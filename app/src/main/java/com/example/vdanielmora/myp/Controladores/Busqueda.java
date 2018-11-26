package com.example.vdanielmora.myp.Controladores;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vdanielmora.myp.Modelo.Materia;
import com.example.vdanielmora.myp.Modelo.Profesor;
import com.example.vdanielmora.myp.Persistencia.BaseDatos;
import com.example.vdanielmora.myp.R;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class Busqueda extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.vdanielmora.myp.controladores.MESSAGE";
    private Button btnMateria;
    private Button btnProfesor;
    private Button btnListaM;
    private Button btnListaP;
    private EditText materia;
    private EditText profesor;
    private ArrayList listaM;
    private ArrayList listaP;
    private ArrayList<Materia> listaEncontradosM;
    private ArrayList<Profesor> listaEncontradosP;
    private ArrayList listaCompletaMaterias;
    private ArrayList listaCompletaProfesores;
    private BaseDatos baseDatos = new BaseDatos(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);
        btnMateria = (Button)findViewById(R.id.btnMateria);
        btnProfesor= (Button)findViewById(R.id.btnProfesor);
        materia = (EditText)findViewById(R.id.txtMateria);
        profesor = (EditText)findViewById(R.id.txtProfesor);
        btnListaM = (Button)findViewById(R.id.btnListaM);
        btnListaP= (Button)findViewById(R.id.btnListaP);
    }

    public void onClick(View v){

        switch (v.getId()){


            /*
             * OPCION PARA BUSCAR MATERIAS
             */

            case R.id.btnMateria:




                //Cargo la lista de materias que existen en la base de datos
                listaM = llenadoArrayListMateria(baseDatos.obtenerTodasLasMaterias());
                /*Comparo los elementos de la lista de materias en la base de datos para luego mostrar en la lista de encontrados
                 *todos aquellos que son similares
                 */
                listaEncontradosM = llenadoEncontradoMaterias(listaM, materia.getText().toString());

                Intent intentMateria = new Intent(getApplicationContext(), ListadoMaterias.class);
                Bundle bundleM = new Bundle();
                bundleM.putSerializable("objetosM", listaEncontradosM);
                intentMateria.putExtras(bundleM);
                startActivity(intentMateria);

                break;

            /*
             * OPCION PARA BUSCAR PROFESORES
             */

            case R.id.btnProfesor:
                listaP = llenadoArrayListProfesor(baseDatos.obtenerTodosLosProfesores());

                listaEncontradosP = llenadoEncontradoProfesores(listaP, profesor.getText().toString());

                Intent intentProfesor = new Intent(getApplicationContext(),ListaProfesor.class);
                Bundle bundleP = new Bundle();
                bundleP.putSerializable("objetosP",listaEncontradosP);
                intentProfesor.putExtras(bundleP);
                startActivity(intentProfesor);

                break;

            case R.id.btnListaM:
                listaCompletaMaterias=llenadoArrayListMateria(baseDatos.obtenerTodasLasMaterias());
                Intent intentListaMateriaCompleta = new Intent(getApplicationContext(), ListadoMaterias.class);
                Bundle bundleMateriaCompleta = new Bundle();
                bundleMateriaCompleta.putSerializable("objetosM", listaCompletaMaterias);
                intentListaMateriaCompleta.putExtras(bundleMateriaCompleta);
                startActivity(intentListaMateriaCompleta);

                break;

            case R.id.btnListaP:
                listaCompletaProfesores= llenadoArrayListProfesor(baseDatos.obtenerTodosLosProfesores());

                Intent intentListaProfesorCompleta = new Intent(getApplicationContext(),ListaProfesor.class);
                Bundle bundleProfesorCompleta = new Bundle();
                bundleProfesorCompleta.putSerializable("objetosP",listaCompletaProfesores);
                intentListaProfesorCompleta.putExtras(bundleProfesorCompleta);
                startActivity(intentListaProfesorCompleta);
                break;


        }

    }

    public ArrayList llenadoArrayListMateria(ArrayList<Materia> lista){

        ArrayList<Materia> list = new ArrayList<>();

        for(int i =0; i<lista.size();i++){
            list.add(lista.get(i));
        }

        return list;
    }

    public ArrayList llenadoArrayListProfesor(ArrayList<Profesor> lista){
        ArrayList<Profesor> list = new ArrayList<>();
        for(int i=0; i<lista.size();i++){
            list.add(lista.get(i));
        }
        return list;
    }

    public ArrayList llenadoEncontradoProfesores(ArrayList<Profesor> lista, String dato){
        //Se crea una nueva lista de tipo "objeto"
        ArrayList<Profesor> listaDeEncontrados = new ArrayList<>();

        //Se recorre la lista que se recibio como parametro
        for(int i =0; i<lista.size();i++){
            //Del elemento en la posicion "i", que es un objeto profesor
            //Retornamos el dato que este objeto tiene como "nombre"
            String nombre = lista.get(i).getNombre();
            //Comparamos si este dato es igual a lo que ingresamos en el texto de busqueda
            if(nombre.contains(dato.toLowerCase().trim().toLowerCase())){
            //Si es el caso entonces lo añadimos a la nueva lista de objetos encontrados de tipo "profesor"
                listaDeEncontrados.add(lista.get(i));

            }
            //De lo contrario seguimos iterando
        }
        //Retornamos la lista de objetos "profesor" que corresponde a los encontrados segun el dato ingresado
        return listaDeEncontrados;
    }

    public ArrayList llenadoEncontradoMaterias(ArrayList<Materia> lista, String dato){
        //Se crea una nueva lista de tipo "objeto"
        ArrayList<Materia> listaDeEncontrados = new ArrayList<>();

        //Se recorre la lista que se recibio como parametro
        for(int i = 0; i<lista.size();i++){
            //Del elemento en la posicion "i", que es un objeto materia
            //Retornamos el dato que este objeto tiene como "nombre"
            String nombre = lista.get(i).getNombre().trim().toLowerCase();
            //Comparamos si este dato es igual a lo que ingresamos en el texto de busqueda
            if(nombre.contains(dato.toLowerCase())){
                //Si es el caso entonces l oañadimos a la nueva lista de objetos encontrados de tipo "materia"
                listaDeEncontrados.add(lista.get(i));
            }
        //De lo contrario seguimos iterando
        }
        //Retornamos la lista de objetos "materia" que corresponden a los encontrados segun el dato ingresado
        return listaDeEncontrados;
    }

}
