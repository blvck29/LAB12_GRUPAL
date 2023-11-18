package com.game.rougeclans.model.beans;

public class Soldado  extends Persona{

    private int alimentoDia;
    private int moral;
    private int fuerza;
    private int produce;

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
}
