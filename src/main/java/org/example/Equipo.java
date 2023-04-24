package org.example;

import lombok.Getter;

public class Equipo {
    //clase Equipo: Tiene como atributos un nombre y una descripción
    //Tiene un constructor y un solo Getter.
   @Getter
    private String nombre;
    private String descripcion = null;
    public Equipo(String nom){
        this.nombre = nom;
    }
}