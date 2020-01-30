package com.example.listas_danielmirorego;

public class Alumno {

    private String nombre;
    private String curso;
    private String ciclo;
    private int imagen;

    public Alumno() {
    }

    public Alumno(String nombre, String curso, String ciclo, int imagen) {
        this.nombre = nombre;
        this.curso = curso;
        this.ciclo = ciclo;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
