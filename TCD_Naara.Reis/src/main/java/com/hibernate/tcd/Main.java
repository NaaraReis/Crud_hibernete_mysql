package com.hibernate.tcd;

import com.hibernate.tcd.dao.ClienteDAO;
import com.hibernate.tcd.model.Cliente;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ClienteDAO dao = new ClienteDAO();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n----- MENU CLIENTES -----");
            System.out.println("1. Cadastrar cliente");
            System.out.println("2. Listar clientes");
            System.out.println("3. Atualizar cliente");
            System.out.println("4. Excluir cliente");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opcao: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // consumir linha

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Telefone: ");
                    String telefone = scanner.nextLine();

                    Cliente novoCliente = new Cliente();
                    novoCliente.setNome(nome);
                    novoCliente.setEmail(email);
                    novoCliente.setTelefone(telefone);
                    dao.cadastrar(novoCliente);
                    break;

                case 2:
                    List<Cliente> clientes = dao.listar();
                    if (clientes != null && !clientes.isEmpty()) {
                        for (Cliente c : clientes) {
                            System.out.println("ID: " + c.getId());
                            System.out.println("Nome: " + c.getNome());
                            System.out.println("Email: " + c.getEmail());
                            System.out.println("Telefone: " + c.getTelefone());
                            System.out.println("------------------------");
                        }
                    } else {
                        System.out.println("Nenhum cliente encontrado.");
                    }
                    break;

                case 3:
                    System.out.print("ID do cliente a atualizar: ");
                    int idAtualizar = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Novo nome: ");
                    String novoNome = scanner.nextLine();
                    System.out.print("Novo email: ");
                    String novoEmail = scanner.nextLine();
                    System.out.print("Novo telefone: ");
                    String novoTelefone = scanner.nextLine();
                    dao.atualizar(idAtualizar, novoNome, novoEmail, novoTelefone);
                    break;

                case 4:
                    System.out.print("ID do cliente a excluir: ");
                    int idExcluir = scanner.nextInt();
                    dao.excluir(idExcluir);
                    break;

                case 0:
                    System.out.println("Encerrando o programa...");
                    break;

                default:
                    System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);

        scanner.close();
    }
}


