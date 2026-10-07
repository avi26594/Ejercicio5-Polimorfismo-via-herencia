package view;

import controller.JuegoController;
import java.util.List;
import java.util.Scanner;
import model.Carta;

public class InterfazConsola {
    private JuegoController controller;
    private Scanner scanner;

    public InterfazConsola() {
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        System.out.print("=== BIENVENIDO A UVG CARD BATTLE ===\nIngresa tu nombre de estudiante: ");
        String nombre = scanner.nextLine();
        this.controller = new JuegoController(nombre);

        boolean salir = false;
        while (!salir) {
            mostrarEstadoYMenu();
            System.out.print("Selecciona una opción: ");
            try {
                int opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1 -> listarCartas();
                    case 2 -> buscarCarta();
                    case 3 -> ordenarMazo();
                    case 4 -> jugarCarta();
                    case 5 -> tomarCarta();
                    case 6 -> System.out.println(controller.pasarTurno());
                    case 7 -> {
                        salir = true;
                        System.out.println("¡Gracias por jugar UVG Card Battle!");
                    }
                    default -> System.out.println("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debes ingresar un número entero.");
            }
            System.out.println("\n------------------------------------------------");
        }
    }

    private void mostrarEstadoYMenu() {
        System.out.println("\nESTADO DEL JUGADOR: " + controller.getJugadorActual());
        System.out.println("=== MENÚ PRINCIPAL ===");
        System.out.println("1. Listar cartas en mano");
        System.out.println("2. Buscar carta (ID o Nombre)");
        System.out.println("3. Ordenar mano por costo de energía");
        System.out.println("4. Jugar una carta");
        System.out.println("5. Tomar/Robar una carta");
        System.out.println("6. Pasar turno");
        System.out.println("7. Salir");
    }

    private void listarCartas() {
        List<Carta> mano = controller.obtenerTodasLasCartasMano();
        System.out.println("\n--- CARTAS EN TU MANO ---");
        if (mano.isEmpty()) {
            System.out.println("Tu mano está vacía.");
        } else {
            mano.forEach(c -> System.out.println(c.toString()));
        }
    }

    private void buscarCarta() {
        System.out.println("\n¿Deseas buscar por (1) ID o (2) Nombre?");
        String tipo = scanner.nextLine();
        if (tipo.equals("1")) {
            System.out.print("Ingresa el ID exacto: ");
            String id = scanner.nextLine();
            Carta c = controller.buscarCartaPorId(id);
            if (c != null) System.out.println("Encontrada: " + c);
            else System.out.println("No se encontró ninguna carta con ese ID.");
        } else if (tipo.equals("2")) {
            System.out.print("Ingresa el Nombre a buscar: ");
            String nombre = scanner.nextLine();
            List<Carta> resultados = controller.buscarCartaPorNombre(nombre);
            if (resultados.isEmpty()) System.out.println("No hay coincidencias.");
            else resultados.forEach(c -> System.out.println("Encontrada: " + c));
        } else {
            System.out.println("Tipo de búsqueda no válido.");
        }
    }

    private void ordenarMazo() {
        controller.ordenarMazoPorEnergia();
        System.out.println("¡Mano ordenada ascendentemente por costo de energía!");
        listarCartas();
    }

    private void jugarCarta() {
        listarCartas();
        System.out.print("Ingresa el ID de la carta que deseas jugar: ");
        String id = scanner.nextLine();
        String resultado = controller.usarCarta(id);
        System.out.println(resultado);
    }

    private void tomarCarta() {
        String res = controller.tomarCarta();
        System.out.println(res);
    }
}