package com.example.sosrota.Model.DAO;

import com.example.sosrota.Model.VO.BairroVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.sosrota.Model.DAO.ConexaoDAO.getConnection;

public class BairroDAO {
    public void create(BairroVO bairroVO) throws Exception {
        String sql = "INSERT INTO bairro (nome) VALUES (?)";

        Connection conn = ConexaoDAO.getConnection();
        PreparedStatement comandoSQL = conn.prepareStatement(sql);

        comandoSQL.setString(1, bairroVO.getNome());

        comandoSQL.execute();
        comandoSQL.close();
    }

    public BairroVO reader(int id) throws Exception {
        String sql = "INSERT INTO bairro (nome) VALUES (?)";

        Connection conn = ConexaoDAO.getConnection();
        PreparedStatement comandoSQL = conn.prepareStatement(sql);

        comandoSQL.setInt(1, id);

        ResultSet resultadoSQL = comandoSQL.executeQuery();

        BairroVO bairroVO = null;

        if (resultadoSQL.next()) {
            bairroVO = new BairroVO();
            bairroVO.setNome(resultadoSQL.getString("nome"));
        }

        comandoSQL.close();
        resultadoSQL.close();
        return bairroVO;
    }

    public List<BairroVO> readerAll() throws Exception {
        String sql = "INSERT INTO bairro (nome) VALUES (?)";

        Connection conn = ConexaoDAO.getConnection();
        PreparedStatement comandoSQL = conn.prepareStatement(sql);

        ResultSet resultadoSQL = comandoSQL.executeQuery();
        ArrayList<BairroVO> lista = new ArrayList<>();

        while (resultadoSQL.next()){
            BairroVO bairroVO = new BairroVO();
            bairroVO.setNome(resultadoSQL.getString("nome"));

            lista.add(bairroVO);
        }

        return lista;
    }
    public void update(BairroVO bairroVO) throws Exception {
        String sql = "INSERT INTO bairro (nome) VALUES (?)";

        Connection conn = ConexaoDAO.getConnection();
        PreparedStatement comandoSQL = conn.prepareStatement(sql);

        comandoSQL.setString(1, bairroVO.getNome());

        comandoSQL.execute();
        comandoSQL.close();
    }
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM bairro WHERE id=?";

        Connection conn = ConexaoDAO.getConnection();
        PreparedStatement comandoSQL = conn.prepareStatement(sql);

        comandoSQL.setInt(1, id);

        comandoSQL.execute();
        comandoSQL.close();
    }
}
