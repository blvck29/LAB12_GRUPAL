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

    /*
    public ArrayList<HistorialGuerras> listarHistorial(int idCivilizacion){

        ArrayList<HistorialGuerras> lista = new ArrayList<>();
        ArrayList<HistorialGuerras> listaOrdenadaPorFecha = new ArrayList<>();
        CivilizacionDao civilizacionDao = new CivilizacionDao();
        GuerraDao guerraDao = new GuerraDao();

        ArrayList<Guerra> listaGuerrasDeCivilizacion = guerraDao.listarGuerras(idCivilizacion);

        for (Guerra guerra: listaGuerrasDeCivilizacion){

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

     */

    public ArrayList<HistorialGuerras> listarHistorial(int idCivilizacion){

        ArrayList<HistorialGuerras> lista = new ArrayList<>();
        ArrayList<HistorialGuerras> listaOrdenadaPorFecha = new ArrayList<>();
        CivilizacionDao civilizacionDao = new CivilizacionDao();
        GuerraDao guerraDao = new GuerraDao();

        String sql = "select * from guerra where id_civilizacion_atacante = ? or id_civilizacion_defensora = ?;";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,idCivilizacion); // atacamos
            pstmt.setInt(2,idCivilizacion); // fuimos atacados

            try(ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    HistorialGuerras historialGuerras = new HistorialGuerras();

                    if (guerraDao.obtenerRolGuerra(rs.getInt("id_guerra"),idCivilizacion)==1){ //Atacante
                        historialGuerras.setIdOponente(civilizacionDao.obtenerCivilizacion(rs.getInt("id_civilizacion_defensora")).getJugador().getIdJugador());
                        historialGuerras.setCivilizacionOponente(civilizacionDao.obtenerCivilizacion(rs.getInt("id_civilizacion_defensora")));
                        if(rs.getString("estado_guerra").equalsIgnoreCase("VA")){
                            historialGuerras.setResultado("Victoria");
                        }
                        if(rs.getString("estado_guerra").equalsIgnoreCase("VD")){
                            historialGuerras.setResultado("Derrota");
                        }
                        historialGuerras.setFecha(rs.getInt("dia_atacante"));
                    }

                    if (guerraDao.obtenerRolGuerra(rs.getInt("id_guerra"),idCivilizacion)==2){ //Defensor
                        historialGuerras.setIdOponente(civilizacionDao.obtenerCivilizacion(rs.getInt("id_civilizacion_atacante")).getJugador().getIdJugador());
                        historialGuerras.setCivilizacionOponente(civilizacionDao.obtenerCivilizacion(rs.getInt("id_civilizacion_atacante")));
                        if(rs.getString("estado_guerra").equalsIgnoreCase("VA")){
                            historialGuerras.setResultado("Derrota");
                        }
                        if(rs.getString("estado_guerra").equalsIgnoreCase("VD")){
                            historialGuerras.setResultado("Victoria");
                        }
                        historialGuerras.setFecha(rs.getInt("dia_defensor"));
                    }
                    lista.add(historialGuerras);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //lista.sort(Comparator.comparingInt(HistorialGuerras::getFecha));
        for(int i=lista.size();i>0;i--){
            listaOrdenadaPorFecha.add(lista.get(i-1));
        }
        return listaOrdenadaPorFecha;
    }

    public HistorialGuerras obtenerUltimaGuerra(int idCivilizacion){

        HistorialGuerras ultimaGuerra = new HistorialGuerras();
        CivilizacionDao civilizacionDao = new CivilizacionDao();
        GuerraDao guerraDao = new GuerraDao();
        ArrayList<Guerra> listaGuerrasDeCivilizacion = guerraDao.listarGuerras(idCivilizacion);

        if(listaGuerrasDeCivilizacion.isEmpty()){
            return ultimaGuerra;
        }
        Guerra uGuerra = listaGuerrasDeCivilizacion.get(listaGuerrasDeCivilizacion.size()-1);

        if (guerraDao.obtenerRolGuerra(uGuerra.getIdGuerra(),idCivilizacion)==1){ //Atacante
            ultimaGuerra.setIdOponente(uGuerra.getCivilizacionDefensora().getJugador().getIdJugador());
            ultimaGuerra.setCivilizacionOponente(uGuerra.getCivilizacionDefensora());
            if(uGuerra.getEstadoGuerra().equalsIgnoreCase("VA")){
                ultimaGuerra.setResultado("Victoria");
            }
            if(uGuerra.getEstadoGuerra().equalsIgnoreCase("VD")){
                ultimaGuerra.setResultado("Derrota");
            }
            ultimaGuerra.setFecha(uGuerra.getDiaAtacante());
        }
        if (guerraDao.obtenerRolGuerra(uGuerra.getIdGuerra(),idCivilizacion)==2){ //Defensor
            ultimaGuerra.setIdOponente(uGuerra.getCivilizacionAtacante().getJugador().getIdJugador());
            ultimaGuerra.setCivilizacionOponente(uGuerra.getCivilizacionAtacante());
            if(uGuerra.getEstadoGuerra().equalsIgnoreCase("VA")){
                ultimaGuerra.setResultado("Derrota");
            }
            if(uGuerra.getEstadoGuerra().equalsIgnoreCase("VD")){
                ultimaGuerra.setResultado("Victoria");
            }
            ultimaGuerra.setFecha(uGuerra.getDiaAtacante());
        }
        return ultimaGuerra;

    }


}
