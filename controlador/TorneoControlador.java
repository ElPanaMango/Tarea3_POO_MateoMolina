package controlador;

import modelo.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TorneoControlador {
    private Torneo torneo;
    private final String ARCHIVO_DATOS = "torneo.ser";

    public void inicializarTorneo(List<String> nombres) throws NumeroEquiposInvalidoException {
        this.torneo = new Torneo(nombres);
    }

    public void ejecutarSorteoCompleto() throws SorteoInvalidoException {
        if (torneo == null) {
            throw new SorteoInvalidoException("El torneo no ha sido inicializado.");
        }

        List<Equipo> actual = torneo.getEquipos();

        // Octavos de final (16 equipos)
        if (actual.size() == 16) {
            List<String> partidos = torneo.sortearEtapaRecursivo(actual, "Octavos de Final");
            actual = simularGanadores(partidos);
        }
        // Cuartos de final (8 equipos)
        if (actual.size() == 8) {
            List<String> partidos = torneo.sortearEtapaRecursivo(actual, "Cuartos de Final");
            actual = simularGanadores(partidos);
        }
        // Semifinal (4 equipos)
        if (actual.size() == 4) {
            List<String> partidos = torneo.sortearEtapaRecursivo(actual, "Semifinal");
            actual = simularGanadores(partidos);
        }
        // Final (2 equipos)
        if (actual.size() == 2) {
            torneo.sortearEtapaRecursivo(actual, "Gran Final");
        }
    }

    private List<Equipo> simularGanadores(List<String> partidos) {
        List<Equipo> ganadores = new ArrayList<>();
        for (String p : partidos) {
            String[] eq = p.split(" vs ");
            // Selecciona aleatoriamente uno de los dos para avanzar a la siguiente etapa
            String ganador = Math.random() < 0.5 ? eq[0] : eq[1];
            ganadores.add(new Equipo(ganador));
        }
        return ganadores;
    }

    // Serialización: Guardar el objeto en torneo.ser
    public void guardarTorneo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_DATOS))) {
            oos.writeObject(torneo);
            System.out.println(">> ¡Torneo guardado exitosamente en " + ARCHIVO_DATOS + "!");
        } catch (IOException e) {
            System.err.println("Error de E/S al guardar el archivo: " + e.getMessage());
        }
    }

    // Deserialización: Cargar el objeto desde torneo.ser
    public void cargarTorneo() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_DATOS))) {
            torneo = (Torneo) ois.readObject();
            System.out.println(">> ¡Torneo cargado exitosamente desde " + ARCHIVO_DATOS + "!");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar el archivo serializado: " + e.getMessage());
        }
    }

    public Torneo getTorneo() {
        return torneo;
    }
}