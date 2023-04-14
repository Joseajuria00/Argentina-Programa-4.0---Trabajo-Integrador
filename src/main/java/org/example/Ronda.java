package org.example;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Ronda {
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
    public void addPartido (Partido partido) {
        this.partidos.add(partido);
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public int puntosRonda(Pronostico pronostico) {
        return pronostico.puntos();
    }
}
