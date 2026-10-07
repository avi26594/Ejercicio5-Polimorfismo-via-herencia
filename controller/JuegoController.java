package controller;

import java.util.List;
import model.*;

public class JuegoController {
    private Mazo mazoCatalogo;
    private Mazo manoJugador;
    private Jugador jugadorActual;

    public JuegoController(String nombreJugador) {
        this.mazoCatalogo = new Mazo();
        this.manoJugador = new Mazo();
        this.jugadorActual = new Jugador(nombreJugador, 15); // Inicia con 15 de energía
        cargarCatalogoInicial();
    }

    /**
     * Carga inicial obligatoria de al menos 10 cartas aleatorias/predefinidas.
     */
    private void cargarCatalogoInicial() {
        mazoCatalogo.agregarCarta(new CartaCatedratico("CAT01", "Dr. Morales", 3, "Experto en Estructuras de Datos", "Computación", 2, 4));
        mazoCatalogo.agregarCarta(new CartaCatedratico("CAT02", "Inga. Ruiz", 2, "Catedrática exigente de Algoritmos", "Computación", 1, 3));
        mazoCatalogo.agregarCarta(new CartaCatedratico("CAT03", "Lic. Pérez", 4, "Explicaciones largas y detalladas", "Matemáticas", 3, 5));
        
        mazoCatalogo.agregarCarta(new CartaCurso("CUR01", "POO", 4, "Programación Orientada a Objetos", "Computación", 5, 8));
        mazoCatalogo.agregarCarta(new CartaCurso("CUR02", "Cálculo 1", 5, "Límites y derivadas avanzadas", "Matemáticas", 6, 9));
        mazoCatalogo.agregarCarta(new CartaCurso("CUR03", "Física 1", 3, "Leyes de Newton y cinemática", "Física", 4, 7));
        mazoCatalogo.agregarCarta(new CartaCurso("CUR04", "Química General", 2, "Reacciones e hidrometría básica", "Química", 3, 5));
        
        mazoCatalogo.agregarCarta(new CartaEventoCampus("EVT01", "Semana de Parciales", 1, "Estrés masivo en la biblioteca", "Crisis", -4));
        mazoCatalogo.agregarCarta(new CartaEventoCampus("EVT02", "Feria de Clubes", 0, "Actividades recreativas en la plaza", "Festivo", 5));
        mazoCatalogo.agregarCarta(new CartaEventoCampus("EVT03", "Convivio de Bienvenida", 2, "Pizzas y bebidas gratis", "Social", 3));

        // Inicializar la mano del jugador con 5 cartas aleatorias del catálogo
        for (int i = 0; i < 5; i++) {
            manoJugador.agregarCarta(mazoCatalogo.tomarCartaAleatoria());
        }
    }

    public List<Carta> obtenerTodasLasCartasMano() {
        return manoJugador.getCartas();
    }

    public Carta buscarCartaPorId(String id) {
        return manoJugador.buscarCarta(id);
    }

    public List<Carta> buscarCartaPorNombre(String nombre) {
        return manoJugador.buscarCarta(nombre, true);
    }

    public void ordenarMazoPorEnergia() {
        manoJugador.ordenarPorCostoEnergia();
    }

    public String usarCarta(String idCarta) {
        Carta carta = manoJugador.buscarCarta(idCarta);
        if (carta == null) {
            return "Error: No posees esa carta en tu mano.";
        }
        if (jugadorActual.getEnergia() < carta.getCostoEnergia()) {
            return String.format("Energía insuficiente. Necesitas %d y tienes %d.", 
                    carta.getCostoEnergia(), jugadorActual.getEnergia());
        }

        // Restar el costo de jugar la carta
        jugadorActual.modificarEnergia(-carta.getCostoEnergia());
        
        // Ejecución Polimórfica del efecto de la carta
        String resultadoEfecto = carta.jugarCarta(jugadorActual);
        
        // Retirar la carta jugada de la mano
        manoJugador.getCartas().remove(carta);

        return "Carta jugada exitosamente. " + resultadoEfecto;
    }

    public String tomarCarta() {
        Carta nueva = mazoCatalogo.tomarCartaAleatoria();
        manoJugador.agregarCarta(nueva);
        return "Has robado la carta: " + nueva.getNombre();
    }

    public String pasarTurno() {
        jugadorActual.modificarEnergia(3); // Recupera 3 puntos de energía al pasar
        return "Has pasado el turno. Recuperaste +3 de energía.";
    }

    public Jugador getJugadorActual() {
        return jugadorActual;
    }
}