package com.game.rougeclans.model.daos;

import java.sql.*;

public class PersonaDao extends DaoBase{

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
