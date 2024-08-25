package com.biblioteca.projeto.biblioteca;

import com.biblioteca.projeto.autor.Autor;
import com.biblioteca.projeto.autor.AutorNaoEncontradoException;
import com.biblioteca.projeto.cliente.Cliente;
import com.biblioteca.projeto.cliente.ClienteNaoEncontado;
import com.biblioteca.projeto.emprestimo.Emprestimo;
import com.biblioteca.projeto.livro.Livro;
import com.biblioteca.projeto.livro.LivroException;
import com.biblioteca.projeto.livro.LivrosNaoEncontrados;

import java.util.*;

public class Biblioteca {
    private List<Livro> livros = new ArrayList<>();
    private List<Autor> autores = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();

    private List<Cliente> clientes = new ArrayList<>();

    public void buscarLivros() {
        if(livros.isEmpty()) {
            System.out.println("Não a livros cadastrados.");
        }

        for (Livro livro : livros) {
            System.out.println("Livro: " + livro.getTitulo() + ", por: " + livro.getAutor().getNome() + ", cod: " + livro.getId());
        }
    }

    public Livro buscarLivroPorTitulo(String titulo) {
        for(Livro livro : livros) {
            if(livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro;
            }
        }

        return null;
    }

    public void buscarLivrosDisponiveis() throws LivrosNaoEncontrados {
        List<Livro> livrosDisponiveis = new ArrayList<>();

        for (Livro livro : livros) {
            if(livro.isDisponivel())
                livrosDisponiveis.add(livro);
        }

        if(livrosDisponiveis.isEmpty())
            throw new LivrosNaoEncontrados();

        for (Livro livro : livrosDisponiveis) {
            System.out.println("Livro: " + livro.getTitulo() + ", por: " + livro.getAutor().getNome() + ", cod: " + livro.getId());
        }
    }

    public Livro buscaLivroPorId(int id) throws LivroException {
        for(Livro livro : livros) {
            if(livro.getId() == id)
                return livro;
        }
        
       throw new LivroException("O livro com o código informado não é cadastrado.");
    }

    public Cliente buscarClientePorNome(String name) throws ClienteNaoEncontado {
        for (Cliente cliente : clientes) {
            if(cliente.getNome().equalsIgnoreCase(name)) {
                return cliente;
            }
        }

        throw new ClienteNaoEncontado();
    }

    public void buscarClientes() {
        for (Cliente cliente : clientes) {
            System.out.println("nome: " + cliente.getNome() + " email:" + " " + cliente.getEmail());
        }
    }

    public void cadastrarCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }

    public Autor buscarAutorPorNome(String name) throws AutorNaoEncontradoException {
        for (Autor autor : autores) {
            if(autor.getNome().equals(name)) {
                return autor;
            }
        }
        throw new AutorNaoEncontradoException();
    }

    public void buscarAutores() {
        if(autores.isEmpty())
            System.out.println("Não tem autores cadastrados na biblioteca");

        for (Autor autor : autores) {
            System.out.println("Autor " + "nome: " + autor.getNome());
        }
    }

    public void cadastrarAutores(Autor autor) {
        this.autores.add(autor);
    }

    public void cadastrarLivro(Livro livro) {
        this.livros.add(livro);
    }

    public void emprestarLivro(Livro livro, Cliente cliente) throws LivroException {
        if(livro.isDisponivel()) {
            Emprestimo emprestimo = new Emprestimo(livro, cliente);
            this.emprestimos.add(emprestimo);
            livro.setDisponivel(false);

            cliente.adicionarEmprestimo(emprestimo);
            livro.adicionarEmprestimo(emprestimo);
        } else {
            throw new LivroException("O Livro não está disponivél no momento.");
        }
    }

    @Override
    public String toString() {
        return "Biblioteca{" +
                "livros=" + livros +
                ", autores=" + autores +
                ", emprestimos=" + emprestimos +
                '}';
    }
}
