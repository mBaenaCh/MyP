package com.example.vdanielmora.myp.Modelo;

import java.util.ArrayList;

public class Profesor {

    private int id;
    private String nombre;
    private String apellido;
    private ArrayList <Materia> materias;
    private String facultad;

    public Profesor() {
    }

    public Profesor(int id, String nombre, String apellido, String facultad) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.facultad = facultad;
        materias = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido(){
        return apellido;
    }

    public void setApellido(String apellido){
        this.apellido = apellido;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad (String facultad){
        this.facultad = facultad;
    }

    public ArrayList<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(ArrayList<Materia> materias) {
        this.materias = materias;
    }

    @Override
    public String toString() {
        return getNombre()+","+getApellido()+","+getFacultad();
    }
}
