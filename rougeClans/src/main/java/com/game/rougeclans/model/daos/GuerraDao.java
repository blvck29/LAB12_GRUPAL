package com.game.rougeclans.model.daos;

import com.game.rougeclans.model.beans.Civilizacion;
import com.game.rougeclans.model.beans.Guerra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public int obtenerRolGuerra(int idGuerra, Civilizacion civilizacion){ //1: atacante | 2: defensor

        int rol = 0; //1: atacante | 2: defensor
        String sql = "select * from guerra where id_guerra = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,idGuerra);

            try(ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    if(civilizacion.getIdCivilizacion() == rs.getInt("id_civilizacion_atacante")){
                        rol = 1;
                    }
                    if(civilizacion.getIdCivilizacion() == rs.getInt("id_civilizacion_defensora")){
                        rol = 2;
                    };

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rol;
    }

    public ArrayList<Guerra> listarGuerras(Civilizacion civilizacion){

        ArrayList<Guerra> lista= new ArrayList<>();
        CivilizacionDao civilizacionDao = new CivilizacionDao();

        String sql = "select * from guerra where id_civilizacion_atacante = ? or id_civilizacion_defensora = ?;";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,civilizacion.getIdCivilizacion());
            pstmt.setInt(2,civilizacion.getIdCivilizacion());

            try(ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
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

    public int calcularGuerrasGanadas(Civilizacion civilizacion, ArrayList<Guerra> lista){
        int guerrasGanadas = 0;
        for (Guerra guerra: lista){
            int rol = obtenerRolGuerra(guerra.getIdGuerra(),civilizacion);

            if (rol ==1){
                if(guerra.getEstadoGuerra().equalsIgnoreCase("VA")){
                    guerrasGanadas++;
                }
            }
            if (rol == 2){
                if(guerra.getEstadoGuerra().equalsIgnoreCase("VD")){
                    guerrasGanadas++;
                }
            }
        }
        return guerrasGanadas;
    }
    public int calcularGuerrasPerdidas(Civilizacion civilizacion, ArrayList<Guerra> lista){
        int guerrasPerdidas = 0;
        for (Guerra guerra: lista){
            int rol = obtenerRolGuerra(guerra.getIdGuerra(),civilizacion);

            if (rol ==1){
                if(guerra.getEstadoGuerra().equalsIgnoreCase("VD")){
                    guerrasPerdidas++;
                }
            }
            if (rol == 2){
                if(guerra.getEstadoGuerra().equalsIgnoreCase("VA")){
                    guerrasPerdidas++;
                }
            }
        }
        return guerrasPerdidas;
    }

    public void civilizacionAtacanteGana(int idCivilizacion, int idGuerra){
        String sql = " ";
        sql = "update guerra set estado_guerra = 'victoria' where id_guerra = ? ";
        try(Connection conn=this.getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
            pstmt.setInt(1, idGuerra);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        sql = "update personas set moral = 2*moral, fuerza = cast(1.2*fuerza as unsigned) where profesion='Soldado' and id_civilizacion = ?";
        try(Connection conn=this.getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
            pstmt.setInt(1, idCivilizacion);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        sql = "update personas set produce = cast(1.2*produce as unsigned) where profesion='Granjero' and id_civilizacion = ?";
        try(Connection conn=this.getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
            pstmt.setInt(1, idCivilizacion);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void civilizacionDefensoraGana(int idCivilizacion, int idGuerra){
        String sql = " ";
        //Falta actualizar que ha defendido con exito

        sql = "update personas set produce = cast(1.4*produce as unsigned) where id_civilizacion = ?";
        try(Connection conn=this.getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
            pstmt.setInt(1, idCivilizacion);
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
