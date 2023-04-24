package org.example;

import lombok.Getter;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Fase {
    //Clase Fase: Tiene como atributos el número de fase y una lista de rondas.
    // Tiene 2 constructores, los Getter necesarios y 3 funciones (addRonda, puntosFase y cantPartidos).
    @Getter
    private int nro;
    @Getter
    private List<Ronda> rondas = new ArrayList<>();

    public Fase(int nro){
        this.nro = nro;
    }
    public Fase(int nro, List<Ronda> rd){
        this.nro = nro;
        this.rondas = rd;
    }

    public void addRonda (Ronda ronda) {
        //Recibe como parametro una ronda actualizada. Si esa ronda ya pertenece a la fase, actualiza sus partidos.
        // En el caso contrario agrega una nueva ronda a la lista de rondas.
        boolean ingreso = false;
        if(rondas.isEmpty()){
            this.rondas.add(ronda);
        } else {
            for (Ronda rd : this.rondas){
                if(rd.getNro() == ronda.getNro()){
                    rondas.get(rondas.indexOf(rd)).setPartidos(ronda.getPartidos());
                    ingreso = true;
                }
            }
            if(!ingreso){
                this.rondas.add(ronda);
            }
        }
    }
    public int puntosFase(Jugador jugador){//Recibe un jugador y devuelve la puntuación de la fase.
        int puntos = 0;
        for(Ronda ronda : rondas){
            for(Partido partido : ronda.getPartidos()){
                for(Pronostico pron : jugador.getPronosticos()){
                    if(partido.getId()==pron.getId()){
                        puntos+=pron.puntosPronostico();
                    }
                }
            }
        }
        return puntos;
    }
    public int cantPartidos(){//Devuelve la cantidad de partidos que hay en la fase.
        int cantidad = 0;
        for(Ronda ronda : rondas){
            cantidad+= ronda.cantPartidos();
        }
        return cantidad;
    }
}