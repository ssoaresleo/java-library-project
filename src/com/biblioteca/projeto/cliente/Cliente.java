package com.biblioteca.projeto.cliente;

import com.biblioteca.projeto.emprestimo.Emprestimo;

import java.util.*;

public class Cliente {
    private static int contadorId = 1;
    private int id;

    private String nome;
    private Date dataDeNascimento;
    private String email;

    private Map<Cliente, List<Emprestimo>> historicoEmprestimos = new HashMap<>();

    public Cliente(String nome, Date dataDeNascimento, String email) {
        this.id = contadorId++;
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public Date getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void adicionarEmprestimo(Emprestimo emprestimo) {
        historicoEmprestimos.computeIfAbsent(this, k -> new ArrayList<>()).add(emprestimo);
    }

    public List<Emprestimo> buscarEmprestimos() {
        return historicoEmprestimos.computeIfAbsent(this, k -> new ArrayList<>());
    }
    public void exibirEmprestimos() {
        List<Emprestimo> emprestimos = buscarEmprestimos();

        if (emprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo encontrado para o cliente " + nome);
        } else {
            for (Emprestimo emprestimo : emprestimos) {
                System.out.println(emprestimo);
            }
        }
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return String.format("Cliente: %s, \nEmail: %s", nome, email);
    }
}
