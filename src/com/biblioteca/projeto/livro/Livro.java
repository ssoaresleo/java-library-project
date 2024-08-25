package com.biblioteca.projeto.livro;

import com.biblioteca.projeto.autor.Autor;
import com.biblioteca.projeto.emprestimo.Emprestimo;

import java.util.*;

public class Livro {
    private static int contadorId = 1;
    private int id;
    private String titulo;
    private Autor autor;
    private boolean disponivel = true;
    private Date dataDeCadastro;
    private Date dataDeAtualizacao;

    private Map<Livro, List<Emprestimo>> historicoEmprestimos= new HashMap<>();

    public Livro(String titulo, Autor autor) {
        this.id = contadorId++;
        this.titulo = titulo;
        this.autor = autor;
        this.dataDeCadastro = new Date();
        this.dataDeAtualizacao = new Date();
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
            System.out.println("Nenhum empréstimo encontrado desse livro");
        } else {
            for (Emprestimo emprestimo : emprestimos) {
                System.out.println(emprestimo);
            }
        }
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
        this.dataDeAtualizacao = new Date();
    }

    public Autor getAutor() {
        return autor;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Date getDataDeCadastro() {
        return dataDeCadastro;
    }

    public Date getDataDeAtualizacao() {
        return dataDeAtualizacao;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor=" + autor +
                ", disponivel=" + disponivel +
                ", dataDeCadastro=" + dataDeCadastro +
                ", dataDeAtualizacao=" + dataDeAtualizacao +
                '}';
    }
}
