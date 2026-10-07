package model;

/**
 * Representa una carta de catedrático que pertenece a un departamento.
 */
public class CartaCatedratico extends Carta {
    private String departamento;
    private int llamadasAtencion;
    private int tiempoAtencion;

    public CartaCatedratico(String id, String nombre, int costoEnergia, String descripcion,
                             String departamento, int llamadasAtencion, int tiempoAtencion) {
        super(id, nombre, costoEnergia, descripcion);
        this.departamento = departamento;
        this.llamadasAtencion = llamadasAtencion;
        this.tiempoAtencion = tiempoAtencion;
    }

    public String getDepartamento() {
        return departamento;
    }

    public int getLlamadasAtencion() {
        return llamadasAtencion;
    }

    public int getTiempoAtencion() {
        return tiempoAtencion;
    }

    @Override
    public String jugarCarta(Jugador jugador) {
        // Efecto: Resta energía debido al tiempo de atención acumulado y llamadas de atención
        int penalizacion = tiempoAtencion + (llamadasAtencion * 2);
        jugador.modificarEnergia(-penalizacion);
        return String.format("¡Catedrático %s invocado! Redujo tu energía en %d puntos por atención intensiva.", 
                getNombre(), penalizacion);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Depto: %s | Llamadas: %d | Tiempo: %d min", 
                departamento, llamadasAtencion, tiempoAtencion);
    }
}