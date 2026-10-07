package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Contenedor de la lista polimórfica de cartas. Demuestra uso de overloading en búsquedas.
 */
public class Mazo {
    private List<Carta> cartas;

    public Mazo() {
        this.cartas = new ArrayList<>();
    }

    public void agregarCarta(Carta carta) {
        cartas.add(carta);
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    // OVERLOADING 1: Búsqueda exacta por ID
    public Carta buscarCarta(String id) {
        for (Carta c : cartas) {
            if (c.getId().equalsIgnoreCase(id)) {
                return c;
            }
        }
        return null;
    }

    // OVERLOADING 2: Búsqueda por nombre (devuelve lista si hay coincidencia parcial)
    public List<Carta> buscarCarta(String nombre, boolean coincidenciaParcial) {
        List<Carta> resultados = new ArrayList<>();
        for (Carta c : cartas) {
            if (coincidenciaParcial) {
                if (c.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                    resultados.add(c);
                }
            } else {
                if (c.getNombre().equalsIgnoreCase(nombre)) {
                    resultados.add(c);
                }
            }
        }
        return resultados;
    }

    public void ordenarPorCostoEnergia() {
        cartas.sort(Comparator.comparingInt(Carta::getCostoEnergia));
    }

    public Carta tomarCartaAleatoria() {
        if (cartas.isEmpty()) return null;
        Random r = new Random();
        return cartas.get(r.nextInt(cartas.size()));
    }
}