package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Puntaje {
    public static void main(String[] args) throws IOException, SQLException {
        String[] partesInfo = leerinfo();
        MySQLCon db = new MySQLCon();
        db.connectDatabase(partesInfo[0],partesInfo[1],partesInfo[2]);
        int valorAcierto = Integer.parseInt(partesInfo[3]), puntosRondaPerfecta = Integer.parseInt(partesInfo[4]), puntosFasePerfecta = Integer.parseInt(partesInfo[5]);

        ResultSet rs = db.executeQuery("SELECT * FROM resultados");
        List<Partido> partidos = new ArrayList<>();
        List<Ronda> rondas = new ArrayList<>();
        List<Fase> fases = new ArrayList<>();
        //int cantRondas=0;
        while (rs.next()) {
            try {
                int nroFase = Integer.parseInt(rs.getString(1));
                int nroRonda = Integer.parseInt(rs.getString(2));
                int id = Integer.parseInt(rs.getString(3));
                Equipo equipo1 = new Equipo(rs.getString(4));
                int golesEquipo1 = Integer.parseInt(rs.getString(5));
                int golesEquipo2 = Integer.parseInt(rs.getString(6));
                Equipo equipo2 = new Equipo(rs.getString(7));

                Partido partido = new Partido(id, equipo1,equipo2,golesEquipo1,golesEquipo2, nroRonda, nroFase);
                partidos.add(partido);


                if(fases.size()<nroFase){
                    fases.add(new Fase(nroFase));
                    rondas.clear();
                }

                if(rondas.size()<nroRonda){rondas.add(new Ronda(nroRonda));}
                rondas.get(nroRonda-1).addPartido(partido);//Se puede utilizar un HashMap

                fases.get(nroFase-1).addRonda(rondas.get(nroRonda-1));
            } catch (NumberFormatException e){
                System.err.println("Hay uno o más goles de un equipo que no son enteros");
                System.exit(1);
            }
        }

        rs = db.executeQuery("SELECT * FROM pronosticos");
        //List<Pronostico> pronosticos = new ArrayList<>();
        List<Jugador> jugadores = new ArrayList<>();
        while (rs.next()) {
            int nroFase = Integer.parseInt(rs.getString(1));
            int nroRonda = Integer.parseInt(rs.getString(2));
            String nombreJugador = rs.getString(3);
            int id =Integer.parseInt(rs.getString(4));
            Partido partido = buscarPartidoId(partidos, id);
            Equipo equipo = partido.getEquipo1();
            ResultadoEnum resultado = ResultadoEnum.ganador;
            if(rs.getString(6)!=null && rs.getString(6).equals("x")){equipo = partido.getEquipo1();}
            if(rs.getString(8)!=null && rs.getString(8).equals("x")){equipo = partido.getEquipo2();}
            if(rs.getString(7)!=null && rs.getString(7).equals("x")){
                equipo = partido.getEquipo1();
                resultado = ResultadoEnum.empate;
            }
            Jugador jugador = buscarJugador(jugadores, nombreJugador);
            if(jugador==null){
                jugador = new Jugador(nombreJugador, fases);
                jugadores.add(jugador);
            }
            Pronostico pronostico = new Pronostico(partido, equipo, resultado, id, nroFase, valorAcierto);
            //pronosticos.add(pronostico);
            jugador.addPronostico(pronostico);
        }

        for(Jugador jugador : jugadores){
            int puntosTOT=0, puntosExtras=0;
            System.out.println("\n" + "\033[34m" + jugador.getNombre() + "\033[0m");
            for(Fase fase : fases){
                int puntosFase=0;
                System.out.println("\033[36mFASE " + fase.getNro() + "\033[0m");
                for(Ronda ronda : fase.getRondas()){
                    System.out.print("\tPuntos ronda " + ronda.getNro() + ": " + ronda.puntosRonda(jugador));
                    int aux = rondaPerfecta(jugador,ronda,valorAcierto, puntosRondaPerfecta);
                    puntosExtras += aux;
                    puntosFase += aux;
                    System.out.println();
                }
                puntosFase += fase.puntosFase(jugador);
                System.out.print("Puntos fase: " + puntosFase);
                puntosExtras += fasePerfecta(jugador,fase,valorAcierto, puntosFasePerfecta);;
                System.out.println();
            }
            puntosTOT = jugador.Puntos() + puntosExtras;
            System.out.println("Puntos totales "+ jugador.getNombre() +": " + puntosTOT + "(" + jugador.Puntos()/valorAcierto + " pronosticos acertados)");
        }
        db.cerrar();
    }
    public static int fasePerfecta(Jugador jugador, Fase fase, int acierto, int puntosFasePerfecta){
        int cantAciertos = fase.puntosFase(jugador)/acierto;
        if(cantAciertos==fase.cantPartidos()){
            System.out.print(" +" + puntosFasePerfecta + "(Ha acertados todos los partidos de esta fase).");
            return puntosFasePerfecta;
        }
        return 0;
    }
    public static int rondaPerfecta(Jugador jugador, Ronda ronda, int acierto, int puntosRondaPerfecta){
        int cantAciertos = ronda.puntosRonda(jugador)/acierto;
        if(cantAciertos==ronda.cantPartidos()){
            System.out.print(" +" + puntosRondaPerfecta + "(Ha acertado todos los partidos de esta ronda)." );
            return puntosRondaPerfecta;
        }
        return 0;
    }
    public static Partido buscarPartidoId(List<Partido> partidos, int id){
    //Recibe una lista de partidos y un ID correspondiente a un pronóstico. Devuelve el partido con el mismo ID.
        for(Partido partido : partidos){
            if(partido.getId() == id){
                return partido;
            }
        }
        return null;
    }
    public static Jugador buscarJugador(List<Jugador> jugadores, String nombre){
    //Recibe una lista de jugadores y un nombre de un jugador. Devuelve el jugador que tenga ese nombre.
        for(Jugador jugador : jugadores){
            if(jugador.getNombre().equals(nombre)){
                return jugador;
            }
        }
        return null;
    }
    public static String[] leerinfo(){
        String[] partesInfo = new String[0];
        Path rutainfo = Paths.get("informacion.csv");
        try (BufferedReader reader = Files.newBufferedReader(rutainfo, StandardCharsets.UTF_8)) {
            reader.readLine();
            String linea = reader.readLine();
            partesInfo = linea.split(";");
            return partesInfo;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return partesInfo;
    }
}