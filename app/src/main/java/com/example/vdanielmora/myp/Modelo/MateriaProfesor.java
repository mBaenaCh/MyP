package com.example.vdanielmora.myp.Modelo;

public class MateriaProfesor {

    private int idProfesor;
    private int idMateria;

    public MateriaProfesor() {
    }

    public MateriaProfesor(int idProfesor, int idMateria) {
        this.idProfesor = idProfesor;
        this.idMateria = idMateria;
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
}
