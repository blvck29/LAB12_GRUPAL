package com.game.rougeclans.model.daos;

import com.game.rougeclans.model.beans.Civilizacion;
import com.game.rougeclans.model.beans.Persona;

import java.sql.*;

public class PersonaDao extends DaoBase{

    public Persona obtenerPersona(int idPersona){

        Persona persona = new Persona();
        CivilizacionDao civilizacionDao = new CivilizacionDao(); //Para ahorrar código

        String sql = "select * from personas where id_personas = ?;";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,idPersona);

            try(ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                   persona.setIdPersona(rs.getInt("id_personas"));
                   persona.setCivilizacion(civilizacionDao.obtenerCivilizacion(rs.getInt("id_civilizacion")));
                   persona.setGenero(rs.getString("genero")); //Hacer flujo de transformar los caracteres 'M' o 'F' a 'masculino' y 'femenino' si es necesario
                   persona.setAlimentoDia(rs.getInt("alimento_dia"));
                   persona.setMoral(rs.getInt("moral"));
                   persona.setFuerza(rs.getInt("fuerza"));
                   persona.setProduce(rs.getInt("produce"));
                   persona.setExiliado(rs.getBoolean("exiliado"));
                   persona.setAlimentado(rs.getBoolean("alimentado"));
                   persona.setDaysAlive(rs.getInt("days_alive"));
                   persona.setMuerte(rs.getString("motivoMuerte"));
                   persona.setNombre(rs.getString("nombre"));
                   persona.setRol(rs.getString("profesion"));
                }
                else {
                    persona = null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return persona;
    }


    public void crearPersona(int idCivilizacion,String genero,int alimento_dia,int moral,int fuerza,int produce,String nombre,String profesion){
        String sql = "insert into personas(id_civilizacion,genero,alimento_dia,moral,fuerza,produce,nombre,profesion) values(?,?,?,?,?,?,?,?);";
        //con un case se puede corregir los casos :D
        try(Connection conn=this.getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
            pstmt.setInt(1,idCivilizacion);
            pstmt.setString(2,genero);
            pstmt.setInt(3,alimento_dia);
            pstmt.setInt(4,moral);
            pstmt.setInt(5,fuerza);
            pstmt.setInt(6,produce);
            pstmt.setString(7,nombre);
            pstmt.setString(8,profesion);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //Aquí falta poner un update en base a la profesion para actualizar las horas que pasa
        //Se utiliza otra query :D

    }


    public void exiliarPersona(int idPersona, int idCivilizacion){ //

        String sql = "update personas set exiliado = 1 where id_personas= ? and id_civilizacion =?";
        try(Connection conn=this.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1,idPersona);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
