package org.example;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
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

    public void addPronostico (Pronostico pronostico) {
        this.pronosticos.add(pronostico);
    }

    public void Puntos(){
        int puntosTOT = 0;
        System.out.println("\n" + this.nombre);
        for (Ronda ronda : this.rondas){
            int puntosRonda=0;
            for(Partido part : ronda.getPartidos()){
                for(Pronostico pron : this.pronosticos){
                    if(part.getId()==pron.getId()){
                        puntosRonda+=ronda.puntosRonda(pron);
                    }
                }
            }
            System.out.println("Puntos ronda " + ronda.getNro() + ": " + puntosRonda);
            puntosTOT+=puntosRonda;
        }
        System.out.println("Puntos totales "+ nombre +": " + puntosTOT + "(" + puntosTOT + " pronosticos acertados)");
    }
    public int getPuntosRonda(Ronda ronda){
            int puntosRonda=0;
            for(Partido part : ronda.getPartidos()){
                for(Pronostico pron : this.pronosticos){
                    if(part.getId()==pron.getId()){
                        puntosRonda+=ronda.puntosRonda(pron);
                    }
                }
            }
            return puntosRonda;
    }
}
