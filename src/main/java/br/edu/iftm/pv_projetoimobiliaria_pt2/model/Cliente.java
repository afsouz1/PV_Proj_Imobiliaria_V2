package br.edu.iftm.pv_projetoimobiliaria_pt2.model;

public class Cliente extends Pessoa {
    private int id;
    private String cpf;

    // Construtor
    public Cliente(String nome, String cpf, String telefone) {
        super(nome, telefone);
        this.cpf = cpf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return String.format(
            "\nCliente:\n" +
            "  ID: %d\n" +
            "  Nome: %s\n" +
            "  CPF: %s\n" +
            "  Telefone: %s",
            id, getNome(), cpf, getTelefone()
        );
    }

    
}