package com.game.rougeclans.model.beans;

public class Civilizacion {

    private int idCivilizacion;
    private Jugador jugador;
    private String nombre;
    private String estado;
    private int timeElapsed;
    private int daysElapsed;

    public int getIdCivilizacion() {
        return idCivilizacion;
    }

    public void setIdCivilizacion(int idCivilizacion) {
        this.idCivilizacion = idCivilizacion;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(int timeElapsed) {
        this.timeElapsed = timeElapsed;
    }

    public int getDaysElapsed() {
        return daysElapsed;
    }

    public void setDaysElapsed(int daysElapsed) {
        this.daysElapsed = daysElapsed;
    }


}
