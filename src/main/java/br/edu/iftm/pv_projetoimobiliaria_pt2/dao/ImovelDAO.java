package br.edu.iftm.pv_projetoimobiliaria_pt2.dao;

import br.edu.iftm.pv_projetoimobiliaria_pt2.model.Imovel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImovelDAO {


    public List<Imovel> listarTodos() {
        List<Imovel> imoveis = new ArrayList<>();
        String sql = "SELECT * FROM imovel";
        try (Connection conn = ConexaoPostgreSQL.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Imovel imovel = new Imovel(
                    rs.getString("endereco"),
                    rs.getDouble("preco"),
                    rs.getString("tipo")
                );
                imovel.setId(rs.getInt("id"));
                imoveis.add(imovel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return imoveis;
    }
    
    public void inserir(Imovel imovel) {
        String sql = "INSERT INTO imovel (endereco, preco, tipo) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoPostgreSQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, imovel.getEndereco());
            stmt.setDouble(2, imovel.getPreco());
            stmt.setString(3, imovel.getTipo());
            stmt.executeUpdate();

            // Recupera o ID gerado pelo banco de dados
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                imovel.setId(generatedKeys.getInt(1)); // Atualiza o ID do imóvel
            }
            System.out.println("Imóvel cadastrado com sucesso! ID: " + imovel.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}