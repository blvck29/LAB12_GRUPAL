package com.game.rougeclans.model.daos;

import com.game.rougeclans.model.beans.Jugador;
import com.game.rougeclans.model.beans.Rol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RolDao extends DaoBase {


    public Rol obtenerRol(String idRol){

        Rol rol = new Rol();
        String sql = "select * from roles where id_rol = ?;";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,idRol);

            try(ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    rol.setIdRol(rs.getString("id_rol"));
                    rol.setProfesion(rs.getString("profesion"));
                    rol.setDescripcion(rs.getString("descripcion"));
                }
                else {
                    rol = null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rol;
    }
}
