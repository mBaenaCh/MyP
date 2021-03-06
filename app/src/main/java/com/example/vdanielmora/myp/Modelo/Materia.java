package com.example.vdanielmora.myp.Modelo;

import android.content.Context;

import com.example.vdanielmora.myp.Persistencia.BaseDatos;

import java.io.Serializable;
import java.util.ArrayList;

public class Materia implements Serializable {


    private int id;
    private String nombre;
    private String grupo;
    private String horario;
    private String aula;
    private ArrayList<Comentario> comentarios;
    private Context context;

    public Materia(){

    }

    public Materia(Context context){
        this.context = context;
    }

    public Materia(String nombre, String grupo, String horario, String aula) {

        this.nombre = nombre;
        this.grupo = grupo;
        this.horario = horario;
        this.aula = aula;
        comentarios = new ArrayList<>();

    }

    public int getId(){ return id; }

    public void setId (int id){    this.id = id; }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(ArrayList<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public void añadirComentario(Comentario comentario){
        this.comentarios.add(comentario);
    }

    @Override
    public String toString(){
        return getNombre()+" - "+getGrupo()+" - "+getHorario()+" - "+getAula();
    }
}
