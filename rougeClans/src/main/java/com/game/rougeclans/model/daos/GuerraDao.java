package com.game.rougeclans.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GuerraDao extends DaoBase {
    public ArrayList<String> obtenerResultadoYOponentePorGuerra(int idGuerra){
        ArrayList<String> listaResultado = new ArrayList<>();
        String sql = "";
        try(Connection conn=this.getConnection(); PreparedStatement pstmt=conn.prepareStatement(sql)){
            pstmt.setInt(1,idGuerra);
            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    listaResultado.add(rs.getString(1));
                    listaResultado.add(rs.getString(2));

                }
                return listaResultado;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public String resultadoGuerra(int idGuerra){
        String resultado = "";
        String sql = " ";
        try(Connection conn=this.getConnection(); PreparedStatement pstmt=conn.prepareStatement(sql)){
            pstmt.setInt(1,idGuerra);
            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){


                }
                return resultado;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
