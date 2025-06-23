package Main;

import dao.ClienteDAO;
import entidade.Cliente;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ClienteDAO dao = new ClienteDAO(); // Instancia o DAO para acessar o DB
        Scanner sc = new Scanner(System.in); // Para ler entrada do usuário
        int opcao;

        do {
            // Exibe o menu de opções
            System.out.println("\n----- MENU -----");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Listar Clientes");
            System.out.println("3 - Atualizar Cliente");
            System.out.println("4 - Excluir Cliente");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // Limpa buffer do Scanner

            switch (opcao) {
                case 1: // Cadastrar
                    Cliente novo = new Cliente();
                    System.out.print("Nome: ");
                    novo.setNome(sc.nextLine());
                    System.out.print("Email: ");
                    novo.setEmail(sc.nextLine());
                    System.out.print("Telefone: ");
                    novo.setTelefone(sc.nextLine());
                    dao.cadastrar(novo);
                    break;
                case 2: // Listar
                    List<Cliente> lista = dao.listar();
                    if (lista != null && !lista.isEmpty()) {
                        System.out.println("\n--- Clientes Cadastrados ---");
                        for (Cliente c : lista) {
                            System.out.println("ID: " + c.getId() +
                                    " | Nome: " + c.getNome() +
                                    " | Email: " + c.getEmail() +
                                    " | Telefone: " + c.getTelefone());
                        }
                    } else {
                        System.out.println("Nenhum cliente encontrado.");
                    }
                    break;
                case 3: // Atualizar
                    System.out.print("ID do cliente a atualizar: ");
                    int idAtualizar = sc.nextInt();
                    sc.nextLine();
                    Cliente existente = dao.buscarPorId(idAtualizar);
                    if (existente != null) {
                        System.out.print("Novo nome (" + existente.getNome() + "): ");
                        existente.setNome(sc.nextLine());
                        System.out.print("Novo email (" + existente.getEmail() + "): ");
                        existente.setEmail(sc.nextLine());
                        System.out.print("Novo telefone (" + existente.getTelefone() + "): ");
                        existente.setTelefone(sc.nextLine());
                        dao.atualizar(existente);
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
                    break;
                case 4: // Excluir
                    System.out.print("ID do cliente a excluir: ");
                    int idExcluir = sc.nextInt();
                    dao.excluir(idExcluir);
                    break;
                case 0: // Sair
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0); // O loop continua até que o usuário digite 0

        sc.close(); // Fecha o Scanner
    }
}

