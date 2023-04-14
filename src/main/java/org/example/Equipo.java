package org.example;

import lombok.Getter;

public class Equipo {
   @Getter
    private String nombre;
    private String descripcion = null;
    public Equipo(String nom){
        this.nombre = nom;
    }
}
