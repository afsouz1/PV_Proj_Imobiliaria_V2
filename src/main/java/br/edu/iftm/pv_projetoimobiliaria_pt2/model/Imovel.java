package br.edu.iftm.pv_projetoimobiliaria_pt2.model;

public class Imovel {
    private int id;
    private String endereco;
    private double preco;
    private String tipo;

    // Construtor
    public Imovel(String endereco, double preco, String tipo) {
        this.endereco = endereco;
        this.preco = preco;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "\nImóvel:\n" +
            "   ID: " + id + ",\n" +
            "   Endereço: " + endereco + ",\n" +
            "   Preço: R$ " + preco + ",\n" +
            "   Tipo: " + tipo + "\n";
    }
}