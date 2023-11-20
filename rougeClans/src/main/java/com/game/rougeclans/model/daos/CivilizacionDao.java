package com.game.rougeclans.model.daos;

import com.game.rougeclans.model.SHA256;
import com.game.rougeclans.model.beans.Civilizacion;
import com.game.rougeclans.model.beans.Guerra;
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

    public boolean sumar8horas(int idCivilizacion){

        boolean accionPermitida = false;
        String sql = "";
        sql = "select time_elapsed from civilizaciones where id_civilizacion = ?";
        try(Connection conn=this.getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
            pstmt.setInt(1,idCivilizacion);
            try(ResultSet rs=pstmt.executeQuery()){
                if(rs.next()){
                    //Aquí se valida que time_elapsed sea menor o igual a 24 horas
                    if(rs.getInt(1)<17){
                        accionPermitida = true;
                        sql = "update civilizaciones set time_elapsed = time_elapsed + 8 where id_civilizacion = ?"; //Sumamos 8 horas si time_elapsed es <= 16
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
        return accionPermitida;
    }
    public boolean sumar2horas(int idCivilizacion){

        boolean accionPermitida = false;
        String sql = "";
        sql = "select time_elapsed from civilizaciones where id_civilizacion = ?";
        try(Connection conn=this.getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
            pstmt.setInt(1,idCivilizacion);
            try(ResultSet rs=pstmt.executeQuery()){
                if(rs.next()){
                    //Aquí se valida que time_elapsed sea menor a 24 horas
                    if(rs.getInt(1)<23){
                        accionPermitida = true;
                        sql = "update civilizaciones set time_elapsed = time_elapsed + 2 where id_civilizacion = ?"; //Sumamos 2 horas si time_elapsed es <=22
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
        return accionPermitida;
    }

    public void actualizarTimeAndDaysElapsed(int idCivilizacion){ //usar para actualizar time_elapsed cuando time_elapsed = 24

        Civilizacion civilizacion = obtenerCivilizacion(idCivilizacion); // Ahorrar código

        if(civilizacion.getTimeElapsed()==24){

            //dar de comer a todos
            //if(civilizacion.getEstado().equalsIgnoreCase("En guerra"))
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
            String sql1 = "update personas set alimentado = 0 where id_civilizacion = ? and muerto = 0";
            try(Connection conn=getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql1)){
                pstmt.setInt(1,idCivilizacion);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            //actualizar los días de las personas
            String sql3 = "update personas set days_alive = days_alive + 1 where id_civilizacion = ? and muerto = 0";
            try(Connection conn=getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql3)){
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

        int fuerza = 0;

        String sql = "select sum(fuerza) from personas where id_civilizacion = ? and profesion = ? and muerto = 0";
        try (Connection conn=this.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idCivilizacion);
            pstmt.setString(2,"Soldado");
            try (ResultSet rs = pstmt.executeQuery()) {
                if(rs.next()){
                    fuerza = rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return fuerza;
    }
    public int fuerzaTotalDefensor(int idCivilizacion){

        int fuerza = 0;

        String sql = "select sum(fuerza) from personas where id_civilizacion = ? and muerto = 0";
        try (Connection conn=this.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idCivilizacion);
            try (ResultSet rs = pstmt.executeQuery()) {
                if(rs.next()){
                    fuerza = rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return fuerza;
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
        if(!idPersonasVivas.isEmpty()){
            int moralParcial = moralTotal/idPersonasVivas.size();
            String sql = "update personas set moral = moral + ? where id_civilizacion = ? and muerto = 0";
            try(Connection conn=this.getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
                pstmt.setInt(1, moralParcial);
                pstmt.setInt(2,idCivilizacion);

                pstmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int obtenerProduccionMoralCivilizacion(int idCivilizacion){
        return (obtenerProduccionSoldado(idCivilizacion)+obtenerProduccionConstructor(idCivilizacion));
    }
    public int obtenerProduccionConstructor(int idCivilizacion){

        String sql = "select sum(produce) from personas where profesion = ? and id_civilizacion = ? and muerto = 0";
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

        String sql = "select sum(produce) from personas where profesion = ? and id_civilizacion = ? and muerto = 0";
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
                    System.out.println(personaDao.obtenerPersona(id).isMuerto());
                    idsVivos.add(id);
                }
            }
            for(Integer idP:idsVivos){
                Integer moralP = personaDao.obtenerMoral(idP);
                String sql = "update personas set moral = moral - ? where id_personas = ? and muerto = 0";
                try(Connection conn=this.getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
                    pstmt.setInt(1, (int) Math.ceil((moralP*0.5)));//reduce en 50% la moral a cada uno
                    System.out.println(moralP);
                    System.out.println((int) Math.ceil((moralP*0.5)));
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

        int cant = 0;

        String sql = "select count(id_personas) as cantPoblacion from personas where id_civilizacion = ? and muerto = 0";

        try (Connection conn=this.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idCivilizacion);

            try (ResultSet rs = pstmt.executeQuery()) {
                if(rs.next()) {
                    cant = rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return cant;
    }

    public int obtenerAlimentoTotal(int idCivilizacion){


        if(obtenerCivilizacion(idCivilizacion).getEstado().equalsIgnoreCase("Paz")){
            return produccionAlimento(idCivilizacion);
        }else{
            int A = 0;
            int B = 0;
            GuerraDao guerraDao = new GuerraDao();
            for(Integer idG:guerraDao.listaIdGuerra()){
                boolean validarGuerraActual = guerraDao.obtenerGuerra(idG).getDiaAtacante() == obtenerCivilizacion(idCivilizacion).getDaysElapsed();
                boolean validarQueEsAtacante = guerraDao.obtenerGuerra(idG).getCivilizacionAtacante().getIdCivilizacion()==idCivilizacion;
                if( validarQueEsAtacante && validarGuerraActual){
                    if(guerraDao.obtenerGuerra(idG).getEstadoGuerra().equalsIgnoreCase("victoria")){//se compara el estado de guerram

                        A = produccionAlimento(idCivilizacion);

                        String sql = "select sum(produce) from personas where profesion = ? and id_civilizacion = ? and muerto = 0";
                        try (Connection conn=this.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
                            pstmt.setString(1, "Granjero");
                            pstmt.setInt(2, guerraDao.obtenerGuerra(idG).getCivilizacionDefensora().getIdCivilizacion());
                            try (ResultSet rs = pstmt.executeQuery()) {
                                B =  rs.getInt(1);
                            }
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        return A+B;//devuelve lo que el produce mas quien derrotó
                    }else{
                        return produccionAlimento(idCivilizacion);
                    }
                }else{
                    return produccionAlimento(idCivilizacion);
                }
            }
            return 0;//nunca se va a dar, pero es para que no me salga error
        }
    }
    public int produccionAlimento(int idCivilizacion){
        String sql = "select sum(produce) from personas where profesion = ? and id_civilizacion = ? and muerto = 0";

        //String sql = "select sum(moral) from personas where id_civilizacion = ?";
        try (Connection conn=this.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "Granjero");
            pstmt.setInt(2, idCivilizacion);
            try (ResultSet rs = pstmt.executeQuery()) {
                if(rs.next()){
                    return rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return 0;
    }
    public int obtenerMoralTotalCivilizacion(int idCivilizacion){

        String sql = "select sum(moral) from personas where id_civilizacion = ? and muerto = 0";
        try (Connection conn=this.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,idCivilizacion);
            try (ResultSet rs = pstmt.executeQuery()) {
                if(rs.next()){
                    return rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return 0;
    }

    public int obtenerAncianoDelPueblo (Civilizacion civilizacion) {

        int cant = 0;

        String sql = "select * from personas where id_civilizacion = ? and muerto = 0 order by days_alive desc limit 1;";
        try (Connection conn=this.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, civilizacion.getIdCivilizacion());
            try (ResultSet rs = pstmt.executeQuery()) {
                if(rs.next()){
                    cant = rs.getInt(9);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return cant;
    }
    public Integer maximoDiasCiudadano(int idCivilizacion) {
        ArrayList<Integer> listaDiasVivo = new ArrayList<>();
        String sql = "select days_alive, muerto from personas where id_civilizacion = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idCivilizacion);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    if (!rs.getBoolean(2)) {
                        listaDiasVivo.add(rs.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //obtener el mayor día dentro de la lista de días
        int diaMax = 0;
        for (Integer dia : listaDiasVivo) {
            if (diaMax < dia) {
                diaMax = dia;
            }
        }
        return diaMax;
    }
    public Integer fuerzaTotalConstructor(int idCivilizacion){
        String sql = "select sum(fuerza) from personas where profesion ='Constructor' and id_civilizacion = ? and muerto = 0";
        try (Connection conn=this.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idCivilizacion);
            try (ResultSet rs = pstmt.executeQuery()) {
                if(rs.next()){
                return rs.getInt(1);}
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return 0;
    }
    public Integer fuerzaTotalSoldado(int idCivilizacion){
        String sql = "select sum(fuerza) from personas where profesion ='Soldado' and id_civilizacion = ? and muerto = 0";
        try (Connection conn=this.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idCivilizacion);
            try (ResultSet rs = pstmt.executeQuery()) {
                if(rs.next()){
                    return rs.getInt(1);}
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return 0;
    }


    public Integer fuerzaCivilizacion(int idCivilizacion){//para Leaderboard
        return fuerzaTotalConstructor(idCivilizacion)+fuerzaTotalSoldado(idCivilizacion);
    }

    public Integer contarPersonasProfesionCivilizacion(int idCivilizacion, String profesion){ //cuenta la cantidad de personas por profesion
        Integer cantPersonas = 0;
        String sql ="select count(id_personas) from personas where profesion=? and id_civilizacion = ? and muerto = 0";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,profesion);
            pstmt.setInt(2,idCivilizacion);

            try(ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    cantPersonas = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cantPersonas;

    }

    public Integer obtenerFuerzaTotalProfesionCivilizacion(int idCivilizacion, String profesion){ //se valido que las personas estan vivas
        Integer cantFuerza = 0;
        String sql ="select sum(fuerza) from personas where profesion=? and id_civilizacion = ? and muerto = 0";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,profesion);
            pstmt.setInt(2,idCivilizacion);

            try(ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    cantFuerza = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cantFuerza;
    }

    public ArrayList<Persona> listarPersonasMuertasUltimoDia(int idCivilizacion){

        ArrayList<Persona> lista= new ArrayList<>();
        PersonaDao personaDao = new PersonaDao();
        Civilizacion civilizacion= obtenerCivilizacion(idCivilizacion);

        String sql = "select * from personas where id_civilizacion = ? and muerto = 1 and dia_muerte = ?;";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,idCivilizacion);
            pstmt.setInt(2,civilizacion.getDaysElapsed()-1); //Muertos del día anterior

            try(ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Persona persona = new Persona();
                    persona = personaDao.obtenerPersona(rs.getInt("id_personas"));
                    lista.add(persona);
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

}
