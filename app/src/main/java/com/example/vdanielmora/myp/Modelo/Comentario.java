package com.example.vdanielmora.myp.Modelo;

public class Comentario {

    private int id;
    private String texto;
    private String nombreUsuario;

    public Comentario() {
    }

    public Comentario(int id, String texto, String nombreUsuario) {
        this.id = id;
        this.texto = texto;
        this.nombreUsuario = nombreUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
}
