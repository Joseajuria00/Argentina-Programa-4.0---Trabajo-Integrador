package org.example;

import lombok.Getter;

public class Pronostico {
    @Getter
    private Partido partido;
    @Getter
    private Equipo equipo;
    @Getter
    private ResultadoEnum resultado;
    @Getter
    private int id;
    @Getter
    private int ronda;
    public Pronostico(Partido partido, Equipo eq, ResultadoEnum result, int id, int ronda){
        this.partido = partido;
        this.equipo = eq;
        this.resultado = result;
        this.id = id;
        this.ronda = ronda;
    }
    public int puntos(){
        if(partido.resultado(equipo).equals(resultado)){
            return 1;
        }
        return 0;
    }
}
