package com.example.vdanielmora.myp.Modelo;

public class ComentarioUsuario {

    private int idUsuario;
    private int idComentario;

    public ComentarioUsuario() {
    }

    public ComentarioUsuario(int idUsuario, int idComentario) {
        this.idUsuario = idUsuario;
        this.idComentario = idComentario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }
}
