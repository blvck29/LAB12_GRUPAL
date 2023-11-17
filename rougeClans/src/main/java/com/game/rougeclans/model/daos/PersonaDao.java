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
        MuerteDao muerteDao = new MuerteDao();
        RolDao rolDao = new RolDao();

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
                    persona.setAlimentado(rs.getBoolean("alimentado"));
                    persona.setDaysAlive(rs.getInt("days_alive"));
                    persona.setMuerte(muerteDao.obtenerMuerte(rs.getString("id_muerte")));
                    persona.setNombre(rs.getString("nombre"));
                    persona.setRol(rolDao.obtenerRol(rs.getString("id_rol")));
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

        //Importar Daos
        CivilizacionDao civilizacionDao = new CivilizacionDao();

        String sql = "";
        //los parametros varían segun la profesion

        int alimentoDia = 0;
        int moral = 0;
        int fuerza = 0;
        int produce = 0;
        //crear funciones random


        //Falta validar que no deje crear persona si el tiempo está en 17 (GRanjero, Constructor y soldado)
        //Lo mismo pero no deja crear nada si el tiempo es de 23
        switch (profesion){
            case "Granjero":
                if(civilizacionDao.obtenerCivilizacion(idCivilizacion).getTimeElapsed()<17){
                    alimentoDia = randomNum(10,30);
                    moral = randomNum(10,40);
                    produce = randomNum(100,200);
                    sql = "insert into personas(id_civilizacion,genero,alimento_dia,moral,produce,nombre,id_rol) values(?,?,?,?,?,?,?)";
                    try(Connection conn=this.getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
                        pstmt.setInt(1,idCivilizacion);
                        pstmt.setString(2,genero);
                        pstmt.setInt(3,alimentoDia);
                        pstmt.setInt(4,moral);
                        pstmt.setInt(5,produce);//produce alimento
                        pstmt.setString(6,nombre);
                        pstmt.setString(7,"G");
                        pstmt.executeUpdate();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    //actualizar + 8 horas
                    civilizacionDao.sumar8horas(idCivilizacion);
                }else{
                    //Se debe pasar las horas de manera manual
                }


                break;
            case "Constructor":
                if(civilizacionDao.obtenerCivilizacion(idCivilizacion).getTimeElapsed()<17){
                    alimentoDia = randomNum(50,70);
                    moral = randomNum(10,40);
                    fuerza = randomNum(2,20);
                    produce = randomNum(10,20);
                    sql = "insert into personas(id_civilizacion,genero,alimento_dia,moral,fuerza,produce,nombre,id_rol) values(?,?,?,?,?,?,?,?)";
                    try(Connection conn=this.getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
                        pstmt.setInt(1,idCivilizacion);
                        pstmt.setString(2,genero);
                        pstmt.setInt(3,alimentoDia);
                        pstmt.setInt(4,moral);
                        pstmt.setInt(5,fuerza);
                        pstmt.setInt(6,produce);//moral
                        pstmt.setString(7,nombre);
                        pstmt.setString(8,"C");
                        pstmt.executeUpdate();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    //actualizar + 8 horas
                    civilizacionDao.sumar8horas(idCivilizacion);
                }else{
                    //Se debe pasar las horas de manera manual con un boton
                }

                break;
            case "Soldado":
                if(civilizacionDao.obtenerCivilizacion(idCivilizacion).getTimeElapsed()<17){
                    alimentoDia = randomNum(70,100);
                    moral = randomNum(30,50);
                    fuerza = randomNum(15,50);
                    produce = randomNum(0,20);

                    sql = "insert into personas(id_civilizacion,genero,alimento_dia,moral,fuerza,produce,nombre,id_rol) values(?,?,?,?,?,?,?,?)";

                    try(Connection conn=this.getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
                        pstmt.setInt(1,idCivilizacion);
                        pstmt.setString(2,genero);
                        pstmt.setInt(3,alimentoDia);
                        pstmt.setInt(4,moral);
                        pstmt.setInt(5,fuerza);
                        pstmt.setInt(6,produce);//moral
                        pstmt.setString(7,nombre);
                        pstmt.setString(8,"S");
                        pstmt.executeUpdate();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    //actualizar + 8 horas
                    civilizacionDao.sumar8horas(idCivilizacion);
                }else{
                    //Se debe pasar las horas de manera manual
                }
                break;
            case "Ninguna":

                if(civilizacionDao.obtenerCivilizacion(idCivilizacion).getTimeElapsed()<23){
                    alimentoDia = randomNum(30,50);
                    moral = randomNum(20,50);
                    sql = "insert into personas(id_civilizacion,genero,alimento_dia,moral,nombre,id_rol) values(?,?,?,?,?,?)";

                    try(Connection conn=this.getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
                        pstmt.setInt(1,idCivilizacion);
                        pstmt.setString(2,genero);
                        pstmt.setInt(3,alimentoDia);
                        pstmt.setInt(4,moral);
                        pstmt.setString(5,nombre);
                        pstmt.setString(6,"N");
                        pstmt.executeUpdate();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    //actualizar + 2 horas
                    civilizacionDao.sumar2horas(idCivilizacion);
                }else{
                    //se debe pasar el tiempo de manera manual
                }
                break;

        }

    }




    public int randomNum(int min, int max){
        Random rd = new Random();
        int randNum = rd.nextInt(max - min + 1) + min;
        return randNum;
    }


    public void exiliarPersona(int idPersona, int idCivilizacion){ //

        //Se obtiene el moral de la persona a exiliar
        int moralExiliado = obtenerPersona(idPersona).getMoral();

        String sql = "";
        //Aquí se exilia a la persona (elimina)
        sql = "DELETE FROM personas WHERE id_personas = ? and id_civilizacion = ?";
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


    //No hice cambio aquí
    public void muertePorDepresion(int idPersona){
        Persona persona = obtenerPersona(idPersona); //se obtiene una persona en base al id
        if(persona.getMoral()<=0){
            //falta update del campo "vivo o muerto"
            String sql = "update personas set moral = 0, motivoMuerte = ? where id_personas = ?"; //puede que se de el caso de que la moral llega a negativo
            try(Connection conn=this.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)){

                pstmt.setString(1,"Muerte por depresion");
                pstmt.setInt(2,idPersona);
                pstmt.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            int tiempoDelQueMurio = obtenerPersona(idPersona).getDaysAlive();//tiempo del muerto
            int randomRed = 0;//Inicializo la variable reduccion
            int idCivilPersonaMuerta = obtenerPersona(idPersona).getCivilizacion().getIdCivilizacion();//idCivilizacion a la que pertenece el muerto

            ArrayList<Integer> listaIdPersonas = listaIdPersonasXCivilizacion(idCivilPersonaMuerta);
            for(int idP:listaIdPersonas){

                //Falta validar que se considere las Id de las personas que estan vivas
                //Se debe a que falta el campo de "vivo o muerto"
                int tiempoDelVivo = obtenerPersona(idP).getDaysAlive(); //Se obtiene el tiempoDeVida de la persona
                randomRed = randomNum(0,tiempoDelQueMurio+tiempoDelVivo); //Random de (0 , X)

                //Aca actualiza de manera aleatoria para cada persona
                String sqlUpd = "update personas set moral = moral - ? where id_personas = ?";

                try(Connection conn=this.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sqlUpd)){

                    pstmt.setInt(1,randomRed);
                    pstmt.setInt(2,idP);
                    pstmt.executeUpdate();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }


    public ArrayList<Integer> listaIdPersonasXCivilizacion(int idCivilizacion){
        ArrayList<Integer> listaIdsPersonas = new ArrayList<>();

        String sql = "select p.id_personas from personas p inner join civilizacion c on p.id_civilizacion = c.id_civilizacion where c.id_civilizacion = ?";
        try (Connection conn=this.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idCivilizacion);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    listaIdsPersonas.add(rs.getInt(1));
                }
            }
            return listaIdsPersonas;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


}
