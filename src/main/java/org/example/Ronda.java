package org.example;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Ronda {
    //Clase Ronda: Tiene como atributos el número de ronda y una lista de partidos.
    // Tiene 2 constructores, los Getter necesarios y 2 funciones (addPartido y puntosRonda).
    @Getter
    private int nro;
    private List<Partido> partidos = new ArrayList<>();

    public Ronda(int nro){
        this.nro = nro;
    }
    public Ronda(int nro, List<Partido> partidos){
        this.nro = nro;
        this.partidos = partidos;
    }
    public void addPartido (Partido partido) {//Recibe como parámetro un partido y lo agrega a la lista de partidos.
        this.partidos.add(partido);
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public int puntosRonda1(Pronostico pronostico) {
        return pronostico.puntos();
    }
    public int puntosRonda(List<Pronostico> pronosticos){
        //Recibe una lista de pronósticos y devuelve solo la puntuación de la ronda correspondiente.
        int puntos = 0;
        for(Partido part : partidos){
            for(Pronostico pron : pronosticos){
                if(part.getId()==pron.getId()){
                    puntos+=pron.puntos();
                }
            }
        }
        return puntos;
    }
}
