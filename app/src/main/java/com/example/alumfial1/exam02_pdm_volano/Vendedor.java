package com.example.alumfial1.exam02_pdm_volano;

public class Vendedor {
    private String idvendedor;
    private String correo;
    private String contraseña;
    private String nombre;
    private String apellido;

    public Vendedor() {
    }

    public Vendedor(String idvendedor, String correo, String contraseña, String nombre, String apellido) {
        this.idvendedor = idvendedor;
        this.correo = correo;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getIdvendedor() {
        return idvendedor;
    }

    public void setIdvendedor(String idvendedor) {
        this.idvendedor = idvendedor;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
