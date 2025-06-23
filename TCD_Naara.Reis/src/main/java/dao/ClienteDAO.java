package dao;

import entidade.Cliente;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class ClienteDAO {
    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    // Método para realizar cadastro
    public void cadastrar(Cliente cliente) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction(); // Inicia uma transação
            session.save(cliente);           // Persiste o objeto Cliente no DB
            tx.commit();                     // Confirma a transação
            System.out.println("Cliente cadastrado com sucesso!");
        } catch (Exception e) {
            if (tx != null) tx.rollback(); // Em caso de erro, desfaz a transação
            System.err.println("Erro ao cadastrar cliente: " + e.getMessage());
        }
    }
    // método para listar os clientes
    public List<Cliente> listar() {
        try (Session session = sessionFactory.openSession()) {
            // Executa uma query HQL (Hibernate Query Language) para buscar todos os clientes
            return session.createQuery("from Cliente", Cliente.class).list();
        } catch (Exception e) {
            System.err.println("Erro ao listar clientes: " + e.getMessage());
            return null;
        }
    }
    // Método para atualizar cliente
    public void atualizar(Cliente cliente) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.update(cliente); // Atualiza o objeto Cliente no DB
            tx.commit();
            System.out.println("Cliente atualizado com sucesso!");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Erro ao atualizar cliente: " + e.getMessage());
        }
    }
    // Médoto para excluir cliente
    public void excluir(int id) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            Cliente cliente = session.get(Cliente.class, id); // Busca o cliente pelo ID
            if (cliente != null) {
                tx = session.beginTransaction();
                session.delete(cliente);     // Remove o objeto Cliente do DB
                tx.commit();
                System.out.println("Cliente excluído com sucesso!");
            } else {
                System.out.println("Cliente não encontrado.");
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Erro ao excluir cliente: " + e.getMessage());
        }
    }
}

