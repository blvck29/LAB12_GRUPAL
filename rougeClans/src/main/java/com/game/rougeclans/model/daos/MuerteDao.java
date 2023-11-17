package com.game.rougeclans.model.daos;

import com.game.rougeclans.model.beans.Rol;
import com.game.rougeclans.model.beans.Muerte;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MuerteDao extends DaoBase {

    public Muerte obtenerMuerte(String idMuerte){

        Muerte muerte = new Muerte();
        String sql = "select * from muerte where id_muerte = ?;";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,idMuerte);

            try(ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    muerte.setIdMuerte(rs.getString("id_muerte"));
                    muerte.setMotivo(rs.getString("motivo"));
                    muerte.setDay(rs.getInt("day"));
                }
                else {
                    muerte = null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return muerte;
    }
}
