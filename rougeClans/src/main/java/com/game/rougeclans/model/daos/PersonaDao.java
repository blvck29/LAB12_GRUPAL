package com.game.rougeclans.model.daos;

import com.game.rougeclans.model.beans.Civilizacion;
import com.game.rougeclans.model.beans.Persona;
import com.game.rougeclans.model.beans.Constructor;
import com.game.rougeclans.model.beans.Granjero;
import com.game.rougeclans.model.beans.Soldado;
import com.game.rougeclans.model.beans.Ninguna;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;


public class PersonaDao extends DaoBase{

    public Persona obtenerPersona(int idPersona){

        Persona persona = null;
        String profesion = obtenerProfesion(idPersona);
        CivilizacionDao civilizacionDao = new CivilizacionDao(); //Para ahorrar código
        String sql = "select * from personas where id_personas = ?;";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,idPersona);

            try(ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    switch (profesion){
                        case "Granjero":
                            persona = new Granjero();
                            break;
                        case "Constructor":
                            persona = new Constructor();
                            break;
                        case "Soldado":
                            persona = new Soldado();
                            break;
                        case "Ninguna":
                            persona = new Ninguna();
                            break;
                    }
                    persona.setIdPersona(rs.getInt("id_personas"));
                    persona.setCivilizacion(civilizacionDao.obtenerCivilizacion(rs.getInt("id_civilizacion")));
                    persona.setGenero(rs.getString("genero")); //Hacer flujo de transformar los caracteres 'M' o 'F' a 'masculino' y 'femenino' si es necesario
                    persona.setAlimentado(rs.getBoolean("alimentado"));
                    persona.setDaysAlive(rs.getInt("days_alive"));
                    persona.setProfesion(rs.getString("profesion"));
                    persona.setMuerto(rs.getBoolean("muerto"));
                    persona.setDiaMuerte(rs.getInt("dia_muerte"));
                    persona.setMotivoMuerte(rs.getString("motivoMuerte"));
                    persona.setNombre(rs.getString("nombre"));
                    if(persona instanceof Granjero){
                        Granjero granjero = (Granjero) persona;
                        granjero.setAlimentoDia(rs.getInt("alimento_dia"));
                        granjero.setMoral(rs.getInt("moral"));
                        granjero.setProduce(rs.getInt("produce"));
                    }
                    if(persona instanceof Constructor){
                        Constructor constructor = (Constructor) persona;
                        constructor.setAlimentoDia(rs.getInt("alimento_dia"));
                        constructor.setMoral(rs.getInt("moral"));
                        constructor.setFuerza(rs.getInt("fuerza"));
                        constructor.setProduce(rs.getInt("produce"));
                    }
                    if(persona instanceof Soldado){
                        Soldado soldado = (Soldado) persona;
                        soldado.setAlimentoDia(rs.getInt("alimento_dia"));
                        soldado.setMoral(rs.getInt("moral"));
                        soldado.setFuerza(rs.getInt("fuerza"));
                        soldado.setProduce(rs.getInt("produce"));
                    }
                    if(persona instanceof Ninguna){
                        Ninguna ninguna = (Ninguna) persona;
                        ninguna.setAlimentoDia(rs.getInt("alimento_dia"));
                        ninguna.setMoral(rs.getInt("moral"));
                    }
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
    //----------------------Al final usaremos estas para obtener estos parámetros (mucho menos código que si casteamos por polimorfismo)------
    public String obtenerProfesion(int idPersona){
        String profesion = "";
        String sql = "select profesion from personas where id_personas = ?;";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,idPersona);

            try(ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    profesion = rs.getString("profesion");
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return profesion;
    }
    public int obtenerMoral(int idPersona){
        int moral=0;
        String sql = "select moral from personas where id_personas = ?;";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,idPersona);

            try(ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    moral = rs.getInt("moral");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return moral;
    }
    public int obtenerFuerza(int idPersona){
        int fuerza=0;
        String sql = "select fuerza from personas where id_personas = ?;";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,idPersona);

            try(ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    fuerza = rs.getInt("fuerza");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return fuerza;
    }
    public int obtenerProduce(int idPersona){
        int produce=0;
        String sql = "select produce from personas where id_personas = ?;";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,idPersona);

            try(ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    produce = rs.getInt("produce");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return produce;
    }
    public int obtenerAlimentoDia(int idPersona){
        int alimentoDia=0;
        String sql = "select alimento_dia from personas where id_personas = ?;";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,idPersona);

            try(ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    alimentoDia = rs.getInt("alimentoDia");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return alimentoDia;
    }
    //------------------------------------------------------------------------------------------------


    public Granjero castearPersonaAGranjero(Persona persona) {
        Granjero granjero = new Granjero();
        if (persona instanceof Granjero) {
            granjero = (Granjero) persona;
        }
        return granjero;
    }
    public Constructor castearPersonaAConstructor(Persona persona) {
        Constructor constructor = new Constructor();
        if (persona instanceof Constructor) {
            constructor = (Constructor) persona;
        }
        return constructor;
    }
    public Soldado castearPersonaASoldado(Persona persona) {
        Soldado soldado = new Soldado();
        if (persona instanceof Soldado) {
            soldado = (Soldado) persona;
        }
        return soldado;
    }
    public Ninguna castearPersonaANinguna(Persona persona) {
        Ninguna ninguna = new Ninguna();
        if (persona instanceof Ninguna) {
            ninguna = (Ninguna) persona;
        }
        return ninguna;
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
                    civilizacionDao.sumar8horas(idCivilizacion);
                }else{
                    //Se debe pasar las horas de manera manual
                }
                break;
            case "Ninguna":

                if(civilizacionDao.obtenerCivilizacion(idCivilizacion).getTimeElapsed()<23){
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
        int moralExiliado = obtenerMoral(idPersona);

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
        if(obtenerMoral(idPersona)<=0){
            //falta update del campo "vivo o muerto"
            String sql = "update personas set moral = 0,muerto = 1, motivoMuerte = ? where id_personas = ?"; //puede que se de el caso de que la moral llega a negativo
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

    public void muertePorHambre(int idPersona){
        //PersonaDao personaDao = new PersonaDao();

        Persona persona = obtenerPersona(idPersona); //se obtiene una persona en base al id
        if(obtenerMoral(idPersona)<=0){

            String sql = "update personas set moral = 0,muerto = 1, motivoMuerte = ? where id_personas = ?"; //puede que se de el caso de que la moral llega a negativo
            try(Connection conn=this.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)){

                pstmt.setString(1,"Muerte por hambre");
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

    public void restarMoralPorFaltaComida(int idPersona){
        String sql = "update personas set moral = moral -? where id_personas = ?";
        try(Connection conn=this.getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
            pstmt.setInt(1,obtenerAlimentoDia(idPersona));
            pstmt.setInt(2,idPersona);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(obtenerPersona(idPersona).isMuerto()){
            muertePorHambre(idPersona);
        }
    }
    public void personaAlimentada(int idPersona){
        String sql = "update personas set alimentado = 1 where id_personas = ?";
        try(Connection conn=this.getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
            pstmt.setInt(1,idPersona);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
