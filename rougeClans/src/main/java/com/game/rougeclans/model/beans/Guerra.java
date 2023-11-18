package com.game.rougeclans.model.beans;

public class Guerra {

    private int idGuerra;
    private Civilizacion civilizacionAtacante;
    private Civilizacion civilizacionDefensora;
    private String estadoGuerra;
    private int diaAtacante;
    private int diaDefensor;

    public int getIdGuerra() {
        return idGuerra;
    }

    public void setIdGuerra(int idGuerra) {
        this.idGuerra = idGuerra;
    }

    public Civilizacion getCivilizacionAtacante() {
        return civilizacionAtacante;
    }

    public void setCivilizacionAtacante(Civilizacion civilizacionAtacante) {
        this.civilizacionAtacante = civilizacionAtacante;
    }

    public Civilizacion getCivilizacionDefensora() {
        return civilizacionDefensora;
    }

    public void setCivilizacionDefensora(Civilizacion civilizacionDefensora) {
        this.civilizacionDefensora = civilizacionDefensora;
    }

    public String getEstadoGuerra() {
        return estadoGuerra;
    }

    public void setEstadoGuerra(String estadoGuerra) {
        this.estadoGuerra = estadoGuerra;
    }

    public int getDiaAtacante() {
        return diaAtacante;
    }

    public void setDiaAtacante(int diaAtacante) {
        this.diaAtacante = diaAtacante;
    }

    public int getDiaDefensor() {
        return diaDefensor;
    }

    public void setDiaDefensor(int diaDefensor) {
        this.diaDefensor = diaDefensor;
    }
}
