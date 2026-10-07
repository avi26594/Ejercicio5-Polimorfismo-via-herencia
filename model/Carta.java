package model;

import java.util.Objects;

/**
 * Clase abstracta base que representa una carta genérica en el juego.
 * Demuestra encapsulación, override de métodos Object y base polimórfica.
 */
public abstract class Carta {
    private String id;
    private String nombre;
    private int costoEnergia;
    private String descripcion;

    public Carta(String id, String nombre, int costoEnergia, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.costoEnergia = costoEnergia;
        this.descripcion = descripcion;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCostoEnergia() {
        return costoEnergia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Método polimórfico abstracto. Cada tipo de carta define su propio comportamiento
     * al ser jugada en el tablero.
     */
    public abstract String jugarCarta(Jugador jugador);

    @Override
    public String toString() {
        return String.format("[%s] %s | Costo: %d Energía | Desc: %s", 
                id, nombre, costoEnergia, descripcion);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carta carta = (Carta) o;
        return Objects.equals(id, carta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}