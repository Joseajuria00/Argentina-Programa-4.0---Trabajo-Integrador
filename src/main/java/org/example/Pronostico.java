package org.example;

import lombok.Getter;

public class Pronostico {
    //Clase Pronostico: Tiene como atributos un partido, un equipo, el resultado, el ID , el número de fase y el valor al acertar un resultado.
    // Tiene un constructor, los Getter correspondientes y el método puntosPronostico.
    @Getter
    private Partido partido;
    @Getter
    private Equipo equipo;
    @Getter
    private ResultadoEnum resultado;
    @Getter
    private int id;
    @Getter
    private int nroFase;
    @Getter
    private int valorAcierto;
    public Pronostico(Partido partido, Equipo eq, ResultadoEnum result, int id, int fase, int acierto){
        this.partido = partido;
        this.equipo = eq;
        this.resultado = result;
        this.id = id;
        this.nroFase = fase;
        this.valorAcierto = acierto;
    }
    public int puntosPronostico(){//No recibe parámetros. Busca si se acertó el pronostico y devuelve la puntuación obtenida.
        if(this.partido.resultado(this.equipo).equals(this.resultado)){
            return this.valorAcierto;
        }
        return 0;
    }
}