package com.game.rougeclans.model.beans;

public class Guerra {

    private int idGuerra;
    private Civilizacion civilizacion;
    private Civilizacion oponente;
    private String resultado;
    private String rolGuerra;

    public int getIdGuerra() {
        return idGuerra;
    }

    public void setIdGuerra(int idGuerra) {
        this.idGuerra = idGuerra;
    }

    public Civilizacion getCivilizacion() {
        return civilizacion;
    }

    public void setCivilizacion(Civilizacion civilizacion) {
        this.civilizacion = civilizacion;
    }

    public Civilizacion getOponente() {
        return oponente;
    }

    public void setOponente(Civilizacion oponente) {
        this.oponente = oponente;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getRolGuerra() {
        return rolGuerra;
    }

    public void setRolGuerra(String rolGuerra) {
        this.rolGuerra = rolGuerra;
    }
}
