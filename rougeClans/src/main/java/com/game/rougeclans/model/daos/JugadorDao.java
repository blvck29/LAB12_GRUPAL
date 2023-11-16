package com.game.rougeclans.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JugadorDao extends DaoBase{

    public void crearJugador(String nombre,int edad,String correo,String usuario,String contrasena){
        String sql = "insert into jugadores(nombre,edad,correo,usuario,contrasena) values (?,?,?,?,sha2(?,256))";

        try(Connection conn=this.getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
            pstmt.setString(1,nombre);
            pstmt.setInt(2,edad);
            pstmt.setString(3,correo);
            pstmt.setString(4,usuario);
            pstmt.setString(5,contrasena);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
