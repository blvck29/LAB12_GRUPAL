package com.game.rougeclans.model.daos;

import com.game.rougeclans.model.SHA256;
import com.game.rougeclans.model.beans.Civilizacion;
import com.game.rougeclans.model.beans.Jugador;
import com.game.rougeclans.model.beans.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

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
                    civilizacion.setNombre(rs.getString("nombre"));
                    civilizacion.setTimeElapsed(rs.getInt("time_elapsed"));
                    civilizacion.setDaysElapsed(rs.getInt("days_elapsed"));
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


    public int obtenerId(int idJugador){
        int idCivilizacion = 0;

        String sql = "select id_civilizacion from civilizaciones where id_jugadores = ?;";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,idJugador);

            try(ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    idCivilizacion = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return idCivilizacion;
    }

    public void crearCivilizacion(int idJugador, String nombre){

        String sql = "insert into civilizaciones(id_jugadores,nombre) values (?,?)";

        try(Connection conn=this.getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
            pstmt.setInt(1, idJugador);
            pstmt.setString(2,nombre);

            pstmt.executeUpdate();
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
    /*public void pasarDia(int idCivilizacion){
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
    }*/

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

            //dar de comer a todos
            if(civilizacion.getEstado().equalsIgnoreCase("En guerra"))
                alimentarPoblacion(idCivilizacion);

            //seguimiento de poblacion
            crecimientoPoblacion(idCivilizacion);

            //subir moral a todos
            subirMoral(idCivilizacion);

            //String sql = "update civilizaciones set time_elapsed = time_elapsed - 24, days_elapsed = days_elapsed + 1 where id_civilizacion = ?";
            String sql = "update civilizaciones set time_elapsed = 0, days_elapsed = days_elapsed + 1 where id_civilizacion = ?";

            try(Connection conn=getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
                pstmt.setInt(1,idCivilizacion);
                pstmt.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            //actualizar alimentado a 0
            String sql1 = "update personas set alimentado = 0 where id_civilizacion = ?";
            try(Connection conn=getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql1)){
                pstmt.setInt(1,idCivilizacion);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            //En el pdf dice que se debe listar la muerte de las personas muertas en el dia anterior
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
                    civilizacion.setNombre(rs.getString("nombre"));
                    civilizacion.setTimeElapsed(rs.getInt("time_elapsed"));
                    civilizacion.setDaysElapsed(rs.getInt("days_elapsed"));
                    lista.add(civilizacion);
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public void alimentarPoblacion(int idCivilizacion){

        int alimentoTotal = obtenerAlimentoTotal(idCivilizacion);

        PersonaDao personaDao = new PersonaDao();
        //se obtiene una lista de ids de personas
        ArrayList<Integer> idPersonas = personaDao.listaIdPersonasXCivilizacion(idCivilizacion);

        Random rd = new Random();
        while(!idPersonas.isEmpty() && alimentoTotal>=0){

            int idAleatorio = rd.nextInt(idPersonas.size());//un id aleatorio con la cantidad de idsPersonas
            int idPersonaAleatoria = idPersonas.get(idAleatorio); //se obtiene el idPersona en base al id(numero aleatorio)


            if(!personaDao.obtenerPersona(idPersonaAleatoria).isMuerto()){
                //se obtiene el alimento de la persona aleatoria
                int alimentoPersonaRd =  personaDao.obtenerAlimentoDia(idPersonaAleatoria);

                //se valida que el alimento sea lo suficiente
                if(alimentoPersonaRd>alimentoTotal){
                    //si no es suficiente ocurre que se reduce la moral en base a lo que le faltó comer
                    for(Integer idPersonaRestante:idPersonas){
                        personaDao.restarMoralPorFaltaComida(idPersonaRestante);
                    }
                    break;
                }else{
                    //se resta lo alimentado
                    alimentoTotal = alimentoTotal - alimentoPersonaRd;
                }

                //se actualiza en base de datos a la persona alimentada
                personaDao.personaAlimentada(idPersonaAleatoria);

                //se remueve el indice(id) ligado al idPersona de la lista para no volver a escoger a la persona que ha sido alimentada
                idPersonas.remove(idAleatorio);
            }else{
                //se remueve el indice(id) asociado de esa persona muerta
                idPersonas.remove(idAleatorio);
            }
        }


    }

    public int fuerzaTotalAtacante(int idCivilizacion){
        String sql = "select sum(fuerza) from personas where id_civilizacion = ? and profesion = ?";
        try (Connection conn=this.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idCivilizacion);
            pstmt.setString(2,"Soldado");
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    public int fuerzaTotalDefensor(int idCivilizacion){
        String sql = "select sum(fuerza) from personas where id_civilizacion = ?";
        try (Connection conn=this.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idCivilizacion);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void subirMoral(int idCivilizacion){
        int moralTotal = obtenerProduccionMoralCivilizacion(idCivilizacion);
        PersonaDao personaDao = new PersonaDao();
        //se obtiene una lista de ids de personas
        ArrayList<Integer> idPersonasT = personaDao.listaIdPersonasXCivilizacion(idCivilizacion);
        ArrayList<Integer> idPersonasVivas = new ArrayList<>();
        for(Integer id:idPersonasT){
            if(!personaDao.obtenerPersona(id).isMuerto()){
                idPersonasVivas.add(id);
            }
        }
        int moralParcial = moralTotal/idPersonasVivas.size();
        String sql = "update personas set moral = moral + ? where id_civilizacion = ?";
        try(Connection conn=this.getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
            pstmt.setInt(1, moralParcial);
            pstmt.setInt(2,idCivilizacion);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int obtenerProduccionMoralCivilizacion(int idCivilizacion){
        return (obtenerProduccionSoldado(idCivilizacion)+obtenerProduccionConstructor(idCivilizacion));
    }
    public int obtenerProduccionConstructor(int idCivilizacion){

        String sql = "select sum(produce) from personas where profesion = ? and id_civilizacion = ?";
        try (Connection conn=this.getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)) {
            pstmt.setString(1,"Constructor");
            pstmt.setInt(2,idCivilizacion);
            try(ResultSet rs=pstmt.executeQuery()){
                if (rs.next()) {
                    return rs.getInt(1);
                }else{
                    return 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public int obtenerProduccionSoldado(int idCivilizacion){

        String sql = "select sum(produce) from personas where profesion = ? and id_civilizacion = ?";
        try (Connection conn=this.getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)) {
            pstmt.setString(1,"Soldado");
            pstmt.setInt(2,idCivilizacion);
            try(ResultSet rs=pstmt.executeQuery()){
                if (rs.next()) {
                    return rs.getInt(1);
                }else{
                    return 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void crecimientoPoblacion(int idCivilizacion){
        PersonaDao personaDao = new PersonaDao();
        int diasCivil = obtenerCivilizacion(idCivilizacion).getDaysElapsed();
        int cantPoblacion = obtenerPoblacionTotal(idCivilizacion);
        if(!(cantPoblacion>4*diasCivil)){//si población no es  mayor a cuatro veces los días
            //reducir moral
            ArrayList<Integer> idsPersonas = personaDao.listaIdPersonasXCivilizacion(idCivilizacion);
            ArrayList<Integer> idsVivos = new ArrayList<>();
            for(Integer id:idsPersonas){
                if(!(personaDao.obtenerPersona(id).isMuerto())){
                    idsVivos.add(id);
                }
            }
            for(Integer idP:idsVivos){
                Integer moralP = personaDao.obtenerMoral(idP);
                String sql = "update personas set moral = moral - ? where id_personas = ?";
                try(Connection conn=this.getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
                    pstmt.setInt(1, (int) Math.ceil((moralP*0.5)));//reduce en 50% la moral a cada uno
                    pstmt.setInt(2,idCivilizacion);
                    pstmt.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                //si moral llega a 0 aplicar la funcion muerte por depresión
                if(personaDao.obtenerMoral(idP)<=0){
                    personaDao.muertePorDepresion(idP);
                }
            }
        }

    }
    public int obtenerPoblacionTotal(int idCivilizacion){
        String sql = "select count(id_personas) as cantPoblacion from personas where id_civilizacion = ?";

        try (Connection conn=this.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idCivilizacion);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public int obtenerAlimentoTotal(int idCivilizacion){
        String sql = "select sum(produce) from personas where profesion = ? and id_civilizacion = ?";

        try (Connection conn=this.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "Granjero");
            pstmt.setInt(2, idCivilizacion);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Integer obtenerMoralTotalCivilizacion(int idCivilizacion){

        String sql = "select sum(moral) from personas where id_civilizacion = ?";
        try (Connection conn=this.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idCivilizacion);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
