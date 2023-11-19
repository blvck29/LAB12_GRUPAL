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

    public ArrayList<Guerra> listarGuerras(int idCivilizacion){

        ArrayList<Guerra> lista= new ArrayList<>();
        CivilizacionDao civilizacionDao = new CivilizacionDao();

        String sql = "select * from guerra where id_civilizacion_atacante = ? or id_civilizacion_defensora = ?;";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,idCivilizacion); // atacamos
            pstmt.setInt(2,idCivilizacion); // fuimos atacados

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

    public int calcularGuerrasGanadas(Civilizacion civilizacion){

        ArrayList<Guerra> lista = listarGuerras(civilizacion.getIdCivilizacion());
        int guerrasGanadas = 0;

        if(!(lista.isEmpty())){
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
        }

        return guerrasGanadas;
    }
    public int calcularGuerrasPerdidas(Civilizacion civilizacion){

        ArrayList<Guerra> lista = listarGuerras(civilizacion.getIdCivilizacion());
        int guerrasPerdidas = 0;

        if(!(lista.isEmpty())){
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
        }

        return guerrasPerdidas;
    }


    public double obtenerWinRate(Civilizacion civilizacion){

        double winRate = 0;

        double perdidas = calcularGuerrasPerdidas(civilizacion);
        double ganadas = calcularGuerrasGanadas(civilizacion);

        if(perdidas != 0 && ganadas != 0){
            winRate = (ganadas/(ganadas+perdidas))*100;
        }

        return winRate;
    }


    public void civilizacionAtacanteGana(int idCivilizacion){//No se si se utiliza
        String sql = " ";
        sql = "update personas set moral = 2*moral, fuerza = cast(1.2*fuerza as unsigned) where profesion='soldado' and id_civilizacion = ?";
        try(Connection conn=this.getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
            pstmt.setInt(1, idCivilizacion);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void calcularGanador(int idCivilizacionAtacante, int idCivilizacionDefensora){//

        CivilizacionDao civilizacionDao = new CivilizacionDao();
        Civilizacion civilizacionAtacante = civilizacionDao.obtenerCivilizacion(idCivilizacionAtacante);
        Civilizacion civilizacionDefensora = civilizacionDao.obtenerCivilizacion(idCivilizacionDefensora);


        String sql = "insert into guera (id_civilizacion_atacante,id_civilizacion_defensora,estado_guerra,dia_atacante,dia_defensora) values (?,?,?,?,?)";

        try(Connection conn=this.getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){

            //Cálculo:
            pstmt.setInt(1,idCivilizacionAtacante);
            pstmt.setInt(2,idCivilizacionDefensora);
            pstmt.setInt(4,civilizacionAtacante.getDaysElapsed());
            civilizacionDao.actualizarTimeAndDaysElapsed(civilizacionAtacante.getIdCivilizacion()); //proceso de pasar un día para la civilización atacante
            pstmt.setInt(5,civilizacionDefensora.getDaysElapsed());

            //OBTENER GANADOR:

            if(civilizacionDao.fuerzaTotalAtacante(idCivilizacionAtacante)>civilizacionDao.fuerzaTotalDefensor(idCivilizacionDefensora)){
                pstmt.setString(4,"VA");
            }
            else{
                pstmt.setString(4,"VD");
            }

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
