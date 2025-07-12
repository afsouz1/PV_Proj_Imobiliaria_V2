package br.edu.iftm.pv_projetoimobiliaria_pt2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoPostgreSQL {
    private static final String URL = "jdbc:postgresql://localhost:5432/imobiliaria";
    private static final String USER = "postgres"; // Substitua pelo seu usuário do PostgreSQL
    private static final String PASSWORD = "Elen@2022"; // Substitua pela sua senha do PostgreSQL

    public static Connection getConnection() throws SQLException {
        try {
            // Carrega o driver JDBC do PostgreSQL
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Driver JDBC do PostgreSQL não encontrado!");
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}