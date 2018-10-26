package com.example.vdanielmora.myp.Persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.vdanielmora.myp.Modelo.Materia;
import com.example.vdanielmora.myp.Modelo.Profesor;
import com.example.vdanielmora.myp.Modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class BaseDatos extends SQLiteOpenHelper {

    //Version de la base de datos
    private static final int VERSION_BASEDATOS = 1;

    //Nombre de la base de datos
    private static final String NOMBRE_BASEDATOS = "bdMyP.db";

    //Nombre de las tablas de la base de datos
    private static final String TABLA_USUARIO = "usuario";
    private static final String TABLA_MATERIA = "materia";
    private static final String TABLA_PROFESOR = "profesor";

    /* ----- COLUMNAS QUE COMPONEN LA TABLA USUARIO -----
     *
     * CAMPO ID ES CLAVE PRIMARIA }
     */
    private static final String COLUMNA_USUARIO_ID ="usuario_id";
    private static final String COLUMNA_USUARIO_NOMBRE = "usuario_nombre";
    private static final String COLUMNA_USUARIO_CONTRASEÑA = "usuario_contraseña";

    /* ----- COLUMNAS QUE COMPONEN LA TABLA MATERIA -----
     *
     * CAMPO ID ES CLAVE PRIMARIA }
     */
    private static final String COLUMNA_MATERIA_ID = "materia_id";
    private static final String COLUMNA_MATERIA_NOMBRE = "materia_nombre";
    private static final String COLUMNA_MATERIA_GRUPO = "materia_grupo";
    private static final String COLUMNA_MATERIA_HORARIO = "materia_horario";
    private static final String COLUMNA_MATERIA_AULA = "materia_aula";

    /* ----- COLUMNAS QUE COMPONEN LA TABLA PROFESOR -----
     *
     * CAMPO ID ES CLAVE PRIMARIA
     */
    private static final String COLUMNA_PROFESOR_ID = "profesor_id";
    private static final String COLUMNA_PROFESOR_NOMBRE = "profesor_nombre";



    //Query para la creacion de la tabla de usuario
    private String CREAR_TABLA_USUARIO = "CREATE TABLE "+TABLA_USUARIO+ " ("+
            COLUMNA_USUARIO_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            COLUMNA_USUARIO_NOMBRE+" TEXT,"+
            COLUMNA_USUARIO_CONTRASEÑA+" TEXT"+")";

    //Query para la creacion de la tabla materia
    private String CREAR_TABLA_MATERIA = "CREATE TABLE "+TABLA_MATERIA+ " ("+
            COLUMNA_MATERIA_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            COLUMNA_MATERIA_NOMBRE+" TEXT,"+
            COLUMNA_MATERIA_GRUPO+" TEXT,"+
            COLUMNA_MATERIA_HORARIO+" TEXT,"+
            COLUMNA_MATERIA_AULA+" TEXT"+")";

    //Query para la creacion de la tabla materia
    private String CREAR_TABLA_PROFESOR = "CREATE TABLE "+TABLA_PROFESOR+ " ("+
            COLUMNA_PROFESOR_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            COLUMNA_PROFESOR_NOMBRE+" TEXT"+")";

    private String DROP_TABLE = "DROP TABLE IF EXISTS "+NOMBRE_BASEDATOS;


    public BaseDatos(Context context){
        super(context,NOMBRE_BASEDATOS,null,VERSION_BASEDATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA_USUARIO);
        db.execSQL(CREAR_TABLA_MATERIA);
        db.execSQL(CREAR_TABLA_PROFESOR);
        db.execSQL("INSERT INTO "+TABLA_MATERIA+" ("+COLUMNA_MATERIA_NOMBRE+", "+COLUMNA_MATERIA_GRUPO+", "+COLUMNA_MATERIA_HORARIO+", "+COLUMNA_MATERIA_AULA+") VALUES ('calculo integral','1','MJ12-2','19-212')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Se elimina la tabla si esta existe
        db.execSQL(DROP_TABLE);
        //Se crea una nueva tabla de usuarios
        onCreate(db);

    }

    //Metodo para añadir un objeto usuario a la base de datos
    public String añadirUsuario(Usuario usuario){

        String mensaje="";

        SQLiteDatabase baseDatos = this.getWritableDatabase();

        ContentValues valores = new ContentValues();

        valores.put(COLUMNA_USUARIO_NOMBRE,usuario.getNombreUsuario());
        valores.put(COLUMNA_USUARIO_CONTRASEÑA,usuario.getContraseña());

        try {
            baseDatos.insertOrThrow(TABLA_USUARIO, null, valores);
            mensaje = "Registrado correctamente";
        }catch (SQLException e){
            mensaje = "No registrado correctamente";
        }
        return mensaje;
    }

    //Metodo para obtener de forma ascendente, segun el nombre, todos los usuarios en la base de datos
    public List<Usuario> obtenerTodosLosUsuarios(){
        //Arreglo de columnas a obtener
        String[]columnas={
                COLUMNA_USUARIO_ID,
                COLUMNA_USUARIO_NOMBRE,
                COLUMNA_USUARIO_CONTRASEÑA
        };

        //Forma como seran ordenados los usuarios
        String sortOrder = COLUMNA_USUARIO_NOMBRE +" ASC";

        //Instanciacion de la colection "ArrayList" por medio de referencia a clase general "List"
        List<Usuario> listaUsuarios = new ArrayList<Usuario>();
        SQLiteDatabase baseDatos = this.getReadableDatabase();
        Cursor cursor = baseDatos.query(
                TABLA_USUARIO,  //Tabla que consultaremos
                columnas,       //Columnas que retornaremos de la tabla
                null,
                null,
                null,
                null,
                sortOrder);

        //Recorriendo todas las filas de la tabla mientras halla registros
        if (cursor.moveToFirst()){
            do{
                //Creando un objeto de los datos que esta nen cada fila
                Usuario usuario = new Usuario();
                usuario.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMNA_USUARIO_ID))));
                usuario.setNombreUsuario(cursor.getString(cursor.getColumnIndex(COLUMNA_USUARIO_NOMBRE)));
                usuario.setContraseña(cursor.getString(cursor.getColumnIndex(COLUMNA_USUARIO_CONTRASEÑA)));
                //Añadiendo el objeto a la lista de usuarios
                listaUsuarios.add(usuario);
            }while(cursor.moveToNext());
        }
        cursor.close();
        baseDatos.close();

        return listaUsuarios;
    }


    /*
    Metodo para saber si un usuario existe o no
     */
    public boolean validarUsuario(String correo){
        //Arreglo de columnas a obtener
        String[] columns ={
          COLUMNA_USUARIO_ID
        };
        SQLiteDatabase baseDatos = this.getReadableDatabase();

        String selection = COLUMNA_USUARIO_NOMBRE + " = ?";
        String[] selectionArgs = {correo};


        Cursor cursor = baseDatos.query(
                TABLA_USUARIO, //Tabla que consultaremos
                columns,        //Columnas involucradas
                selection,      //El campo que buscaremos
                selectionArgs,  //El valor con el cual compararemos
                null,
                null,
                null);

        int cursorCount = cursor.getCount();
        cursor.close();
        baseDatos.close();

        if(cursorCount > 0){
            return true;
        }

        return false;
    }

    /*
    Metodo para validar datos ingresados por el usuario al iniciar sesion
     */
    public boolean validarUsuario(String correo, String contraseña){

        String[] columns = {
          COLUMNA_USUARIO_ID
        };

        SQLiteDatabase baseDatos = this.getReadableDatabase();
        String selection = COLUMNA_USUARIO_NOMBRE + " = ?" + " AND " + COLUMNA_USUARIO_CONTRASEÑA+" = ?";
        String[]selectionArgs = {correo, contraseña};

        Cursor cursor = baseDatos.query(
                TABLA_USUARIO,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);

        int cursorCount = cursor.getCount();
        cursor.close();
        baseDatos.close();
        if(cursorCount > 0){
            return true;
        }
        return false;
    }


    //Metodo para añadir un objeto materia a la base de datos
    public String añadirMateria(Materia materia){
        String mensaje="";

        SQLiteDatabase baseDatos = this.getWritableDatabase();
        ContentValues contenedor = new ContentValues();

        contenedor.put(COLUMNA_MATERIA_NOMBRE, materia.getNombre());
        contenedor.put(COLUMNA_MATERIA_GRUPO,materia.getGrupo());
        contenedor.put(COLUMNA_MATERIA_HORARIO, materia.getHorario());
        contenedor.put(COLUMNA_MATERIA_AULA, materia.getAula());

        try{
            baseDatos.insertOrThrow(TABLA_MATERIA, null , contenedor);
            mensaje = "Materia guardada correctamente";
        }catch (SQLException e){
            mensaje = "Materia no ingresada correctamente";
        }

        return mensaje;
    }

    //Metodo para retornar la lista de materias que estan registradas en la base de datos
    public List<Materia> obtenerTodasLasMaterias(){
        //Arreglo de columnas a obtener
        String[]columnas={
                COLUMNA_MATERIA_ID,
                COLUMNA_MATERIA_NOMBRE,
                COLUMNA_MATERIA_GRUPO,
                COLUMNA_MATERIA_HORARIO,
                COLUMNA_MATERIA_AULA
        };

        String sortOrder = COLUMNA_MATERIA_NOMBRE +" ASC";

        //Instanciacion de la colection "ArrayList" por medio de referencia a clase general "List"
        List<Materia> listaMaterias = new ArrayList<Materia>();
        SQLiteDatabase baseDatos = this.getReadableDatabase();
        Cursor cursor = baseDatos.query(
                TABLA_MATERIA,  //Tabla que consultaremos
                columnas,       //Columnas que retornaremos de la tabla
                null,
                null,
                null,
                null,
                sortOrder);

        //Recorriendo todas las filas de la tabla mientras halla registros
        if (cursor.moveToFirst()){
            do{
                //Creando un objeto de los datos que estan en cada fila
                Materia materia = new Materia();

                materia.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMNA_MATERIA_ID))));
                materia.setNombre(cursor.getString(cursor.getColumnIndex(COLUMNA_MATERIA_NOMBRE)));
                materia.setGrupo(cursor.getString(cursor.getColumnIndex(COLUMNA_MATERIA_GRUPO)));
                materia.setHorario(cursor.getString(cursor.getColumnIndex(COLUMNA_MATERIA_HORARIO)));
                materia.setAula(cursor.getString(cursor.getColumnIndex(COLUMNA_MATERIA_AULA)));


                //Añadiendo el objeto a la lista de materias
                listaMaterias.add(materia);
            }while(cursor.moveToNext());
        }
        cursor.close();
        baseDatos.close();

        return listaMaterias;
    }

    //Metodo para añadir un objeto profesor a la base de datos
    public String añadirProfesor(Profesor profesor){

        String mensaje="";

        SQLiteDatabase baseDatos = this.getWritableDatabase();

        ContentValues valores = new ContentValues();

        valores.put(COLUMNA_PROFESOR_NOMBRE,profesor.getNombre());


        try {
            baseDatos.insertOrThrow(TABLA_PROFESOR, null, valores);
            mensaje = "Registrado correctamente";
        }catch (SQLException e){
            mensaje = "No registrado correctamente";
        }
        return mensaje;
    }

    //Metodo para retornar la lista de profesores que estan registrados en la base de datos
    public List<Profesor> obtenerTodosLosProfesores(){
        //Arreglo de columnas a obtener
        String[]columnas={
                COLUMNA_PROFESOR_ID,
                COLUMNA_PROFESOR_NOMBRE
        };

        String sortOrder = COLUMNA_PROFESOR_NOMBRE +" ASC";

        //Instanciacion de la colection "ArrayList" por medio de referencia a clase general "List"
        List<Profesor> listaProfesores = new ArrayList<Profesor>();
        SQLiteDatabase baseDatos = this.getReadableDatabase();
        Cursor cursor = baseDatos.query(
                TABLA_PROFESOR,  //Tabla que consultaremos
                columnas,       //Columnas que retornaremos de la tabla
                null,
                null,
                null,
                null,
                sortOrder);

        //Recorriendo todas las filas de la tabla mientras halla registros
        if (cursor.moveToFirst()){
            do{
                //Creando un objeto de los datos que estan en cada fila
                Profesor profesor = new Profesor();

                profesor.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMNA_PROFESOR_ID))));
                profesor.setNombre(cursor.getString(cursor.getColumnIndex(COLUMNA_PROFESOR_NOMBRE)));


                //Añadiendo el objeto a la lista de materias
                listaProfesores.add(profesor);
            }while(cursor.moveToNext());
        }
        cursor.close();
        baseDatos.close();

        return listaProfesores;
    }




}
