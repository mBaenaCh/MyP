package com.example.vdanielmora.myp.Modelo;

public class MateriaProfesor {

    private int id;
    private int idProfesor;
    private int idMateria;

    public MateriaProfesor() {
    }

    public MateriaProfesor(int idProfesor, int idMateria) {
        this.idProfesor = idProfesor;
        this.idMateria = idMateria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    @Override
    public String toString() {
        return idProfesor + " - " + idMateria;
    }
}
