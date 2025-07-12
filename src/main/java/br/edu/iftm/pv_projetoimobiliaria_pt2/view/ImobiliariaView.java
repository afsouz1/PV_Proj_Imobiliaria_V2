package br.edu.iftm.pv_projetoimobiliaria_pt2.view;

import br.edu.iftm.pv_projetoimobiliaria_pt2.model.Imovel;
import br.edu.iftm.pv_projetoimobiliaria_pt2.model.Cliente;
import br.edu.iftm.pv_projetoimobiliaria_pt2.model.Contrato;
import br.edu.iftm.pv_projetoimobiliaria_pt2.controller.ImobiliariaController;
import br.edu.iftm.pv_projetoimobiliaria_pt2.util.LimpaTela;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ImobiliariaView {
    private ImobiliariaController controller = new ImobiliariaController();
    private Scanner in = new Scanner(System.in);

    public void iniciar() {
        boolean exec = true;
    
        do {
            LimpaTela.limpar();
            System.out.println("Bem-vindo ao Sistema de Gestão de Imobiliária!");
            System.out.println("Selecione a opção desejada:");
            System.out.println("(1) Cadastrar Imóvel;");
            System.out.println("(2) Cadastrar Cliente;");
            System.out.println("(3) Criar Contrato;");
            System.out.println("(4) Listar Imóveis;");
            System.out.println("(5) Listar Clientes;");
            System.out.println("(6) Listar Contratos Ativos;");
            System.out.println("(7) Finalizar Contrato;");
            System.out.println("(0) Sair.");
            System.out.print("Opção: ");
            int opt = in.nextInt();
            in.nextLine();
    
            switch (opt) {
                case 1:
                    cadastrarImovel();
                    break;
    
                case 2:
                    cadastrarCliente();
                    break;
    
                case 3:
                    criarContrato();
                    break;
    
                case 4:
                    listarImoveis();
                    break;
    
                case 5:
                    listarClientes();
                    break;
    
                case 6:
                    listarContratos();
                    break;
                
                case 7:
                    finalizarContrato();
                    break;
                case 0:
                    exec = false;
                    System.out.println("Saindo do sistema...");
                    break;
    
                default:
                    System.out.println("Erro! Opção inserida inválida.");
                    break;
            }
    
            if (exec) {
                System.out.println("\nPressione Enter para continuar...");
                in.nextLine();
            }
        } while (exec);
    
        in.close();
    }

    private void cadastrarImovel() {
        LimpaTela.limpar();
        System.out.println("Cadastro de Imóvel:");
        System.out.print("Endereço: ");
        String endereco = in.nextLine();
        System.out.print("Preço: ");
        double preco = in.nextDouble();
        in.nextLine(); // Consumir a nova linha após o nextDouble()
        System.out.print("Tipo: ");
        String tipo = in.nextLine();
        Imovel imovel = new Imovel(endereco, preco, tipo);
        controller.cadastrarImovel(imovel);
        System.out.println("Operação concluída com sucesso!");// <--  Corrigir para não imprimir em caso de erro
    }

    private void cadastrarCliente() {
        LimpaTela.limpar();
        System.out.println("Cadastro de Cliente:");
        System.out.print("Nome: ");
        String nome = in.nextLine();
        System.out.print("CPF: ");
        String cpf = in.nextLine();
        System.out.print("Telefone: ");
        String telefone = in.nextLine();

        Cliente cliente = new Cliente(nome, cpf, telefone);
        controller.cadastrarCliente(cliente);
        System.out.println("Operação concluída com sucesso!");
    }

    private void criarContrato() {
        LimpaTela.limpar();
        System.out.println("Criação de Contrato:");

        // Listar imóveis disponíveis
        List<Imovel> imoveis = controller.listarImoveis();
        if (imoveis.isEmpty()) {
            System.out.println("Erro: Nenhum imóvel cadastrado. Cadastre um imóvel primeiro.");
            return;
        }

        //Correr a lista e imprimir todos os imóveis disponíveis linha por linha
        System.out.println("Selecione um imóvel:");
        for (int i = 0; i < imoveis.size(); i++) {
            System.out.println((i + 1) + ". " + imoveis.get(i));
        }

        //Usuário seleciona qual imóvel será utilizado no contrato (não será selecionado pelo ID, sim, sequencia)
        System.out.print("Opção: ");
        int imovelIndex = in.nextInt() - 1;
        in.nextLine();

        if (imovelIndex < 0 || imovelIndex >= imoveis.size()) {
            System.out.println("Erro: Opção de imóvel inválida.");
            return;
        }
        Imovel imovelSelecionado = imoveis.get(imovelIndex);

        // Listar clientes disponíveis
        List<Cliente> clientes = controller.listarClientes();
        if (clientes.isEmpty()) {
            System.out.println("Erro: Nenhum cliente cadastrado. Cadastre um cliente primeiro.");
            return;
        }
        System.out.println("Selecione um cliente:");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i + 1) + ". " + clientes.get(i));
        }
        System.out.print("Opção: ");
        int clienteIndex = in.nextInt() - 1;
        in.nextLine();

        if (clienteIndex < 0 || clienteIndex >= clientes.size()) {
            System.out.println("Erro: Opção de cliente inválida.");
            return;
        }
        Cliente clienteSelecionado = clientes.get(clienteIndex);

        // Criar contrato
        Contrato contrato = new Contrato(imovelSelecionado, clienteSelecionado, new Date());
        controller.criarContrato(contrato);
        System.out.println("Operação concluída com sucesso!");
    }

    private void listarImoveis() {
        LimpaTela.limpar();
        System.out.println("Lista de Imóveis:");
        List<Imovel> imoveis = controller.listarImoveis();
        if (imoveis.isEmpty()) {
            System.out.println("Nenhum imóvel cadastrado.");
        } else {
            for (Imovel imovel : imoveis) {
                System.out.println(imovel);
            }
        }
    }

    private void listarClientes() {
        LimpaTela.limpar();
        System.out.println("Lista de Clientes:");
        List<Cliente> clientes = controller.listarClientes();
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        }
    }



    @SuppressWarnings("unused")
    private void listarContratos() {
        LimpaTela.limpar();
        System.out.println("Lista de Contratos:");
        List<Contrato> contratos = controller.listarContratos();
        if (contratos.isEmpty()) {
            System.out.println("Nenhum contrato cadastrado.");
        } else {
            for (Contrato contrato : contratos) {
                String status = contrato.isAtivo() ? "Ativo" : "Inativo";
                String dias = contrato.isAtivo() ? "Dias restantes: " + calcularDiasRestantes(contrato.getDataContrato())
                        : "Dias desde a finalização: " + calcularDiasPassados(contrato.getDataFinalizacao());
                System.out.println(contrato + " | " + dias);
            }
        }
    }

   
    private void finalizarContrato() {
        LimpaTela.limpar();
        System.out.println("Finalização de Contrato:");
    
        // Listar contratos ativos
        List<Contrato> contratos = controller.listarContratos().stream()
                .filter(Contrato::isAtivo)
                .toList();
        if (contratos.isEmpty()) {
            System.out.println("Nenhum contrato ativo para finalizar.");
            return;
        }
    
        System.out.println("Selecione um contrato para finalizar:");
        for (int i = 0; i < contratos.size(); i++) {
            System.out.println((i + 1) + ". " + contratos.get(i));
        }
        System.out.print("Opção: ");
        int contratoIndex = in.nextInt() - 1;
        in.nextLine();
    
        if (contratoIndex < 0 || contratoIndex >= contratos.size()) {
            System.out.println("Erro: Opção de contrato inválida.");
            return;
        }
    
        Contrato contratoSelecionado = contratos.get(contratoIndex);
    
        // Solicitar data de finalização
        System.out.print("Informe a data de finalização (yyyy-MM-dd): ");
        String dataFinalizacaoStr = in.nextLine();
        java.util.Date dataFinalizacao;
        try {
            dataFinalizacao = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(dataFinalizacaoStr);
        } catch (Exception e) {
            System.out.println("Erro: Data inválida.");
            return;
        }
    
        // Finalizar contrato
        controller.finalizarContrato(contratoSelecionado.getId(), dataFinalizacao);
        System.out.println("Contrato finalizado com sucesso!");
    }

    private long calcularDiasRestantes(Date dataInicio) {
        long diff = dataInicio.getTime() - new Date().getTime();
        return diff / (24 * 60 * 60 * 1000);
    }
    
    private long calcularDiasPassados(Date dataFinalizacao) {
        long diff = new Date().getTime() - dataFinalizacao.getTime();
        return diff / (24 * 60 * 60 * 1000);
    }

    
}