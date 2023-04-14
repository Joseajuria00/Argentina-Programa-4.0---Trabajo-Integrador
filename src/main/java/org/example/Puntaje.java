package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Puntaje {
    public static void main(String[] args) throws IOException {
        Path rutaResultados = Paths.get("C://Users//josea//OneDrive//Escritorio//Programación//Argentina Programa 4.0//Trabajo Integrador//Trabajo Integrador//resultados.csv");
        Path rutaPronosticos = Paths.get("C://Users//josea//OneDrive//Escritorio//Programación//Argentina Programa 4.0//Trabajo Integrador//Trabajo Integrador//pronostico.csv");
        List<Partido> partidos = new ArrayList<>();
        List<Ronda> rondas = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(rutaResultados, StandardCharsets.UTF_8)) {
            reader.readLine();
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partesResult = linea.split(";");
                try {
                    int id = Integer.parseInt(partesResult[1]);
                    Equipo equipo1 = new Equipo(partesResult[2]);
                    Equipo equipo2 = new Equipo(partesResult[5]);
                    int golesEquipo1 = Integer.parseInt(partesResult[3]);
                    int golesEquipo2 = Integer.parseInt(partesResult[4]);
                    int nroRonda = Integer.parseInt(partesResult[0]);
                    Partido partido = new Partido(id, equipo1,equipo2,golesEquipo1,golesEquipo2, nroRonda);
                    partidos.add(partido);

                    if(rondas.size()<nroRonda){rondas.add(new Ronda(nroRonda));}
                    rondas.get(nroRonda-1).addPartido(partido);
                } catch (NumberFormatException e){
                    System.err.println("Hay uno o más goles de un equipo que no son enteros");
                    System.exit(1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Pronostico> pronosticos = new ArrayList<>();
        List<Jugador> jugadores = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(rutaPronosticos, StandardCharsets.UTF_8)) {
            reader.readLine();
            String linea;
            while ((linea = reader.readLine()) != null) {
                    String[] partesPronostico = linea.split(";");
                    int nroRonda = Integer.parseInt(partesPronostico[0]);
                    String nombreJugador = partesPronostico[1];
                    int id =Integer.parseInt(partesPronostico[2]);
                    Partido partido = buscarPartidoId(partidos, id);
                    Equipo equipo = partido.getEquipo1();

                    ResultadoEnum resultado = ResultadoEnum.ganador;
                    if(partesPronostico[4].equals("x")){equipo = partido.getEquipo1();}
                    if(partesPronostico[6].equals("x")){equipo = partido.getEquipo2();}
                    if(partesPronostico[5].equals("x")){
                        equipo = partido.getEquipo1();
                        resultado = ResultadoEnum.empate;
                    }
                    Jugador jugador = buscarJugador(jugadores, nombreJugador);
                    if(jugador==null){
                        jugador = new Jugador(nombreJugador, rondas);
                        jugadores.add(jugador);
                    }
                    Pronostico pronostico = new Pronostico(partido, equipo, resultado, id, nroRonda);
                    pronosticos.add(pronostico);
                    jugador.addPronostico(pronostico);
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }

        for(Jugador jugador : jugadores){
            jugador.Puntos();
        }
        int puntosTOT = 0;
    }
    public static Partido buscarPartidoId(List<Partido> partidos, int id){
        for(Partido partido : partidos){
            if(partido.getId() == id){
                return partido;
            }
        }
        return null;
    }
    public static Jugador buscarJugador(List<Jugador> jugadores, String nombre){
        for(Jugador jugador : jugadores){
            if(jugador.getNombre().equals(nombre)){
                return jugador;
            }
        }
        return null;
    }
}