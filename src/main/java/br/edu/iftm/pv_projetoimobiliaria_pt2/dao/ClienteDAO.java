package br.edu.iftm.pv_projetoimobiliaria_pt2.dao;

import br.edu.iftm.pv_projetoimobiliaria_pt2.model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public List<Cliente> listarTodos() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try (Connection conn = ConexaoPostgreSQL.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("telefone")
                );
                cliente.setId(rs.getInt("id"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }



    /**
     //Verifica se um CPF já está cadastrado no banco de dados.
     *
     * @param cpf O CPF a ser verificado.
     * @return true se o CPF já existe, false caso contrário.
     */
    public boolean existeClientePorCpf(String cpf) {
        String sql = "SELECT COUNT(*) FROM cliente WHERE cpf = ?";
        try (Connection conn = ConexaoPostgreSQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Retorna true se o CPF já existe
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Retorna false em caso de erro ou se o CPF não existe
    }

    /**
     * Insere um novo cliente no banco de dados.
     *
     * @param cliente O cliente a ser inserido.
     */
    public void inserir(Cliente cliente) {
        if (existeClientePorCpf(cliente.getCpf())) {
            throw new IllegalArgumentException("Erro: CPF já cadastrado!");
        }

        String sql = "INSERT INTO cliente (nome, cpf, telefone) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoPostgreSQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getTelefone());
            stmt.executeUpdate();

            // Recupera o ID gerado pelo banco de dados
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                cliente.setId(generatedKeys.getInt(1)); // Atualiza o ID do cliente
            }
            System.out.println("Cliente cadastrado com sucesso! ID: " + cliente.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}