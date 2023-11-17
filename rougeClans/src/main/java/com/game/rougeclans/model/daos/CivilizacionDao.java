package com.game.rougeclans.model.daos;

import com.game.rougeclans.model.beans.Civilizacion;
import com.game.rougeclans.model.beans.Jugador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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


    //creo que este método es redundante, pues con obtenerCivilización se obtienen todos los parámetros, entonces lo que ...
    //se haría (en este caso) para obtener el estado de una civilización es:
    //String estadoCivilización = obtenerCivilización(idCivilización).getEstado()
    //Además solo con ese método hasta se podría obtener parámetros del usuario (superbean)
    //Por ejemplo, si necesitamos el usuario del jugador relacionado con una civilización basta con:
    // obtenerCivilización(idCivilización).getJugador().getUsuario()

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

    //--------------------Métodos asociados a las horas y días transcurridos----------------------------------

    public void pasarLasHoras(int idCivilizacion){ //Hace que el tiempo de las civilizaciones vaya directamente a 24
        String sql = "update civilizaciones set time_elapsed = 24 where id_civilizacion = ?";
        try(Connection conn=this.getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
            pstmt.setInt(1,idCivilizacion);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void pasarDia(int idCivilizacion){
        String sqlver = "select time_elapsed from civilizaciones where id_civilizacion = ?";
        try(Connection conn=this.getConnection(); PreparedStatement pstmt=conn.prepareStatement(sqlver)){
            pstmt.setInt(1,idCivilizacion);
            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    if(rs.getInt(1)==24){
                        String sql = "update civilizaciones set days_elapsed = days_elapsed+1 where id_civilizacion = ?";
                        try(Connection conn1=this.getConnection(); PreparedStatement pstmt1= conn1.prepareStatement(sql)){

                            pstmt1.setInt(1,idCivilizacion);
                            pstmt1.executeUpdate();

                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void sumar8horas(int idCivilizacion){

        //primero actualizamos time_elapsed y days_elapsed para cerciorarnos que time_elapsed < 24
        actualizarTimeAndDaysElapsed(idCivilizacion);

        String sql = "";
        sql = "select time_elapsed from civilizaciones where id_civilizacion = ?";
        try(Connection conn=this.getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
            pstmt.setInt(1,idCivilizacion);
            try(ResultSet rs=pstmt.executeQuery()){
                if(rs.next()){
                    //Aquí se valida que time_elapsed sea menor o igual a 24 horas
                    if(rs.getInt(1)<25){
                        sql = "update civilizaciones set time_elapsed = time_elapsed + 8 where id_civilizacion = ? and time_elapsed<25";
                        try(Connection conn1=this.getConnection(); PreparedStatement pstmt1= conn1.prepareStatement(sql)){
                            pstmt1.setInt(1,idCivilizacion);
                            pstmt1.executeUpdate();

                            //ahora actualizamos time_elapsed y days_elapsed si time_elapsed > 24 después de sumar las horas
                            actualizarTimeAndDaysElapsed(idCivilizacion);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void sumar2horas(int idCivilizacion){

        //primero actualizamos time_elapsed y days_elapsed para cerciorarnos que time_elapsed < 24
        actualizarTimeAndDaysElapsed(idCivilizacion);

        String sql = "";
        sql = "select time_elapsed from civilizaciones where id_civilizacion = ?";
        try(Connection conn=this.getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
            pstmt.setInt(1,idCivilizacion);
            try(ResultSet rs=pstmt.executeQuery()){
                if(rs.next()){
                    //Aquí se valida que time_elapsed sea menor a 24 horas
                    if(rs.getInt(1)<25){
                        sql = "update civilizaciones set time_elapsed = time_elapsed + 2 where id_civilizacion = ? and time_elapsed<25";
                        try(Connection conn1=this.getConnection(); PreparedStatement pstmt1= conn1.prepareStatement(sql)){
                            pstmt1.setInt(1,idCivilizacion);
                            pstmt1.executeUpdate();

                            //ahora actualizamos time_elapsed y days_elapsed si time_elapsed > 24 después de sumar las horas
                            actualizarTimeAndDaysElapsed(idCivilizacion);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizarTimeAndDaysElapsed(int idCivilizacion){ //usar para actualizar time_elapsed cuando time_elapsed > 24

        Civilizacion civilizacion = obtenerCivilizacion(idCivilizacion); // Ahorrar código

        if(civilizacion.getTimeElapsed()>=24){
            String sql = "update civilizaciones set time_elapsed = time_elapsed - 24, days_elapsed = days_elapsed + 1 where id_civilizacion = ? and time_elapsed<25";;
            try(Connection conn=getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
                pstmt.setInt(1,idCivilizacion);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }


    //----------------------------------------------------------------------------------------------------------------------------------------------

    //Lista que servirá para la parte de declaración de guerra
    public ArrayList<Civilizacion> listarCivilizaciones(int idCivilizacionNoMostrada){ //para el parámetro de idCivilización no mostrada, colocar '0' si es que se quiere listar TODAS las civilizaciones

        ArrayList<Civilizacion> lista = new ArrayList<>();
        JugadorDao jugadorDao = new JugadorDao(); //Para ahorrar código
        String sql = "select * from civilizaciones where id_civilizacion != ?;";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,idCivilizacionNoMostrada);

            try(ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Civilizacion civilizacion = new Civilizacion();
                    civilizacion.setIdCivilizacion(rs.getInt("id_civilizacion"));
                    civilizacion.setJugador(jugadorDao.obtenerJugador(rs.getInt("id_jugadores")));
                    civilizacion.setEstado(rs.getString("estado"));
                    civilizacion.setTimeElapsed(rs.getInt("time_elapsed"));
                    civilizacion.setDaysElapsed(rs.getInt("days_elapsed"));
                    civilizacion.setAlimentoTotal(rs.getInt("alimento_total"));
                    civilizacion.setPoblacionTotal(rs.getInt("poblacion_total"));
                    lista.add(civilizacion);
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }


}
