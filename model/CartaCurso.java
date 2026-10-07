package model;

/**
 * Representa una materia académica que otorga créditos al jugador.
 */
public class CartaCurso extends Carta {
    private String departamento;
    private int creditosAportados;
    private int nivelDificultad;

    public CartaCurso(String id, String nombre, int costoEnergia, String descripcion,
                      String departamento, int creditosAportados, int nivelDificultad) {
        super(id, nombre, costoEnergia, descripcion);
        this.departamento = departamento;
        this.creditosAportados = creditosAportados;
        this.nivelDificultad = nivelDificultad;
    }

    public int getCreditosAportados() {
        return creditosAportados;
    }

    public int getNivelDificultad() {
        return nivelDificultad;
    }

    @Override
    public String jugarCarta(Jugador jugador) {
        jugador.agregarCreditos(creditosAportados);
        return String.format("¡Aprobaste el curso %s! Ganaste %d créditos académicos (Dificultad: %d).", 
                getNombre(), creditosAportados, nivelDificultad);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Depto: %s | Créditos: %d | Dificultad: %d/10", 
                departamento, creditosAportados, nivelDificultad);
    }
}