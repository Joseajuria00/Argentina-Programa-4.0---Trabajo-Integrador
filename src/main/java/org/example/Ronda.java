package org.example;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Ronda {
    //Clase Ronda: Tiene como atributos el número de ronda y una lista de partidos.
    // Tiene 2 constructores, los Getter necesarios y 3 funciones (addPartido, puntosRonda y cantPartidos).
    @Getter
    private int nro;
    @Setter
    @Getter
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
    public int puntosRonda(Jugador jugador){//Recibe un jugador y devuelve la puntuación de la ronda.
        int puntos = 0;
        for(Partido part : partidos){
            for(Pronostico pron : jugador.getPronosticos()){
                if(part.getId()==pron.getId()){
                    puntos+=pron.puntosPronostico();
                }
            }
        }
        return puntos;
    }
    public int cantPartidos(){//Devuelve la cantidad de partidos que hay en la ronda.
        return partidos.size();
    }
}