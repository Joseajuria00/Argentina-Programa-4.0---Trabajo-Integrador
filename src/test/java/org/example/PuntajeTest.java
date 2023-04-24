package org.example;

import java.util.Arrays;
import junit.framework.TestCase;
import org.junit.Test;


public class PuntajeTest extends TestCase{
    public void testDosRondasConsecutivas(){
        Equipo argentina = new Equipo("Argentina");
        Equipo arabiaSaudita = new Equipo("Arabia Saudita");
        Equipo mexico = new Equipo("Mexico");
        Equipo polonia = new Equipo("Polonia");
        Partido partido1 = new Partido(1, argentina, arabiaSaudita, 1, 2, 1,1);
        Partido partido2 = new Partido(2, polonia, mexico, 0, 0, 1,1);
        Partido partido3 = new Partido(3, argentina, mexico, 2, 0, 2,1);
        Partido partido4 = new Partido(4, arabiaSaudita, polonia, 0, 2, 2,1);
        Ronda r1 = new Ronda(1, Arrays.asList(partido1,partido2));
        Ronda r2 = new Ronda(1, Arrays.asList(partido3,partido4));
        Fase f1 = new Fase(1, Arrays.asList(r1,r2));
        Pronostico p1 = new Pronostico(partido1, argentina, ResultadoEnum.empate,1,1, 1);
        Pronostico p2 = new Pronostico(partido2, polonia, ResultadoEnum.empate,2,1,1);
        Pronostico p3 = new Pronostico(partido3, argentina, ResultadoEnum.ganador,3,2,1);
        Pronostico p4 = new Pronostico(partido4, polonia, ResultadoEnum.ganador,4,3,1);
        Jugador j1 = new Jugador("JOSE", Arrays.asList(f1), Arrays.asList(p1,p2,p3,p4));
        int dosRondas = r1.puntosRonda(j1) + r2.puntosRonda(j1);
        assertEquals(3, dosRondas);
    }
}