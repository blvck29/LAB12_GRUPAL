package com.game.rougeclans.model.beans;

public class Persona {

    private int idPersona;
    private Civilizacion civilizacion;
    private String nombre;
    private String genero;
    private int alimentoDia;
    private int moral;
    private int fuerza;
    private int produce;
    private boolean alimentado;
    private int daysAlive;

    private String profesion;

    private boolean muerto;
    private String motivoMuerte;


    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public Civilizacion getCivilizacion() {
        return civilizacion;
    }

    public void setCivilizacion(Civilizacion civilizacion) {
        this.civilizacion = civilizacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAlimentoDia() {
        return alimentoDia;
    }

    public void setAlimentoDia(int alimentoDia) {
        this.alimentoDia = alimentoDia;
    }

    public int getMoral() {
        return moral;
    }

    public void setMoral(int moral) {
        this.moral = moral;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public int getProduce() {
        return produce;
    }

    public void setProduce(int produce) {
        this.produce = produce;
    }

    public boolean isAlimentado() {
        return alimentado;
    }

    public void setAlimentado(boolean alimentado) {
        this.alimentado = alimentado;
    }

    public int getDaysAlive() {
        return daysAlive;
    }

    public void setDaysAlive(int daysAlive) {
        this.daysAlive = daysAlive;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public boolean isMuerto() {
        return muerto;
    }

    public void setMuerto(boolean muerto) {
        this.muerto = muerto;
    }

    public String getMotivoMuerte() {
        return motivoMuerte;
    }

    public void setMotivoMuerte(String motivoMuerte) {
        this.motivoMuerte = motivoMuerte;
    }
}
