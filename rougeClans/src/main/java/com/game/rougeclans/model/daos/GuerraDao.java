package com.game.rougeclans.model.daos;

import com.game.rougeclans.model.beans.Civilizacion;
import com.game.rougeclans.model.beans.Guerra;
import com.game.rougeclans.model.beans.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GuerraDao extends DaoBase {

    public Guerra obtenerGuerra(int idGuerra){

        Guerra guerra = new Guerra();
        CivilizacionDao civilizacionDao = new CivilizacionDao(); //Para ahorrar código

        String sql = "select * from guerra where id_guerra = ?;";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,idGuerra);

            try(ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    guerra.setIdGuerra(rs.getInt("id_guerra"));
                    guerra.setCivilizacionAtacante(civilizacionDao.obtenerCivilizacion(rs.getInt("id_civilizacion_atacante")));
                    guerra.setCivilizacionDefensora(civilizacionDao.obtenerCivilizacion(rs.getInt("id_civilizacion_defensora")));
                    guerra.setEstadoGuerra(rs.getString("estado_guerra"));
                    guerra.setDiaAtacante(rs.getInt("dia_atacante"));
                    guerra.setDiaDefensor(rs.getInt("dia_defensor"));
                }
                else {
                    guerra = null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return guerra;
    }

    public int obtenerRolGuerra(int idGuerra, int  idCivilizacion){ //1: atacante | 2: defensor

        int rol = 0; //1: atacante | 2: defensor
        String sql = "select * from guerra where id_guerra = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,idGuerra);

            try(ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    if(idCivilizacion == rs.getInt("id_civilizacion_atacante")){
                        rol = 1;
                    }
                    if(idCivilizacion == rs.getInt("id_civilizacion_defensora")){
                        rol = 2;
                    };

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rol;
    }

    public ArrayList<Guerra> listarGuerras(int idCivilizacion){

        ArrayList<Guerra> lista= new ArrayList<>();
        CivilizacionDao civilizacionDao = new CivilizacionDao();

        String sql = "select * from guerra where id_civilizacion_atacante = ? or id_civilizacion_defensora = ?;";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,idCivilizacion); // atacamos
            pstmt.setInt(2,idCivilizacion); // fuimos atacados

            try(ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Guerra guerra = new Guerra();
                    guerra.setIdGuerra(rs.getInt("id_guerra"));
                    guerra.setCivilizacionAtacante(civilizacionDao.obtenerCivilizacion(rs.getInt("id_civilizacion_atacante")));
                    guerra.setCivilizacionDefensora(civilizacionDao.obtenerCivilizacion(rs.getInt("id_civilizacion_defensora")));
                    guerra.setEstadoGuerra(rs.getString("estado_guerra"));
                    guerra.setDiaAtacante(rs.getInt("dia_atacante"));
                    guerra.setDiaDefensor(rs.getInt("dia_defensor"));
                    lista.add(guerra);
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public int calcularGuerrasGanadas(int idCivilizacion){

        int guerrasGanadas = 0;

        String sql = "select * from guerra where id_civilizacion_atacante = ? or id_civilizacion_defensora = ?;";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,idCivilizacion); // atacamos
            pstmt.setInt(2,idCivilizacion); // fuimos atacados

            try(ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    if(rs.getInt("id_civilizacion_atacante")==idCivilizacion){
                        if(rs.getString("estado_guerra").equalsIgnoreCase("VA")){
                            guerrasGanadas++;
                        }
                    }
                    if(rs.getInt("id_civilizacion_defensora")==idCivilizacion){
                        if(rs.getString("estado_guerra").equalsIgnoreCase("VD")){
                            guerrasGanadas++;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return guerrasGanadas;
    }
    public int calcularGuerrasPerdidas(int idCivilizacion){


        int guerrasPerdidas = 0;

        String sql = "select * from guerra where id_civilizacion_atacante = ? or id_civilizacion_defensora = ?;";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,idCivilizacion); // atacamos
            pstmt.setInt(2,idCivilizacion); // fuimos atacados

            try(ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    if(rs.getInt("id_civilizacion_atacante")==idCivilizacion){
                        if(rs.getString("estado_guerra").equalsIgnoreCase("VD")){
                            guerrasPerdidas++;
                        }
                    }
                    if(rs.getInt("id_civilizacion_defensora")==idCivilizacion){
                        if(rs.getString("estado_guerra").equalsIgnoreCase("VA")){
                            guerrasPerdidas++;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return guerrasPerdidas;
    }


    public double obtenerWinRate(int idCivilizacion){

        double winRate = 0;

        double perdidas = calcularGuerrasPerdidas(idCivilizacion);
        double ganadas = calcularGuerrasGanadas(idCivilizacion);

        if(perdidas != 0 && ganadas != 0){
            winRate = (ganadas/(ganadas+perdidas))*100;
        }
        if(perdidas == 0 && ganadas != 0){
            winRate = 100;
        }

        return winRate;
    }


    public void civilizacionAtacanteGana(int idCivilizacionAtacante, int idGuerra){//No se si se utiliza
        String sql = " ";
        String va = obtenerGuerra(idGuerra).getEstadoGuerra();
        if(va.equalsIgnoreCase("VA")){

            sql = "update personas set moral = 2*moral, fuerza = cast(1.2*fuerza as unsigned) where profesion='Soldado' and id_civilizacion = ?";
            try(Connection conn=this.getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
                pstmt.setInt(1, idCivilizacionAtacante);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            sql = "update personas set produce = cast(1.2*produce as unsigned) where profesion='Granjero' and id_civilizacion = ?";
            try(Connection conn=this.getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
                pstmt.setInt(1, idCivilizacionAtacante);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }

    public void civilizacionDefensoraGana(int idCivilizacionDefensora, int idGuerra){
        String vd = obtenerGuerra(idGuerra).getEstadoGuerra();
        if(vd.equalsIgnoreCase("VD")){
            String sql = "update personas set produce = cast(produce*1.4 as unsigned) where id_civilizacion = ?";
            try(Connection conn=this.getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
                pstmt.setInt(1, idCivilizacionDefensora);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean calcularGanador(int idCivilizacionAtacante, int idCivilizacionDefensora){//

        boolean ganoAtacante = false;
        CivilizacionDao civilizacionDao = new CivilizacionDao();
        Civilizacion civilizacionAtacante = civilizacionDao.obtenerCivilizacion(idCivilizacionAtacante);
        Civilizacion civilizacionDefensora = civilizacionDao.obtenerCivilizacion(idCivilizacionDefensora);


        String sql = "insert into guerra (id_civilizacion_atacante,id_civilizacion_defensora,estado_guerra,dia_atacante,dia_defensor) values (?,?,?,?,?)";

        try(Connection conn=this.getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){

            //Cálculo:
            pstmt.setInt(1,idCivilizacionAtacante);
            pstmt.setInt(2,idCivilizacionDefensora);
            pstmt.setInt(4,civilizacionAtacante.getDaysElapsed());
            civilizacionDao.pasarLasHoras(idCivilizacionAtacante);
            civilizacionDao.actualizarTimeAndDaysElapsed(idCivilizacionAtacante); //proceso de pasar un día para la civilización atacante
            pstmt.setInt(5,civilizacionDefensora.getDaysElapsed());
            if(civilizacionDao.fuerzaTotalAtacante(idCivilizacionAtacante)>civilizacionDao.fuerzaTotalDefensor(idCivilizacionDefensora)){
                pstmt.setString(3,"VA");
                ganoAtacante = true;
            }
            else{
                pstmt.setString(3,"VD");
            }

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ganoAtacante;
    }

    public int obtenerUltimoIdGuerra(){
        int ultimoId = 0;

        String sql = "select max(id_guerra) from guerra;";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            try(ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    ultimoId = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ultimoId;
    }

    public void asignarPuntajes(int idGuerra){
        Guerra guerra = obtenerGuerra(idGuerra);
        switch (guerra.getEstadoGuerra()){
            case "VA":
                //Falta el método de ganaAtacante
                pierdeDefensor(guerra.getCivilizacionDefensora().getIdCivilizacion(),idGuerra);
                break;
            case "VD":
                //Falta el método de ganaDefensor
                pierdeAtacante(guerra.getCivilizacionAtacante().getIdCivilizacion(),idGuerra);
                break;
        }
    }

    public void pierdeAtacante(int idCivilizacion, int idGuerra){
        PersonaDao personaDao  = new PersonaDao();
        int idCivilOponent = obtenerGuerra(idGuerra).getCivilizacionDefensora().getIdCivilizacion();
        int[] probabilidad = {1,1,1,1,1,1,2,2,2,3};
        //1:soldado muere
        //2:sobrevive pero pierde moral
        //3:se una al enemigo
        Random rd = new Random();
        int indAleatorio = rd.nextInt(probabilidad.length);
        int numProb = probabilidad[indAleatorio];
        if(obtenerGuerra(idGuerra).getEstadoGuerra().equalsIgnoreCase("VD")){
            for(Integer idPer:personaDao.listaIdPersonasXCivilizacion(idCivilizacion)){
                switch (numProb){
                    case 1:
                        muerePersona(idPer,"Soldado");//se muere el soldado
                        //los demás sufren depresión por muerte
                        personaDao.muertePorDepresion(idPer);
                        break;
                    case 2:
                        pierdeMitadMoralPersona(idPer,"Soldado");//
                        break;
                    case 3:
                        personaCambiaBando(idPer,idCivilOponent,"Soldado");//soldado cambia bando
                        break;

                }
            }

        }

    }

    public void pierdeDefensor(int idCivilizacion, int idGuerra){
        PersonaDao personaDao  = new PersonaDao();
        int idCivilOponent = obtenerGuerra(idGuerra).getCivilizacionDefensora().getIdCivilizacion();
        int[] probabilidad = {1,1,1,1,1,1,2,2,2,3};
        //1:soldado muere
        //2:sobrevive pero pierde moral
        //3:se una al enemigo
        Random rd = new Random();
        int indAleatorio = rd.nextInt(probabilidad.length);
        int numProb = probabilidad[indAleatorio];
        if(obtenerGuerra(idGuerra).getEstadoGuerra().equalsIgnoreCase("VA")){
            for(Integer idPer:personaDao.listaIdPersonasXCivilizacion(idCivilizacion)){
                switch (numProb){
                    case 1:
                        muerePersona(idPer,"Constructor");//se muere el soldado
                        //los demás sufren depresión por muerte
                        personaDao.muertePorDepresion(idPer);
                        break;
                    case 2:
                        pierdeMitadMoralPersona(idPer,"Constructor");//
                        break;
                    case 3:
                        personaCambiaBando(idPer,idCivilOponent,"Constructor");//soldado cambia bando
                        break;

                }
            }
            PersonaDao pDao = new PersonaDao();//solo lo instancio para acceder a la funcion random
            int moralPerd = pDao.randomNum(20,80);
            double moralPerc = 1-moralPerd*0.01;

            //Los demás pobladores pierden moral
            //Soldado
            perderMoralRandom(moralPerc,"Soldado",idCivilizacion);
            //Granjero
            perderMoralRandom(moralPerc,"Granjero",idCivilizacion);
            //Ninguna
            perderMoralRandom(moralPerc,"Ninguna",idCivilizacion);

        }
    }

    public void muerePersona(int idPersona, String profesion){
        String sql = "update personas set muerto = 1 where profesion = ? and id_personas = ?";
        try(Connection conn=this.getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
            pstmt.setString(1, profesion);
            pstmt.setInt(2, idPersona);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void pierdeMitadMoralPersona(int idPersona, String profesion){
        String sql = "update personas set moral = cast(0.5*moral as unsigned) where profesion = ? and id_personas = ?";
        try(Connection conn=this.getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
            pstmt.setString(1, profesion);
            pstmt.setInt(2, idPersona);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void personaCambiaBando(int idPersona, int idCivilizacion, String profesion){

        String sql = "update personas set id_civilizacion = ? where profesion = ? and id_personas = ?";
        try(Connection conn=this.getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
            pstmt.setInt(1, idCivilizacion);
            pstmt.setString(2, profesion);
            pstmt.setInt(3,idPersona);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void perderMoralRandom(Double moralPer, String profesion,int idCivilizacion){
        String sql = "update personas set moral = cast(moral*? as unsigned) where profesion=? and id_civilizacion = ?";
        try(Connection conn=this.getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
            pstmt.setDouble(1, moralPer);
            pstmt.setString(2, profesion);
            pstmt.setInt(3, idCivilizacion);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Integer> listaIdGuerra(){
        ArrayList<Integer> listaId = new ArrayList<>();
        String sql = "select id_guerra from guerra";
        try (Connection conn=this.getConnection(); ResultSet rs=conn.createStatement().executeQuery(sql)) {

            while (rs.next()) {
                listaId.add(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaId;
    }



}
