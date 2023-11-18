package com.game.rougeclans.model.daos;

import com.game.rougeclans.model.SHA256;
import com.game.rougeclans.model.beans.Jugador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JugadorDao extends DaoBase{

    public Jugador obtenerJugador(int idJugador){

        Jugador jugador = new Jugador();
        String sql = "select * from jugadores where idJugadores = ?;";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,idJugador);

            try(ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    jugador.setIdJugador(rs.getInt("idJugadores"));
                    jugador.setNombre(rs.getString("nombre"));
                    jugador.setEdad(rs.getInt("edad"));
                    jugador.setCorreo(rs.getString("correo"));
                    jugador.setUsuario(rs.getString("usuario"));
                    jugador.setContrasena(rs.getString("contrasena"));
                    jugador.setListaNegra(rs.getBoolean("ban"));
                }
                else {
                    jugador = null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return jugador;
    }

    public void crearJugador(String nombre,String edad,String correo,String usuario,String contrasena){

        contrasena = SHA256.cipherPassword(contrasena);

        String sql = "insert into jugadores(nombre,edad,correo,usuario,contrasena) values (?,?,?,?,?)";

        try(Connection conn=this.getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
            pstmt.setString(1,nombre);
            pstmt.setInt(2, Integer.parseInt(edad));
            pstmt.setString(3,correo);
            pstmt.setString(4,usuario);
            pstmt.setString(5,contrasena);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void banearJugador(int idUser){ //Aquí lo mete a la lista negra
        String sql = "update jugadores set ban = 1 where idjugadores = ? ";
        try(Connection conn=this.getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
            pstmt.setInt(1,idUser);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean login(String usuario, String contrasena){

        boolean valido = false;
        contrasena = SHA256.cipherPassword(contrasena);

        String sql = "SELECT usuario, contrasena FROM jugadores WHERE usuario = ? AND contrasena = ? AND ban != 1;";


        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1, usuario);
            pstmt.setString(2, contrasena);

            try(ResultSet rs = pstmt.executeQuery()){

                while(rs.next()){
                    String usuarioDB = rs.getString(1);
                    String contrasenaDB = rs.getString(2);

                    if (usuarioDB == null || contrasenaDB == null){
                        valido = false;
                    } else if (usuarioDB.equals(usuario) && contrasenaDB.equals(contrasena)){
                        valido = true;
                    }
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return valido;
    }


}


