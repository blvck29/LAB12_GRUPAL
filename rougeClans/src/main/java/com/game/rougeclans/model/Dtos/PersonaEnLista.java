package com.game.rougeclans.model.Dtos;

import com.game.rougeclans.model.beans.Civilizacion;

public class PersonaEnLista {

    private int idPersona;
    private Civilizacion civilizacion;
    private String genero;
    private int alimentoDia;
    private int moral;
    private int fuerza;
    private int produce;
    private boolean alimentado;
    private int daysAlive;
    private String nombre;
    private String profesion;

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

    public void setFuerza(int fuerzo) {
        this.fuerza = fuerzo;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }
}
