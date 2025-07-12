package br.edu.iftm.pv_projetoimobiliaria_pt2.model;
import java.util.Date;

public class Contrato {
    private int id;
    private Imovel imovel;
    private Cliente cliente;
    private Date dataContrato;
    private Date dataFinalizacao;
    private boolean ativo;

    // Construtor
    public Contrato(Imovel imovel, Cliente cliente, Date dataContrato) {
        this.imovel = imovel;
        this.cliente = cliente;
        this.dataContrato = dataContrato;
        this.ativo = true; // Por padrão, o contrato é ativo ao ser criado
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Imovel getImovel() {
        return imovel;
    }
    public void setImovel(Imovel imovel){
        this.imovel = imovel;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }

    public Date getDataContrato() {
        return dataContrato;
    }

    public void setDataContrato(Date dataContrato) {
        this.dataContrato = dataContrato;
    }

    public Date getDataFinalizacao() {
        return dataFinalizacao;
    }

    public void setDataFinalizacao(Date dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
public String toString() {
    String status = ativo ? "Ativo" : "Inativo";
    String dataFinalizacaoStr = (dataFinalizacao != null) 
        ? new java.text.SimpleDateFormat("yyyy-MM-dd").format(dataFinalizacao) 
        : "N/A";

    return String.format(
        "Contrato:\n" +
        "  ID: %d\n" +
        "  Imóvel: %s (ID: %d)\n" +
        "  Cliente: %s (ID: %d)\n" +
        "  Data do Contrato: %s\n" +
        "  Data de Finalização: %s\n" +
        "  Status: %s",
        id,
        imovel.getEndereco(), imovel.getId(),
        cliente.getNome(), cliente.getId(),
        new java.text.SimpleDateFormat("yyyy-MM-dd").format(dataContrato),
        dataFinalizacaoStr,
        status
    );
}

}