package com.example.sosrota.Model.DAO;

import com.example.sosrota.Model.VO.ProfissionalVO;
import com.example.sosrota.Model.DAO.ConexaoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ProfissionalDAO {

    public void create(ProfissionalVO profissional) throws Exception{
        String sql = "INSERT INTO profissional (nome, contato, funcao, ativo) VALUES (?, ?, ?, ?)";

        Connection conn = ConexaoDAO.getConnection();
        PreparedStatement comandoSQL = conn.prepareStatement(sql);

        comandoSQL.setString(1, profissional.getNome());
        comandoSQL.setString(2, profissional.getContato());
        comandoSQL.setString(3, profissional.getFuncao());
        comandoSQL.setBoolean(4, profissional.getAtivo());

        comandoSQL.execute();
        comandoSQL.close();
    }

    public ProfissionalVO reader(int id)throws Exception {
        String sql = "SELECT * FROM profissional WHERE id = ?";

        Connection conn = ConexaoDAO.getConnection();
        PreparedStatement comandoSQL = conn.prepareStatement(sql);

        comandoSQL.setInt(1, id);

        ResultSet resultadoSQL = comandoSQL.executeQuery();
        ProfissionalVO profissional = null;

        if (resultadoSQL.next()) {
            profissional = new ProfissionalVO();
            profissional.setId(resultadoSQL.getInt("id"));
            profissional.setNome(resultadoSQL.getString("nome"));
            profissional.setContato(resultadoSQL.getString("contato"));
            profissional.setFuncao(resultadoSQL.getString("funcao"));
            profissional.setAtivo(resultadoSQL.getBoolean("ativo"));
        }

        resultadoSQL.close();
        comandoSQL.close();
        return profissional;
    }
    public List<ProfissionalVO> readerAll() throws Exception {
        String sql = "SELECT * FROM profissional";

        Connection conn = ConexaoDAO.getConnection();
        PreparedStatement comandoSQL = conn.prepareStatement(sql);

        ResultSet resultadoSQL = comandoSQL.executeQuery();
        List<ProfissionalVO> lista = new ArrayList<>();

        while (resultadoSQL.next()) {
            ProfissionalVO profissionalVO = new ProfissionalVO();
            profissionalVO.setId(resultadoSQL.getInt("id"));
            profissionalVO.setNome(resultadoSQL.getString("nome"));
            profissionalVO.setContato(resultadoSQL.getString("contato"));
            profissionalVO.setFuncao(resultadoSQL.getString("funcao"));
            profissionalVO.setAtivo(resultadoSQL.getBoolean("ativo"));

            lista.add(profissionalVO);
        }

        resultadoSQL.close();
        comandoSQL.close();
        return lista;
    }

    public void update(ProfissionalVO profissional) throws Exception {
        String sql = "UPDATE profissional SET nome=?, contato=?, funcao=?, ativo=? WHERE id=?";

        Connection conn = ConexaoDAO.getConnection();
        PreparedStatement comandoSQL = conn.prepareStatement(sql);

        comandoSQL.setString(1, profissional.getNome());
        comandoSQL.setString(2, profissional.getContato());
        comandoSQL.setString(3, profissional.getFuncao());
        comandoSQL.setBoolean(4, profissional.getAtivo());
        comandoSQL.setInt(5, profissional.getId());

        comandoSQL.execute();
        comandoSQL.close();
    }

    public void delete(int id) throws Exception {
        String sql = "DELETE FROM profissional WHERE id=?";

        Connection conn = ConexaoDAO.getConnection();
        PreparedStatement comandoSQL = conn.prepareStatement(sql);

        comandoSQL.setInt(1, id);

        comandoSQL.execute();
        comandoSQL.close();
    }


}
