package br.edu.iftm.pv_projetoimobiliaria_pt2.controller;

import br.edu.iftm.pv_projetoimobiliaria_pt2.dao.ClienteDAO;
import br.edu.iftm.pv_projetoimobiliaria_pt2.dao.ContratoDAO;
import br.edu.iftm.pv_projetoimobiliaria_pt2.dao.ImovelDAO;
import br.edu.iftm.pv_projetoimobiliaria_pt2.model.Imovel;
import br.edu.iftm.pv_projetoimobiliaria_pt2.model.Cliente;
import br.edu.iftm.pv_projetoimobiliaria_pt2.model.Contrato;
import java.util.Date;
import java.util.List;

public class ImobiliariaController {
    private ImovelDAO imovelDAO = new ImovelDAO();
    private ClienteDAO clienteDAO = new ClienteDAO();
    private ContratoDAO contratoDAO = new ContratoDAO();

    /**
     * Cadastra um imóvel no banco de dados.
     *
     * @param imovel O imóvel a ser cadastrado.
     */
    public void cadastrarImovel(Imovel imovel) {
        imovelDAO.inserir(imovel);
    }

    /**
     * Cadastra um cliente no banco de dados, verificando se o CPF já existe.
     *
     * @param cliente O cliente a ser cadastrado.
     */
    public void cadastrarCliente(Cliente cliente) {
        if (clienteDAO.existeClientePorCpf(cliente.getCpf())) {
            System.out.println("Erro: CPF já cadastrado!");
        } else {
            clienteDAO.inserir(cliente);
        }
    }

    /**
     * Cria um contrato no banco de dados.
     *
     * @param contrato O contrato a ser criado.
     */
    public void criarContrato(Contrato contrato) {
        contratoDAO.inserir(contrato);
    }

    /**
     * Retorna uma lista de todos os imóveis cadastrados.
     *
     * @return Lista de imóveis.
     */
    public List<Imovel> listarImoveis() {
        return imovelDAO.listarTodos();
    }

    /**
     * Retorna uma lista de todos os clientes cadastrados.
     *
     * @return Lista de clientes.
     */
    public List<Cliente> listarClientes() {
        return clienteDAO.listarTodos();
    }

    /**
     * Retorna uma lista de todos os contratos cadastrados.
     *
     * @return Lista de contratos.
     */
    public List<Contrato> listarContratos() {
        return contratoDAO.listarTodos();
    }

    /**
     * Finaliza um contrato, definindo a data de finalização e marcando como inativo.
     *
     * @param idContrato       ID do contrato a ser finalizado.
     * @param dataFinalizacao  Data de finalização do contrato.
     */
    public void finalizarContrato(int idContrato, Date dataFinalizacao) {
        contratoDAO.finalizarContrato(idContrato, dataFinalizacao);
    }
    

}