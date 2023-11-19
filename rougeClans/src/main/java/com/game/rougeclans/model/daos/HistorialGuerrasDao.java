package com.game.rougeclans.model.daos;

import com.game.rougeclans.model.Dtos.HistorialGuerras;
import com.game.rougeclans.model.Dtos.Top10Jugadores;
import com.game.rougeclans.model.beans.Guerra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

public class HistorialGuerrasDao extends DaoBase{

    public ArrayList<HistorialGuerras> listarHistorial(int idCivilizacion){

        ArrayList<HistorialGuerras> lista = new ArrayList<>();
        ArrayList<HistorialGuerras> listaOrdenadaPorFecha = new ArrayList<>();
        CivilizacionDao civilizacionDao = new CivilizacionDao();
        GuerraDao guerraDao = new GuerraDao();

        ArrayList<Guerra> listaGuerrasDeCivlizacion = guerraDao.listarGuerras(idCivilizacion);

        for (Guerra guerra: listaGuerrasDeCivlizacion){

            HistorialGuerras historialGuerras = new HistorialGuerras();
            if (guerraDao.obtenerRolGuerra(guerra.getIdGuerra(),idCivilizacion)==1){ //Atacante
                historialGuerras.setIdOponente(guerra.getCivilizacionDefensora().getJugador().getIdJugador());
                historialGuerras.setCivilizacionOponente(guerra.getCivilizacionDefensora());
                if(guerra.getEstadoGuerra().equalsIgnoreCase("VA")){
                    historialGuerras.setResultado("Victoria");
                }
                if(guerra.getEstadoGuerra().equalsIgnoreCase("VD")){
                    historialGuerras.setResultado("Derrota");
                }
                historialGuerras.setFecha(guerra.getDiaAtacante());
            }

            if (guerraDao.obtenerRolGuerra(guerra.getIdGuerra(),idCivilizacion)==2){ //Defensor
                historialGuerras.setIdOponente(guerra.getCivilizacionAtacante().getJugador().getIdJugador());
                historialGuerras.setCivilizacionOponente(guerra.getCivilizacionAtacante());
                if(guerra.getEstadoGuerra().equalsIgnoreCase("VA")){
                    historialGuerras.setResultado("Derrota");
                }
                if(guerra.getEstadoGuerra().equalsIgnoreCase("VD")){
                    historialGuerras.setResultado("Victoria");
                }
                historialGuerras.setFecha(guerra.getDiaAtacante());
            }
            lista.add(historialGuerras);
        }
        lista.sort(Comparator.comparingInt(HistorialGuerras::getFecha));
        for(int i=lista.size();i>0;i--){
            listaOrdenadaPorFecha.add(lista.get(i-1));
        }
        return listaOrdenadaPorFecha;
    }
}
