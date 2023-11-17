package com.game.rougeclans.model.Dtos;
import com.game.rougeclans.model.beans.Persona;
public class Top5MoralBaja {
    private Persona persona;

    private int moralTotal;

    public Persona getPersona(){
        return persona;
    }
    public void setPersona(Persona persona){
        this.persona = persona;
    }
    public int getMoralTotal(){return moralTotal;}

    public void setMoralTotal(int moralTotal){
        this.moralTotal = moralTotal;
    }
    //Las horas del día se obtienen con el idCivilizacion
}
