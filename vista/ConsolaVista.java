package vista;

import controlador.TorneoControlador;
import modelo.NumeroEquiposInvalidoException;
import modelo.SorteoInvalidoException;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ConsolaVista {
    public static void main(String[] args) {
        TorneoControlador controlador = new TorneoControlador();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== LIGA PROFESIONAL DE FUTBOL - SORTEO MVC ===");
            System.out.println("1. Crear nuevo torneo (16 equipos)");
            System.out.println("2. Ejecutar sorteo recursivo de etapas");
            System.out.println("3. Guardar torneo (Serialización)");
            System.out.println("4. Cargar torneo (Deserialización)");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            try {
                switch (opcion) {
                    case 1:
                        List<String> equipos16 = Arrays.asList(
                            "Liga de Quito", "Independiente del Valle", "Barcelona SC", "Emelec",
                            "Universidad Católica", "El Nacional", "Aucas", "Delfín SC",
                            "Macará", "Deportivo Cuenca", "Mushuc Runa", "Técnico Universitario",
                            "Orense SC", "Imbabura SC", "Cumbayá FC", "Libertad FC"
                        );
                        controlador.inicializarTorneo(equipos16);
                        System.out.println("¡Torneo inicializado correctamente con 16 equipos!");
                        break;
                    case 2:
                        controlador.ejecutarSorteoCompleto();
                        System.out.println("\n--- RESULTADOS GENERALES DEL TORNEO ---");
                        for (String resultado : controlador.getTorneo().getResultadosEtapas()) {
                            System.out.println(resultado);
                        }
                        break;
                    case 3:
                        controlador.guardarTorneo();
                        break;
                    case 4:
                        controlador.cargarTorneo();
                        if (controlador.getTorneo() != null) {
                            System.out.println("Estado actual del torneo restaurado con éxito.");
                        }
                        break;
                    case 5:
                        System.out.println("Saliendo de la aplicación...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } catch (NumeroEquiposInvalidoException | SorteoInvalidoException e) {
                System.err.println("[EXCEPCIÓN CONTROLADA]: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("[ERROR INESPERADO]: " + e.getMessage());
            }

        } while (opcion != 5);

        scanner.close();
    }
}