package com.example.vdanielmora.myp.Modelo;

public class Comentario {

    private int id;
    private String texto;
    private String nombreUsuario;

    public Comentario() {
    }

    public Comentario(String texto, String nombreUsuario) {
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
