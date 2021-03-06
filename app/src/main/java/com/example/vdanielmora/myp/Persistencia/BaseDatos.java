package com.example.vdanielmora.myp.Persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.vdanielmora.myp.Modelo.Comentario;
import com.example.vdanielmora.myp.Modelo.Materia;
import com.example.vdanielmora.myp.Modelo.Profesor;
import com.example.vdanielmora.myp.Modelo.Usuario;

import java.sql.Array;
import java.sql.SQLData;
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
    private static final String TABLA_COMENTARIO = "comentario";
    private static final String TABLA_COMENTARIO_MATERIA = "comentario_materia";
    private static final String TABLA_MATERIA_PROFESOR = "materia_profesor";

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
    private static final String COLUMNA_PROFESOR_FACULTAD = "profesor_facultad";

    /* ----- COLUMNAS QUE COMPONEN LA TABLA COMENTARIO -----
     *
     */
    private static final String COLUMNA_COMENTARIO_ID = "comentario_id";
    private static final String COLUMNA_COMENTARIO_TEXTO = "comentario_texto";
    private static final String COLUMNA_COMENTARIO_NUSUARIO = "comentario_nombreUsuario";

    /* ----- COLUMNAS QUE COMPONEN LA TABLA MATERIA PROFESOR -----
     *
     *
     */
    private static final String COLUMNA_MP_ID = "mp_id";
    private static final String COLUMNA_MP_ID_MATERIA = "mp_materia_id";
    private static final String COLUMNA_MP_ID_PROFESOR = "mp_profesor_id";

    /* ----- COLUMNAS QUE COMPONEN LA TABLA COMENTARIO MATERIA -----
     *
     *
     */
    private static final String COLUMNA_CM_ID = "cm_id";
    private static final String COLUMNA_CM_ID_COMENTARIO = "cm_comentario_id";
    private static final String COLUMNA_CM_ID_MATERIA = "cm_materia_id";




    //Query para la creacion de la tabla de usuario
    private String CREAR_TABLA_USUARIO = "CREATE TABLE "+TABLA_USUARIO+ " ("+
            COLUMNA_USUARIO_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            COLUMNA_USUARIO_NOMBRE+" TEXT,"+
            COLUMNA_USUARIO_CONTRASEÑA+" TEXT"+")";

    //Query para la creacion de la tabla materia
    private String CREAR_TABLA_MATERIA = "CREATE TABLE "+TABLA_MATERIA+ " ("+
            COLUMNA_MATERIA_ID+" INTEGER PRIMARY KEY,"+
            COLUMNA_MATERIA_NOMBRE+" TEXT,"+
            COLUMNA_MATERIA_GRUPO+" TEXT,"+
            COLUMNA_MATERIA_HORARIO+" TEXT,"+
            COLUMNA_MATERIA_AULA+" TEXT"+")";

    //Query para la creacion de la tabla profesor
    private String CREAR_TABLA_PROFESOR = "CREATE TABLE "+TABLA_PROFESOR+" ("+
            COLUMNA_PROFESOR_ID+" INTEGER PRIMARY KEY,"+
            COLUMNA_PROFESOR_NOMBRE+" TEXT,"+
            COLUMNA_PROFESOR_FACULTAD+" TEXT"+")";

    //Query para la creacion de la tabla comentario
    private String CREAR_TABLA_COMENTARIO = "CREATE TABLE "+TABLA_COMENTARIO+ " ("+
            COLUMNA_COMENTARIO_ID+" INTEGER PRIMARY KEY,"+
            COLUMNA_COMENTARIO_TEXTO+" TEXT,"+
            COLUMNA_COMENTARIO_NUSUARIO+" TEXT"+")";


    //Query para la creacion de la tabla materia profesor
    private String CREAR_TABLA_MP = "CREATE TABLE "+TABLA_MATERIA_PROFESOR+ " ("+
            COLUMNA_MP_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            COLUMNA_MP_ID_MATERIA+" INTEGER," +
            COLUMNA_MP_ID_PROFESOR+" INTEGER," +
            "FOREIGN KEY ("+COLUMNA_MP_ID_MATERIA+") REFERENCES "+TABLA_MATERIA+"("+COLUMNA_MATERIA_ID+"),"+
            "FOREIGN KEY ("+COLUMNA_MP_ID_PROFESOR+") REFERENCES "+TABLA_PROFESOR+"("+COLUMNA_PROFESOR_ID+")"+")";

    //Query para la creacion de la tabla comentario materia
    private String CREAR_TABLA_CM = "CREATE TABLE "+TABLA_COMENTARIO_MATERIA+ " ("+
            COLUMNA_CM_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            COLUMNA_CM_ID_COMENTARIO+ " INTEGER,"+
            COLUMNA_CM_ID_MATERIA+ " INTEGER,"+
            "FOREIGN KEY ("+COLUMNA_CM_ID_COMENTARIO+") REFERENCES "+TABLA_COMENTARIO+"("+COLUMNA_COMENTARIO_ID+"),"+
            "FOREIGN KEY ("+COLUMNA_CM_ID_MATERIA+") REFERENCES "+TABLA_MATERIA+"("+COLUMNA_MATERIA_ID+")"+")";

    private String DROP_TABLE = "DROP TABLE IF EXISTS "+NOMBRE_BASEDATOS;


    public BaseDatos(Context context){

        super(context,NOMBRE_BASEDATOS,null,VERSION_BASEDATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREAR_TABLA_USUARIO);
        db.execSQL(CREAR_TABLA_MATERIA);
        db.execSQL(CREAR_TABLA_PROFESOR);
        db.execSQL(CREAR_TABLA_COMENTARIO);
        db.execSQL(CREAR_TABLA_CM);
        db.execSQL(CREAR_TABLA_MP);

    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
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
        ContentValues contenedor = new ContentValues();
        SQLiteDatabase baseDatos = this.getWritableDatabase();

        contenedor.put(COLUMNA_MATERIA_ID,materia.getId());
        contenedor.put(COLUMNA_MATERIA_NOMBRE,materia.getNombre());
        contenedor.put(COLUMNA_MATERIA_GRUPO, materia.getGrupo());
        contenedor.put(COLUMNA_MATERIA_HORARIO,materia.getHorario());
        contenedor.put(COLUMNA_MATERIA_AULA,materia.getAula());

        try{
            baseDatos.insertOrThrow(TABLA_MATERIA,null, contenedor);
            mensaje = "Materia guardada correctamente";
        }catch (SQLException e){
            mensaje = "Materia no ingresada correctamente";
        }

        return mensaje;
    }

    //Metodo para retornar la lista de materias que estan registradas en la base de datos
    public ArrayList<Materia> obtenerTodasLasMaterias(){
        //Arreglo de columnas a obtener
        String[]columnas={
                COLUMNA_MATERIA_ID,
                COLUMNA_MATERIA_NOMBRE,
                COLUMNA_MATERIA_GRUPO,
                COLUMNA_MATERIA_HORARIO,
                COLUMNA_MATERIA_AULA
        };

        String sortOrder = COLUMNA_MATERIA_ID +" ASC";

        //Instanciacion de la colection "ArrayList" por medio de referencia a clase general "List"
        ArrayList<Materia> listaMaterias = new ArrayList<Materia>();
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
        ContentValues contenedor = new ContentValues();

        contenedor.put(COLUMNA_PROFESOR_ID, profesor.getId());
        contenedor.put(COLUMNA_PROFESOR_NOMBRE, profesor.getNombre());
        contenedor.put(COLUMNA_PROFESOR_FACULTAD,profesor.getFacultad());

        try {
            baseDatos.insertOrThrow(TABLA_PROFESOR,null,contenedor);
            mensaje = "Registrado correctamente";
        }catch (SQLException e){
            mensaje = "No registrado correctamente";
        }
        return mensaje;
    }

    //Metodo para retornar la lista de profesores que estan registrados en la base de datos
    public ArrayList<Profesor> obtenerTodosLosProfesores(){
        //Arreglo de columnas a obtener
        String[]columnas={
                COLUMNA_PROFESOR_ID,
                COLUMNA_PROFESOR_NOMBRE,
                COLUMNA_PROFESOR_FACULTAD
        };

        //Forma como seran ordenados los usuarios
        String sortOrder = COLUMNA_PROFESOR_ID +" ASC";

        //Instanciacion de la colection "ArrayList" por medio de referencia a clase general "List"
        ArrayList<Profesor> listaProfesores = new ArrayList<>();
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
            do {
                //Creando un objeto de los datos que esta nen cada fila
                Profesor profesor = new Profesor();
                profesor.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMNA_PROFESOR_ID))));
                profesor.setNombre(cursor.getString(cursor.getColumnIndex(COLUMNA_PROFESOR_NOMBRE)));
                profesor.setFacultad(cursor.getString(cursor.getColumnIndex(COLUMNA_PROFESOR_FACULTAD)));

                //Añadiendo el objeto a la lista de usuarios
                listaProfesores.add(profesor);
            }while(cursor.moveToNext());
        }
        cursor.close();
        baseDatos.close();

        return listaProfesores;
    }

    //Metodo para añadir un objeto comentario a la base de datos
    public String añadirComentario(Comentario comentario){
        String mensaje="";

        SQLiteDatabase baseDatos = this.getWritableDatabase();

        ContentValues valores = new ContentValues();

        valores.put(COLUMNA_COMENTARIO_ID,comentario.getId());
        valores.put(COLUMNA_COMENTARIO_TEXTO,comentario.getTexto());
        valores.put(COLUMNA_COMENTARIO_NUSUARIO,comentario.getNombreUsuario());

        try {
            baseDatos.insertOrThrow(TABLA_COMENTARIO, null, valores);
            mensaje = "Registrado correctamente";
        }catch (SQLException e){
            mensaje = "No registrado correctamente";
        }
        return mensaje;
    }

    //Metodo para obtener de forma ascendente, segun el id, todos los comentarios en la base de datos
    public ArrayList<Comentario> obtenerTodosLosComentarios(){
        //Arreglo de columnas a obtener
        String[]columnas={
                COLUMNA_COMENTARIO_ID,
                COLUMNA_COMENTARIO_TEXTO,
                COLUMNA_COMENTARIO_NUSUARIO
        };

        //Forma como seran ordenados los usuarios
        String sortOrder = COLUMNA_COMENTARIO_ID +" ASC";

        //Instanciacion de la colection "ArrayList" por medio de referencia a clase general "List"
        ArrayList<Comentario> listaComentarios = new ArrayList<>();
        SQLiteDatabase baseDatos = this.getReadableDatabase();
        Cursor cursor = baseDatos.query(
                TABLA_COMENTARIO,  //Tabla que consultaremos
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
                Comentario comentario = new Comentario();
                comentario.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMNA_COMENTARIO_ID))));
                comentario.setTexto(cursor.getString(cursor.getColumnIndex(COLUMNA_COMENTARIO_TEXTO)));
                comentario.setNombreUsuario(cursor.getString(cursor.getColumnIndex(COLUMNA_COMENTARIO_NUSUARIO)));
                //Añadiendo el objeto a la lista de usuarios
                listaComentarios.add(comentario);
            }while(cursor.moveToNext());
        }
        cursor.close();
        baseDatos.close();

        return listaComentarios;
    }

    public String añadirMateriaProfesor(int idMateria, int idProfesor){

        String mensaje="";

        try{
            SQLiteDatabase baseDatos = this.getWritableDatabase();

            baseDatos.execSQL("INSERT INTO "+TABLA_MATERIA_PROFESOR+" ("+COLUMNA_MP_ID_MATERIA+", "+COLUMNA_MP_ID_PROFESOR+") VALUES ("+idMateria+", "+idProfesor+")");

            mensaje = "Materia asignada correctamente";
        }catch (SQLException e){
            mensaje = "Materia asignada incorrectamente";
        }

        return mensaje;
    }

    public String añadirComentarioMateria(int idComentario, int idMateria){
        String mensaje = "";

        try{
            SQLiteDatabase baseDatos = this.getWritableDatabase();
            baseDatos.execSQL("INSERT INTO "+TABLA_COMENTARIO_MATERIA+" ("+COLUMNA_CM_ID_COMENTARIO+", "+COLUMNA_CM_ID_MATERIA+") VALUES ("+idComentario+", "+idMateria+")");
            mensaje = "comentario asignado correctamente";
        }catch(SQLException e){
            mensaje = "Comentario asignado incorrectamente";
        }
        return mensaje;
    }

    public ArrayList<String> listaDeMateriasProfesor(String idProfesor){
        ArrayList<String> materias = new ArrayList<>();
        SQLiteDatabase baseDatos = this.getReadableDatabase();
        String idMateria = "";
        String[] parametros={
                idProfesor
        };

        Cursor cursor = baseDatos.rawQuery("SELECT "+COLUMNA_MP_ID_MATERIA+" FROM "+TABLA_MATERIA_PROFESOR+" WHERE "+COLUMNA_MP_ID_PROFESOR+"=? ",parametros);

        if(cursor!=null && cursor.moveToFirst()) {
            do {

                idMateria = Integer.toString(cursor.getInt(cursor.getColumnIndex(COLUMNA_MP_ID_MATERIA)));
                materias.add(idMateria);

            } while (cursor.moveToNext());

            cursor.close();
        }else{
            Log.d("","TABLA VACIA?");
        }
        return materias;
    }

    public ArrayList<String> listaComentariosMateria(String idMateria){
        ArrayList<String> comentarios = new ArrayList<>();
        SQLiteDatabase baseDatos = this.getReadableDatabase();
        String idComentario = "";
        String[] parametros={
                idMateria
        };

        Cursor cursor = baseDatos.rawQuery("SELECT "+COLUMNA_CM_ID_COMENTARIO+" FROM "+TABLA_COMENTARIO_MATERIA+" WHERE "+COLUMNA_CM_ID_MATERIA+"=? ",parametros);

        if(cursor!=null && cursor.moveToFirst()) {
            do {

                idComentario = Integer.toString(cursor.getInt(cursor.getColumnIndex(COLUMNA_CM_ID_COMENTARIO)));
                comentarios.add(idComentario);

            } while (cursor.moveToNext());

            cursor.close();
        }else{
            Log.d("","TABLA VACIA?");
        }
        return comentarios;
    }

}
