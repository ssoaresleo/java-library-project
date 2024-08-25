package com.biblioteca.projeto.autor;

import java.util.Date;

public class Autor {
    private static int contadorId = 1;
    private int id;
    private String nome;
    private Date dataNascimento;

    public Autor(String name, Date dataNascimento) {
        this.id = contadorId++;
        this.nome = name;
        this.dataNascimento = dataNascimento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", name='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                '}';
    }
}
