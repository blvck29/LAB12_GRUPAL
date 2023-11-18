package com.game.rougeclans.model.Dtos;

import com.game.rougeclans.model.beans.Civilizacion;

import java.util.PrimitiveIterator;

public class Top10Jugadores{

    private Civilizacion civilizacion;
    private int poblacionTotal;
    private int moralTotal;
    private int guerrasGanadas;
    private double winrate;
    private int fuerzaTotal;
    private int maximoDiasPersona;
    private int produccionAlimento;

    public Civilizacion getCivilizacion() {
        return civilizacion;
    }

    public void setCivilizacion(Civilizacion civilizacion) {
        this.civilizacion = civilizacion;
    }

    public int getPoblacionTotal() {
        return poblacionTotal;
    }

    public void setPoblacionTotal(int poblacionTotal) {
        this.poblacionTotal = poblacionTotal;
    }

    public int getMoralTotal() {
        return moralTotal;
    }

    public void setMoralTotal(int moralTotal) {
        this.moralTotal = moralTotal;
    }

    public int getGuerrasGanadas() {
        return guerrasGanadas;
    }

    public void setGuerrasGanadas(int guerrasGanadas) {
        this.guerrasGanadas = guerrasGanadas;
    }

    public double getWinrate() {
        return winrate;
    }

    public void setWinrate(double winrate) {
        this.winrate = winrate;
    }

    public int getFuerzaTotal() {
        return fuerzaTotal;
    }

    public void setFuerzaTotal(int fuerzaTotal) {
        this.fuerzaTotal = fuerzaTotal;
    }

    public int getMaximoDiasPersona() {
        return maximoDiasPersona;
    }

    public void setMaximoDiasPersona(int maximoDiasPersona) {
        this.maximoDiasPersona = maximoDiasPersona;
    }

    public int getProduccionAlimento() {
        return produccionAlimento;
    }

    public void setProduccionAlimento(int produccionAlimento) {
        this.produccionAlimento = produccionAlimento;
    }

}
