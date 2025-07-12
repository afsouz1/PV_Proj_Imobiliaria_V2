package br.edu.iftm.pv_projetoimobiliaria_pt2.dao;

import br.edu.iftm.pv_projetoimobiliaria_pt2.model.Cliente;
import br.edu.iftm.pv_projetoimobiliaria_pt2.model.Contrato;
import br.edu.iftm.pv_projetoimobiliaria_pt2.model.Imovel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContratoDAO {
 
    /**
     * Finaliza um contrato, definindo a data de finalização e marcando como inativo.
     *
     * @param idContrato O ID do contrato a ser finalizado.
     * @param dataFinalizacao A data de finalização do contrato.
     */
    public void finalizarContrato(int idContrato, java.util.Date dataFinalizacao) {
        String sql = "UPDATE contrato SET data_finalizacao = ?, ativo = false WHERE id = ?";
        try (Connection conn = ConexaoPostgreSQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(dataFinalizacao.getTime())); // Data de finalização
            stmt.setInt(2, idContrato); // ID do contrato
            stmt.executeUpdate();
            System.out.println("Contrato finalizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retorna uma lista de todos os contratos cadastrados.
     *
     * @return Lista de contratos.
     */
    public List<Contrato> listarTodos() {
        List<Contrato> contratos = new ArrayList<>();
        String sql = "SELECT c.id, c.data_contrato, c.data_finalizacao, c.ativo, " +
                     "i.id AS imovel_id, i.endereco, i.preco, i.tipo, " +
                     "cli.id AS cliente_id, cli.nome, cli.cpf, cli.telefone " +
                     "FROM contrato c " +
                     "JOIN imovel i ON c.id_imovel = i.id " +
                     "JOIN cliente cli ON c.id_cliente = cli.id";
        try (Connection conn = ConexaoPostgreSQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // Cria o objeto Imovel
                Imovel imovel = new Imovel(
                    rs.getString("endereco"),
                    rs.getDouble("preco"),
                    rs.getString("tipo")
                );
                imovel.setId(rs.getInt("imovel_id"));

                // Cria o objeto Cliente
                Cliente cliente = new Cliente(
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("telefone")
                );
                cliente.setId(rs.getInt("cliente_id"));

                // Cria o objeto Contrato
                Contrato contrato = new Contrato(imovel, cliente, rs.getDate("data_contrato"));
                contrato.setId(rs.getInt("id"));
                contrato.setDataFinalizacao(rs.getDate("data_finalizacao"));
                contrato.setAtivo(rs.getBoolean("ativo"));

                contratos.add(contrato);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contratos;
    }

    /**
     * Finaliza um contrato no banco de dados.
     *
     * @param idContrato      ID do contrato a ser finalizado.
     * @param dataFinalizacao Data de finalização do contrato.
     */
    public void finalizarContrato(int idContrato, Date dataFinalizacao) {
        String sql = "UPDATE contrato SET data_finalizacao = ?, ativo = false WHERE id = ?";
        try (Connection conn = ConexaoPostgreSQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(dataFinalizacao.getTime())); // Data de finalização
            stmt.setInt(2, idContrato); // ID do contrato
            stmt.executeUpdate();
            System.out.println("Contrato finalizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inserir(Contrato contrato) {
        String sql = "INSERT INTO contrato (id_imovel, id_cliente, data_contrato, ativo) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoPostgreSQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, contrato.getImovel().getId()); // ID do imóvel
            stmt.setInt(2, contrato.getCliente().getId()); // ID do cliente
            stmt.setDate(3, new java.sql.Date(contrato.getDataContrato().getTime())); // Data do contrato
            stmt.setBoolean(4, contrato.isAtivo()); // Status do contrato
            stmt.executeUpdate();
            System.out.println("Contrato criado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}