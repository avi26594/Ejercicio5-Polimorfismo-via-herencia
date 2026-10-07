package model;

/**
 * Representa un evento del campus que altera dinámicamente la energía en el tablero.
 */
public class CartaEventoCampus extends Carta {
    private String tipoEvento;
    private int cambioEnergiaTablero;

    public CartaEventoCampus(String id, String nombre, int costoEnergia, String descripcion,
                             String tipoEvento, int cambioEnergiaTablero) {
        super(id, nombre, costoEnergia, descripcion);
        this.tipoEvento = tipoEvento;
        this.cambioEnergiaTablero = cambioEnergiaTablero;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public int getCambioEnergiaTablero() {
        return cambioEnergiaTablero;
    }

    @Override
    public String jugarCarta(Jugador jugador) {
        jugador.modificarEnergia(cambioEnergiaTablero);
        String efectoStr = cambioEnergiaTablero >= 0 ? "ganaste " + cambioEnergiaTablero : "perdiste " + Math.abs(cambioEnergiaTablero);
        return String.format("¡Evento de Campus '%s' activado! (%s): %s puntos de energía.", 
                getNombre(), tipoEvento, efectoStr);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Tipo Evento: %s | Impacto Energía: %+d", 
                tipoEvento, cambioEnergiaTablero);
    }
}