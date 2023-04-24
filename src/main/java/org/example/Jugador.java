package org.example;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    //Clase Jugador: Tiene como atributos un nombre, una lista de fases y una lista de pronósticos.
    //Tiene 2 constructores, los Getter de los atributos que sean necesarios y 2 funciones.
    @Getter
    private String nombre;
    @Getter
    private List<Fase> fases = new ArrayList<>();
    @Getter
    private List<Pronostico> pronosticos = new ArrayList<>();
    public Jugador(String nombre, List<Fase> fases){
        this.nombre = nombre;
        this.fases = fases;
    }
    public Jugador(String nombre, List<Fase> fs, List<Pronostico> pronos){
        this.nombre = nombre;
        this.fases = fs;
        this.pronosticos = pronos;
    }

    public void addPronostico (Pronostico pronostico) {//Recibe como parámetro un pronóstico y lo agrega a la lista de pronósticos.
        this.pronosticos.add(pronostico);
    }

    public int Puntos(){//Devuelve los puntos totales del jugador correspondiente sin contar puntos extras
        int puntosTOT = 0;
        for(Fase fase : this.fases){
            puntosTOT+=fase.puntosFase(this);
        }
        return puntosTOT;
    }
}
