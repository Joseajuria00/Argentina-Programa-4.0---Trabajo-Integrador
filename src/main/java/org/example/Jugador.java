package org.example;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    //Clase Jugador: Tiene como atributos un nombre, una lista de rondas y una lista de pronósticos.
    //Tiene 2 constructores, los Getter de los atributos que sean necesarios y 3 funciones.
    @Getter
    private String nombre;
    @Getter
    private List<Ronda> rondas = new ArrayList<>();
    private List<Pronostico> pronosticos = new ArrayList<>();
    public Jugador(String nombre, List<Ronda> rondas){
        this.nombre = nombre;
        this.rondas = rondas;
    }
    public Jugador(String nombre, List<Ronda> rondas, List<Pronostico> pronos){
        this.nombre = nombre;
        this.rondas = rondas;
        this.pronosticos = pronos;
    }

    public void addPronostico (Pronostico pronostico) {//Recibe como parámetro un pronóstico y lo agrega a la lista de pronósticos.
        this.pronosticos.add(pronostico);
    }

    public int Puntos(){//Devuelve los puntos totales del jugador correspondiente
        int puntosTOT = 0;
        for (Ronda ronda : this.rondas){
            int puntosRonda=0;
            puntosRonda = ronda.puntosRonda(pronosticos);
            /*
            for(Partido part : ronda.getPartidos()){
                for(Pronostico pron : this.pronosticos){
                    if(part.getId()==pron.getId()){
                        puntosRonda+=ronda.puntosRonda(pron);
                    }
                }
            }*/
            puntosTOT+=puntosRonda;
        }
        return puntosTOT;
    }
    public int getPuntosRonda(Ronda ronda){//Devuelve un número de ronda y devuelve los puntos obtenidos por un jugador en esa ronda.
        int puntosRonda=0;
        puntosRonda = ronda.puntosRonda(pronosticos);
        return puntosRonda;
    }
}
