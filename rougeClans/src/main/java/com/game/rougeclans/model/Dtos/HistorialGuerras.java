package com.game.rougeclans.model.Dtos;

import com.game.rougeclans.model.beans.Civilizacion;
import com.game.rougeclans.model.beans.Guerra;
import com.game.rougeclans.model.daos.CivilizacionDao;

public class HistorialGuerras {

    private int idOponente;
    private Civilizacion civilizacionOponente;
    private String resultado;
    private int fecha;

    public int getIdOponente() {
        return idOponente;
    }

    public void setIdOponente(int idOponente) {
        this.idOponente = idOponente;
    }

    public int getFecha() {
        return fecha;
    }

    public void setFecha(int fecha) {
        this.fecha = fecha;
    }

    public Civilizacion getCivilizacionOponente() {
        return civilizacionOponente;
    }

    public void setCivilizacionOponente(Civilizacion civilizacionOponente) {
        this.civilizacionOponente = civilizacionOponente;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
