package com.game.rougeclans.model.beans;

public class Guerra {

    private int idGuerra;
    private Civilizacion civilizacion;
    private Civilizacion oponenteCivilizacion;
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

    public Civilizacion getOponenteCivilizacion() {
        return oponenteCivilizacion;
    }

    public void setOponenteCivilizacion(Civilizacion oponenteCivilizacion) {
        this.oponenteCivilizacion = oponenteCivilizacion;
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
