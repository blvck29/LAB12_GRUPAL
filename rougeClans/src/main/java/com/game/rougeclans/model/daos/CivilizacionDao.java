package com.game.rougeclans.model.daos;

import com.game.rougeclans.model.beans.Civilizacion;
import com.game.rougeclans.model.beans.Jugador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CivilizacionDao extends DaoBase{

    public Civilizacion obtenerCivilizacion(int idCivilizacion){

        Civilizacion civilizacion = new Civilizacion();
        JugadorDao jugadorDao = new JugadorDao(); //Para ahorrar código

        String sql = "select * from civilizaciones where id_civilizacion = ?;";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,idCivilizacion);

            try(ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    civilizacion.setIdCivilizacion(rs.getInt("id_civilizacion"));
                    civilizacion.setJugador(jugadorDao.obtenerJugador(rs.getInt("id_jugadores")));
                    civilizacion.setEstado(rs.getString("estado"));
                    civilizacion.setTimeElapsed(rs.getInt("time_elapsed"));
                    civilizacion.setDaysElapsed(rs.getInt("days_elapsed"));
                    civilizacion.setAlimentoTotal(rs.getInt("alimento_total"));
                    civilizacion.setPoblacionTotal(rs.getInt("poblacion_total"));
                }
                else {
                    civilizacion = null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return civilizacion;
    }


    public String estadoCivilizacion(int idCivilizacion){
        String estado = "";
        String sql = "select estado from civilizaciones where id_civilizacion = ? ";
        try(Connection conn=this.getConnection(); PreparedStatement pstmt=conn.prepareStatement(sql)){
            pstmt.setInt(1,idCivilizacion);
            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    estado = rs.getString(1);
                }
                return estado;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
