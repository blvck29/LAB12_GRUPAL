package com.game.rougeclans.model.daos;

import com.game.rougeclans.model.beans.Civilizacion;
import com.game.rougeclans.model.beans.Persona;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;


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


    public void crearPersona(int idCivilizacion,String genero,String nombre,String profesion){

        String sql = "";
        //los parametros varían segun la profesion

        int alimentoDia = 0;
        int moral = 0;
        int fuerza = 0;
        int produce = 0;
        //crear funciones random

        switch (profesion){
            case "Granjero":
                alimentoDia = randomNum(10,30);
                moral = randomNum(10,40);
                produce = randomNum(100,200);
                sql = "insert into personas(id_civilizacion,genero,alimento_dia,moral,produce,nombre,profesion) values(?,?,?,?,?,?,?)";
                try(Connection conn=this.getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
                    pstmt.setInt(1,idCivilizacion);
                    pstmt.setString(2,genero);
                    pstmt.setInt(3,alimentoDia);
                    pstmt.setInt(4,moral);
                    pstmt.setInt(5,produce);//produce alimento
                    pstmt.setString(6,nombre);
                    pstmt.setString(7,"Granjero");
                    pstmt.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                //actualizar + 8 horas
                sumar8horas(idCivilizacion);

                break;
            case "Constructor":
                alimentoDia = randomNum(50,70);
                moral = randomNum(10,40);
                fuerza = randomNum(2,20);
                produce = randomNum(10,20);
                sql = "insert into personas(id_civilizacion,genero,alimento_dia,moral,fuerza,produce,nombre,profesion) values(?,?,?,?,?,?,?,?)";
                try(Connection conn=this.getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
                    pstmt.setInt(1,idCivilizacion);
                    pstmt.setString(2,genero);
                    pstmt.setInt(3,alimentoDia);
                    pstmt.setInt(4,moral);
                    pstmt.setInt(5,fuerza);
                    pstmt.setInt(6,produce);//moral
                    pstmt.setString(7,nombre);
                    pstmt.setString(8,"Constructor");
                    pstmt.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                //actualizar + 8 horas
                sumar8horas(idCivilizacion);
                break;
            case "Soldado":

                alimentoDia = randomNum(70,100);
                moral = randomNum(30,50);
                fuerza = randomNum(15,50);
                produce = randomNum(0,20);

                sql = "insert into personas(id_civilizacion,genero,alimento_dia,moral,fuerza,produce,nombre,profesion) values(?,?,?,?,?,?,?,?)";

                try(Connection conn=this.getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
                    pstmt.setInt(1,idCivilizacion);
                    pstmt.setString(2,genero);
                    pstmt.setInt(3,alimentoDia);
                    pstmt.setInt(4,moral);
                    pstmt.setInt(5,fuerza);
                    pstmt.setInt(6,produce);//moral
                    pstmt.setString(7,nombre);
                    pstmt.setString(8,"Soldado");
                    pstmt.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                //actualizar + 8 horas
                sumar8horas(idCivilizacion);
                break;
            case "Ninguna":
                alimentoDia = randomNum(30,50);
                moral = randomNum(20,50);
                sql = "insert into personas(id_civilizacion,genero,alimento_dia,moral,nombre,profesion) values(?,?,?,?,?,?)";

                try(Connection conn=this.getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
                    pstmt.setInt(1,idCivilizacion);
                    pstmt.setString(2,genero);
                    pstmt.setInt(3,alimentoDia);
                    pstmt.setInt(4,moral);
                    pstmt.setString(5,nombre);
                    pstmt.setString(6,"Ninguna");
                    pstmt.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                //actualizar + 2 horas
                sumar2horas(idCivilizacion);
                break;

        }

    }

    public void sumar8horas(int idCivilizacion){
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
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }else{
                    //no se que poner si time_elapsed es mayor a 24

                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void sumar2horas(int idCivilizacion){
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
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }else{
                    //no se que poner si time_elapsed es mayor a 24

                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int randomNum(int min, int max){
        Random rd = new Random();
        int randNum = rd.nextInt(max - min + 1) + min;
        return randNum;
    }

    public void exiliarPersona(int idPersona, int idCivilizacion){ //

        //Se obtiene el moral de la persona a exiliar
        int moralExiliado = obtenerMoralExiliado(idPersona, idCivilizacion);

        String sql = "";
        //Aquí se exilia a la persona (elimina)
        sql = "DELETE FROM personas WHERE id_persona = ? and id_civilizacion = ?";
        try(Connection conn=this.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1,idPersona);
            pstmt.setInt(2,idCivilizacion);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //Un update de moral a todas las personas que son de la misma civilizacion que el exiliado

        sql = "update personas set moral = moral - ? where id_civilizacion = ?";

        //Se obtiene un rango aleatorio entre 0 y moralExiliado/2
        int reducMoral = randomNum(0,moralExiliado/2);
        try(Connection conn=this.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1,reducMoral);
            pstmt.setInt(2,idCivilizacion);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int obtenerMoralExiliado(int idPersona, int idCivilizacion){

        String sql = "select moral from personas where id_persona = ? and id_civilizacion = ?";
        try(Connection conn=this.getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
            pstmt.setInt(1,idPersona);
            pstmt.setInt(2,idCivilizacion);
            try(ResultSet rs=pstmt.executeQuery()){
                if(rs.next()){
                    return rs.getInt(1);
                }else{
                    return 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
