package com.game.rougeclans.model.daos;

import com.game.rougeclans.model.Dtos.Top10Jugadores;
import com.game.rougeclans.model.beans.Guerra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Top10JugadoresDao extends DaoBase{

    public ArrayList<Top10Jugadores> listar(){

        ArrayList<Top10Jugadores> lista = new ArrayList<>();
        CivilizacionDao civilizacionDao = new CivilizacionDao();
        GuerraDao guerraDao = new GuerraDao();


        String sql = "select * from civilizaciones;";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            try(ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Top10Jugadores top10Jugadores = new Top10Jugadores();
                    top10Jugadores.setCivilizacion(civilizacionDao.obtenerCivilizacion(rs.getInt("id_civilizacion")));
                    top10Jugadores.setPoblacionTotal(civilizacionDao.obtenerPoblacionTotal(rs.getInt("id_civilizacion")));
                    top10Jugadores.setMoralTotal(civilizacionDao.obtenerMoralTotalCivilizacion(rs.getInt("id_civilizacion")));
                    top10Jugadores.setGuerrasGanadas(guerraDao.calcularGuerrasGanadas(top10Jugadores.getCivilizacion()));
                    top10Jugadores.setWinrate(guerraDao.obtenerWinRate(top10Jugadores.getCivilizacion()));
                    top10Jugadores.setFuerzaTotal(civilizacionDao.fuerzaTotalDefensor(rs.getInt("id_civilizacion")));
                    top10Jugadores.setMaximoDiasPersona(civilizacionDao.obtenerAncianoDelPueblo(top10Jugadores.getCivilizacion()));
                    top10Jugadores.setProduccionAlimento(civilizacionDao.obtenerAlimentoTotal(rs.getInt("id_civilizacion")));
                    lista.add(top10Jugadores);
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }


    public ArrayList<Top10Jugadores> listarTop10(String orderBy){

        CivilizacionDao civilizacionDao = new CivilizacionDao();
        GuerraDao guerraDao = new GuerraDao();

        ArrayList<Top10Jugadores> lista = listar();

        ArrayList<Top10Jugadores> listaTop10 = new ArrayList<>();

        switch (orderBy){
            case "dias_jugados":
                lista.sort(Comparator.comparingInt((Top10Jugadores t) -> t.getCivilizacion().getDaysElapsed()));
                break;
            case "poblacion_total":
                lista.sort(Comparator.comparingInt(Top10Jugadores::getPoblacionTotal));
                break;
            case "moral_total":
                lista.sort(Comparator.comparingInt(Top10Jugadores::getMoralTotal));
                break;
            case "guerras_ganadas":
                lista.sort(Comparator.comparingInt(Top10Jugadores::getGuerrasGanadas));
                break;
            case "winrate":
                lista.sort(Comparator.comparingDouble(Top10Jugadores::getWinrate));
                break;
            case "fuerza_total":
                lista.sort(Comparator.comparingInt(Top10Jugadores::getFuerzaTotal));
                break;
            case "anciano_del_pueblo":
                lista.sort(Comparator.comparingInt(Top10Jugadores::getMaximoDiasPersona));
                break;
            case "produccion_de_alimento":
                lista.sort(Comparator.comparingInt(Top10Jugadores::getProduccionAlimento));
                break;

        }
        for(int i=lista.size();i>0;i--){
            if(lista.size() - i <= 10){
                listaTop10.add(lista.get(i-1));
            }
        }
        return listaTop10;
    }

}
