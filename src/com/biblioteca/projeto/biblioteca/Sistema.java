package com.biblioteca.projeto.biblioteca;

import com.biblioteca.projeto.autor.Autor;
import com.biblioteca.projeto.autor.AutorNaoEncontradoException;
import com.biblioteca.projeto.cliente.Cliente;
import com.biblioteca.projeto.livro.Livro;
import com.biblioteca.projeto.livro.LivroException;
import com.biblioteca.projeto.livro.LivrosNaoEncontrados;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Sistema {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Bem-vindo(a) Ao Sistema de Livraria");
            System.out.println("Gostaria de ver os livros disponíveis hoje? (sim/não/sair)");

            String resposta = scanner.nextLine();

            // Listar livros
            if(resposta.equalsIgnoreCase("sim")) {
               try {
                   System.out.println("Esses são os livros disponiveis para emprestimos");
                   biblioteca.buscarLivrosDisponiveis();

               } catch (LivrosNaoEncontrados e) {
                   System.out.println(e.getMessage());
                   continue;
               }

                System.out.println("Deseja escolher algum desses para ler? (sim/nao)");
                String desejaLer = scanner.nextLine();

                // Novo emprestimo
                if(desejaLer.equalsIgnoreCase("sim")) {
                    System.out.println("Insira o código do livro que deseja levar");

                    int codigoDoLivro = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Boa escolha! Para continuar, Insira seu nome:");
                    String nomeCliente = scanner.nextLine().trim();

                    Cliente cliente = biblioteca.buscarClientePorNome(nomeCliente);

                   try {
                       Livro livro = biblioteca.buscaLivroPorId(codigoDoLivro);
                       biblioteca.emprestarLivro(livro, cliente);

                       System.out.println("Livro: " + livro.getTitulo() + ", cod: " + livro.getId() + " foi emprestado para: " + nomeCliente);
                   } catch (LivroException e) {
                       System.out.println(e.getMessage());
                   }
                }

            } else if (resposta.equals("nao") || resposta.equals("não")) {
                // inicia um loop(menu) com outro contexto
               while (true) {
                   System.out.println("Sistema de Livraria");
                   System.out.println("(clientes/cadastro/emprestimos/livros/autores/voltar)");

                   String escolha = scanner.nextLine();

                   if(escolha.equalsIgnoreCase("autores")) {
                       System.out.println("Lista de autores: ");
                       biblioteca.buscarAutores();

                   }
                   //Buscar emprestimos por cliente ou livro expecifico
                   else if(escolha.equalsIgnoreCase("emprestimos")) {
                       System.out.println("Como deseja listar os emprestimos? (livro/cliente)");
                       String buscarEmprestimosPorTipo = scanner.nextLine();

                       if(buscarEmprestimosPorTipo.equals("cliente")) {
                           System.out.println("Digite o nome do cliente: ");

                           String nomeCliente = scanner.nextLine();

                           Cliente cliente = biblioteca.buscarClientePorNome(nomeCliente);

                           cliente.exibirEmprestimos();
                       } else if(buscarEmprestimosPorTipo.equals("livro")) {
                           System.out.println("Digite o titulo do livro: ");

                           String tituloLivro = scanner.nextLine();

                           Livro livro = biblioteca.buscarLivroPorTitulo(tituloLivro);

                           livro.exibirEmprestimos();
                       }

                   } else if(escolha.equals("livros")) {
                       System.out.println("Como deseja listar os livros? (todos/nome)");
                       String buscarLivrosPor = scanner.nextLine();

                       if(buscarLivrosPor.equals("todos")) {
                           biblioteca.buscarLivros();

                       } else if(buscarLivrosPor.equals("nome")) {
                           System.out.println("Digite o titulo:");
                           String tituloLivro = scanner.nextLine();

                           System.out.println(biblioteca.buscarLivroPorTitulo(tituloLivro));
                       }
                   } else if(escolha.equals("clientes")) {
                       System.out.println("Lista de clientes: ");
                       biblioteca.buscarClientes();

                   } else if(escolha.equals("cadastro")) {
                       System.out.println("Qual tipo de cadastro? (livro/cliente/autor)");
                       String tipoCadastro = scanner.nextLine();

                       // Inicia um loop cadastro de livro
                       if(tipoCadastro.equals("livro")) {
                           while (true) {
                               System.out.println("Digite o titulo do livro:");
                               String titulo = scanner.nextLine();

                               System.out.println("Nome do autor:");
                               String nomeDoAutor = scanner.nextLine();

                               try {
                                   Autor autorDoLivro = biblioteca.buscarAutorPorNome(nomeDoAutor);

                                   Livro novoLivro = new Livro(titulo, autorDoLivro);
                                   biblioteca.cadastrarLivro(novoLivro);

                                   System.out.println("Livro cadastrado com sucesso!");

                                   break;
                               } catch (AutorNaoEncontradoException e) {
                                   System.out.println(e.getMessage());
                                   System.out.println("(Certifique-se que esse autor está cadastrado ou que tenha inserido o nome corretamente)");

                                   System.out.println("Lista dos autores cadastrados: ");
                                   biblioteca.buscarAutores();

                                   System.out.println("Deseja tentar novamente? (sim/nao)");

                                   String desejaContinuarCadastro = scanner.nextLine();

                                   if(!desejaContinuarCadastro.equalsIgnoreCase("sim")) {
                                       System.out.println("Cancelando cadastro de autor...");
                                       break;
                                   }
                               }
                           }
                       }

                       if(tipoCadastro.equals("cliente")) {
                           System.out.println("Para continuar insira seu nome: ");
                           String name = scanner.nextLine();

                           System.out.println("Insira sua data de nascimento: (12/04/2000)");
                           String dataDeNascimento = scanner.nextLine();

                           System.out.println("Insira seu e-mail");
                           String email = scanner.nextLine();

                           SimpleDateFormat dataFormato = new SimpleDateFormat("dd/MM/yyyy");

                           try {
                               Date dataFormatada = dataFormato.parse(dataDeNascimento);
                               Cliente novoCliente = new Cliente(name, dataFormatada, email);

                               biblioteca.cadastrarCliente(novoCliente);

                               System.out.println("Seja bem vindo " + novoCliente.getNome() + " Começe já escolhendo um livro!");

                           }catch (ParseException e) {
                               e.printStackTrace();
                           }
                       } else if (tipoCadastro.equals("autor")) {
                           System.out.println("Digite o nome do autor:");
                           String nome = scanner.nextLine();

                           System.out.println("Digite a data de nascimento: (12/04/2000)");
                           String dataDeNascimento = scanner.nextLine();

                           SimpleDateFormat dataFormato = new SimpleDateFormat("dd/MM/yyyy");

                           try {
                               Date dataFormatada = dataFormato.parse(dataDeNascimento);
                               Autor novoAutor = new Autor(nome, dataFormatada);

                               biblioteca.cadastrarAutores(novoAutor);

                               System.out.println("O Autor " + novoAutor.getNome() + " Foi cadastrado com sucesso!");
                           }catch (ParseException e) {
                               e.printStackTrace();
                           }
                       }
                   }
                   // Sair para o inicio
                   if(escolha.equalsIgnoreCase("voltar")) {
                       break;
                   }
               }
            } else if (resposta.equals("sair")) {
                System.out.println("Obrigado por utilizar nosso sitema de livraria! Volte sempre :D");

                // Fechando Loop
                break;
            } else {
                System.out.println("Resposta inválida");
            }
        }
        scanner.close();
    }
}