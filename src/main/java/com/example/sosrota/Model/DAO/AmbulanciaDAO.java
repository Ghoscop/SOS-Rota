package com.example.sosrota.Model.DAO;

import com.example.sosrota.Model.VO.AmbulanciaVO;
import com.example.sosrota.Model.VO.ProfissionalVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AmbulanciaDAO {

    public void create(AmbulanciaVO ambulanciaVO) throws Exception{
        String sql = "INSERT INTO profissional (placa, tipo, status) VALUES (?, ?, ?)";

        Connection conn = ConexaoDAO.getConnection();
        PreparedStatement comandoSQL = conn.prepareStatement(sql);

        comandoSQL.setString(1, ambulanciaVO.getPlaca());
        comandoSQL.setString(2, ambulanciaVO.getTipo());
        comandoSQL.setBoolean(3, ambulanciaVO.getStatus());

        comandoSQL.execute();
        comandoSQL.close();
    }

    public AmbulanciaVO reader(int id) throws Exception{
        String sql = "SELECT * FROM ambulancia WHERE id = ?";

        Connection conn = ConexaoDAO.getConnection();
        PreparedStatement comandoSQL = conn.prepareStatement(sql);

        comandoSQL.setInt(1, id);

        ResultSet resultadoSQL = comandoSQL.executeQuery();
        AmbulanciaVO ambulanciaVO = null;

        if (resultadoSQL.next()) {
            ambulanciaVO = new AmbulanciaVO();
            ambulanciaVO.setId(resultadoSQL.getInt("id"));
            ambulanciaVO.setPlaca(resultadoSQL.getString("placa"));
            ambulanciaVO.setTipo(resultadoSQL.getString("tipo"));
            ambulanciaVO.setStatus(resultadoSQL.getBoolean("status"));
        }

        resultadoSQL.close();
        comandoSQL.close();

        return ambulanciaVO;
    }
    public List<AmbulanciaVO> readerAll() throws Exception {
        String sql = "SELECT * FROM ambulancia";

        Connection conn = ConexaoDAO.getConnection();
        PreparedStatement comandoSQL = conn.prepareStatement(sql);

        ResultSet resultadoSQL = comandoSQL.executeQuery();
        List<AmbulanciaVO> lista = new ArrayList<>();

        while (resultadoSQL.next()) {
            AmbulanciaVO ambulanciaVO = new AmbulanciaVO();
            ambulanciaVO.setId(resultadoSQL.getInt("id"));
            ambulanciaVO.setPlaca(resultadoSQL.getString("placa"));
            ambulanciaVO.setTipo(resultadoSQL.getString("tipo"));
            ambulanciaVO.setStatus(resultadoSQL.getBoolean("status"));

            lista.add(ambulanciaVO);
        }

        resultadoSQL.close();
        comandoSQL.close();
        return lista;
    }
    public void update(AmbulanciaVO ambulanciaVO) throws Exception {
        String sql = "UPDATE ambulancia SET placa=?, tipo=?, status=?";

        Connection conn = ConexaoDAO.getConnection();
        PreparedStatement comandoSQL = conn.prepareStatement(sql);

        comandoSQL.setString(1, ambulanciaVO.getPlaca());
        comandoSQL.setString(2, ambulanciaVO.getTipo());
        comandoSQL.setBoolean(3, ambulanciaVO.getStatus());

        comandoSQL.execute();
        comandoSQL.close();
    }

    public void delete(int id) throws Exception {
        String sql = "DELETE FROM ambulancia WHERE id=?";

        Connection conn = ConexaoDAO.getConnection();
        PreparedStatement comandoSQL = conn.prepareStatement(sql);

        comandoSQL.setInt(1, id);

        comandoSQL.execute();
        comandoSQL.close();
    }

}
