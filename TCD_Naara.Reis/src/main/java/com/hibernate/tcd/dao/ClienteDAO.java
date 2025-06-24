package com.hibernate.tcd.dao;

import com.hibernate.tcd.model.Cliente;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
public class ClienteDAO {

    private static final SessionFactory sessionFactory;

    // Bloco estático para inicializar a SessionFactory uma única vez
    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Erro ao criar SessionFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Método cadastrar cliente
    public void cadastrar(Cliente cliente) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction(); // Inicia uma transação
            session.save(cliente); // Persiste o objeto Cliente no DB
            tx.commit(); // Confirma a transação
            System.out.println("Cliente cadastrado com sucesso!");
        } catch (Exception e) {
            if (tx != null) tx.rollback(); // Em caso de erro, desfaz a transação
            System.err.println("Erro ao cadastrar cliente: " + e.getMessage());
        }
    }

    // Método listar cliente
    public List<Cliente> listar() {
        try (Session session = sessionFactory.openSession()) {
            // Executa uma query HQL (Hibernate Query Language) para buscar todos os clientes
            return session.createQuery("FROM Cliente", Cliente.class).list();
        } catch (Exception e) {
            System.err.println("Erro ao listar clientes: " + e.getMessage());
            return null;
        }
    }

    // // Método atualizar cliente
    public void atualizar(int id, String novoNome, String novoEmail, String novoTelefone) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            Cliente cliente = session.get(Cliente.class, id);
            if (cliente != null) {
                tx = session.beginTransaction();
                cliente.setNome(novoNome);
                cliente.setEmail(novoEmail);
                cliente.setTelefone(novoTelefone);
                session.update(cliente); // Atualiza o objeto Cliente no DB
                tx.commit();
                System.out.println("Cliente atualizado com sucesso!");
            } else {
                System.out.println("Cliente com ID " + id + " não encontrado.");
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    // Método excluir cliente
    public void excluir(int id) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            Cliente cliente = session.get(Cliente.class, id);
            if (cliente != null) {
                tx = session.beginTransaction();
                session.delete(cliente); // Remove o objeto Cliente do DB
                tx.commit();
                System.out.println("Cliente excluído com sucesso!");
            } else  {
                System.out.println("Cliente com ID " + id + " não encontrado.");
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Erro ao excluir cliente: " + e.getMessage());
        }
    }

    // BUSCAR POR ID
    public Cliente buscarPorId(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Cliente.class, id);
        } catch (Exception e) {
            System.err.println("Erro ao buscar cliente: " + e.getMessage());
            return null;
        }
    }
}
