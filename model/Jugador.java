package model;

public class Jugador {
    private String nombre;
    private int energia;
    private int creditosAcumulados;

    public Jugador(String nombre, int energiaInicial) {
        this.nombre = nombre;
        this.energia = energiaInicial;
        this.creditosAcumulados = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public void modificarEnergia(int delta) {
        this.energia += delta;
    }

    public int getCreditosAcumulados() {
        return creditosAcumulados;
    }

    public void agregarCreditos(int creditos) {
        this.creditosAcumulados += creditos;
    }

    @Override
    public String toString() {
        return String.format("Jugador: %s | Energía Actual: %d | Créditos Ganados: %d", 
                nombre, energia, creditosAcumulados);
    }
}