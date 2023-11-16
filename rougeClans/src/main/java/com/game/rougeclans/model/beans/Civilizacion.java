package com.game.rougeclans.model.beans;

public class Civilizacion {

    private int idCivilizacion;
    private Jugador jugador;
    private String estado;
    private int timeElapsed;
    private int daysElapsed;
    private int alimentoTotal;
    private int poblacionTotal;
    private int fuerzaTotal;

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

    public int getAlimentoTotal() {
        return alimentoTotal;
    }

    public void setAlimentoTotal(int alimentoTotal) {
        this.alimentoTotal = alimentoTotal;
    }

    public int getPoblacionTotal() {
        return poblacionTotal;
    }

    public void setPoblacionTotal(int poblacionTotal) {
        this.poblacionTotal = poblacionTotal;
    }

    public int getFuerzaTotal() {
        return fuerzaTotal;
    }

    public void setFuerzaTotal(int fuerzaTotal) {
        this.fuerzaTotal = fuerzaTotal;
    }
}
