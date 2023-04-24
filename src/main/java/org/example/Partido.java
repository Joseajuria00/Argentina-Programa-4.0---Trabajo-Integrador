package org.example;

import lombok.Getter;
import lombok.Setter;

public class Partido {
    //Clase Partido: Tiene como atributos 2 equipos que jugaron un partido entre ellos, los goles de cada uno,
    // el ID del partido, el número de ronda y el número de fase.
    // Contiene un constructor, los Getter correspondientes y el método resultado.
    @Getter
    private Equipo equipo1;
    @Getter
    private Equipo equipo2;
    @Getter
    private int golesEquipo1;
    @Getter
    private int golesEquipo2;
    @Getter
    private int id;
    @Getter
    private int nroRonda;
    @Getter
    private int nroFase;
    public Partido(int id, Equipo eq1, Equipo eq2, int golesEq1, int golesEq2, int ronda, int fase){
        this.id = id;
        this.equipo1 = eq1;
        this.equipo2 = eq2;
        this.golesEquipo1 = golesEq1;
        this.golesEquipo2 = golesEq2;
        this.nroRonda = ronda;
        this.nroFase = fase;
    }

    public ResultadoEnum resultado(Equipo equipo){
    //Recibe uno de los dos equipos del partido y devuelve su resultado en ese partido.
        if (this.golesEquipo1==this.getGolesEquipo2()) {
            return  ResultadoEnum.empate;
        }
        if(equipo.equals(equipo1)){
            if(golesEquipo1>golesEquipo2){
                return ResultadoEnum.ganador;
            } else if (golesEquipo1<golesEquipo2) {
                return ResultadoEnum.perdedor;
            }
        }
        if (equipo.equals(equipo2)) {
            if(golesEquipo1>golesEquipo2){
                return ResultadoEnum.perdedor;
            } else if (golesEquipo1<golesEquipo2) {
                return ResultadoEnum.ganador;
            }
        }
        return ResultadoEnum.desconocido;
    }
}