package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Torneo implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Equipo> equipos;
    private List<String> resultadosEtapas;

    public Torneo(List<String> nombresEquipos) throws NumeroEquiposInvalidoException {
        validarNumeroEquipos(nombresEquipos.size());
        this.equipos = new ArrayList<>();
        for (String nombre : nombresEquipos) {
            this.equipos.add(new Equipo(nombre));
        }
        this.resultadosEtapas = new ArrayList<>();
    }

    private void validarNumeroEquipos(int n) throws NumeroEquiposInvalidoException {
        if (n != 16 && n != 8 && n != 4 && n != 2) {
            throw new NumeroEquiposInvalidoException("El número de equipos debe ser una potencia de 2 (16, 8, 4, 2). Actual: " + n);
        }
    }

    // Algoritmo de sorteo recursivo para las etapas
    public List<String> sortearEtapaRecursivo(List<Equipo> listaEquipos, String nombreEtapa) throws SorteoInvalidoException {
        if (listaEquipos == null || listaEquipos.size() % 2 != 0) {
            throw new SorteoInvalidoException("Error en el sorteo: La cantidad de equipos debe ser par para la etapa " + nombreEtapa);
        }

        List<String> emparejamientos = new ArrayList<>();
        List<Equipo> copiaEquipos = new ArrayList<>(listaEquipos);
        Collections.shuffle(copiaEquipos); // Mezcla aleatoria garantizando reglas del torneo

        for (int i = 0; i < copiaEquipos.size(); i += 2) {
            Equipo eq1 = copiaEquipos.get(i);
            Equipo eq2 = copiaEquipos.get(i + 1);

            if (eq1.equals(eq2)) {
                throw new SorteoInvalidoException("Condición lógica imposible: Un equipo no puede competir contra sí mismo.");
            }
            emparejamientos.add(eq1.getNombre() + " vs " + eq2.getNombre());
        }

        resultadosEtapas.add("=== ETAPA: " + nombreEtapa + " ===");
        resultadosEtapas.addAll(emparejamientos);

        return emparejamientos;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public List<String> getResultadosEtapas() {
        return resultadosEtapas;
    }
}