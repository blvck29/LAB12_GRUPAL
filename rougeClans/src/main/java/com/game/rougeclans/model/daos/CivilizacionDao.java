package com.game.rougeclans.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CivilizacionDao extends DaoBase{
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
