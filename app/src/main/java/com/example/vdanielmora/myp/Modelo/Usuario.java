package com.example.vdanielmora.myp.Modelo;

public class Usuario {

    private int id;
    private String nombreUsuario;
    private String contraseña;

    public Usuario() {

    }

    public Usuario(int id, String nombreUsuario, String contraseña) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public String toString(){
        return getNombreUsuario()+" , "+getContraseña();
    }


}
