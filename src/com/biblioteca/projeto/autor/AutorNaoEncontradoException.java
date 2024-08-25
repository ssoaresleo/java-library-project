package com.biblioteca.projeto.autor;

public class AutorNaoEncontradoException extends Exception{
    public AutorNaoEncontradoException(){
        super ("Autor não encontrado");
    }
}
