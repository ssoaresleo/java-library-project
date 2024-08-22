import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Autor autor1 = new Autor(1, "João Guilherme", new Date());
        Autor autor2 = new Autor(2, "Maria rocha", new Date());
        Autor autor3 = new Autor(3, "Pedro Martins", new Date());


        Livro livro1 = new Livro(1, "Clean Code", autor1);
        Livro livro2 = new Livro(2, "Entendendo o Java", autor2);
        Livro livro3 = new Livro(3, "Escopo de funções", autor3);

        Cliente cliente1 = new Cliente("Leonardo Henrique", new Date(), "ssoares.leo@gmail.com");
        Cliente cliente2 = new Cliente("Lucas Winicius", new Date(), "winicius.lucas@gmail.com");

        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);

        biblioteca.setAutores(autor1);
        biblioteca.setAutores(autor2);
        biblioteca.setAutores(autor3);

        biblioteca.setLivros(livro1);
        biblioteca.setLivros(livro2);
        biblioteca.setLivros(livro3);

        biblioteca.setClientes(cliente1);
        biblioteca.setClientes(cliente2);

        biblioteca.emprestar(livro1, "Leonardo Henrique");

        while (true) {
            System.out.println("Bem-vindo(a) Ao Sistema de Livraria");
            System.out.println("Gostaria de ver os livros disponíveis hoje? (sim/não/sair)");

            String resposta = scanner.nextLine();
            resposta = resposta.trim().toLowerCase();

            if(resposta.equals("sim")) {
                List<Livro> livrosDisponiveis = biblioteca.buscarLivrosDisponiveis();

                if(livrosDisponiveis.isEmpty()) {
                    System.out.println("Não foi possivél encontrar livros...");
                } else {
                    for (Livro livro : livrosDisponiveis) {
                        System.out.println("Livro: " + livro.getTitulo() + ", por: " + livro.getAutor().getName() + ", cod: " + livro.getId());
                    }
                    System.out.println("Deseja escolher algum para ler? (sim/nao)");

                    String desejaLer = scanner.nextLine().trim().toLowerCase();

                    if(desejaLer.equals("sim")) {
                        System.out.println("Insira o código do livro que deseja levar");

                        int codLivro = scanner.nextInt();
                        // Limpa o buffer
                        scanner.nextLine();

                        Livro livro = biblioteca.buscaLivroPorId(codLivro);

                        if(livro == null || !livro.isDisponivel()) {
                            System.out.println("Não foi possivél encontrar este livro, ou não está disponível no momento.");
                        } else {
                            System.out.println("Boa escolha! " + livro.getTitulo() + " Foi escolhido por você!");
                            System.out.println("Qual seu nome?");

                            String nomePessoa = scanner.nextLine().trim();

                            Cliente clienteExiste = biblioteca.buscarClientePorNome(nomePessoa);

                            if(clienteExiste == null) {
                                System.out.println("Hmm vi aqui que você não tem um cadastro na nossa biblioteca, caso queira se registrar volte novamente e escolha a opção de cadastro!");
                            } else {
                                biblioteca.emprestar(livro, nomePessoa);

                                System.out.println("O Livro foi emprestado com sucesso para: " + nomePessoa);
                            }
                        }
                    }
                }
            } else if (resposta.equals("nao") || resposta.equals("não")) {
                System.out.println("Ok, o que deseja hoje?");
                System.out.println("(clientes/cadastro/emprestimos/buscar livros)");

                String escolha = scanner.nextLine();
                escolha = escolha.toLowerCase().trim();

                if(escolha.equals("emprestimos")) {
                    System.out.println("Como deseja listar os emprestimos? (livro/cliente)");

                    String tipoDaBusca = scanner.nextLine();


                    if(tipoDaBusca.equals("cliente")) {
                        System.out.println("Digite o nome do cliente: ");

                        String nomeCliente = scanner.nextLine();

                        Cliente cliente = biblioteca.buscarClientePorNome(nomeCliente);

                        if(cliente == null) {
                            System.out.println("Não foi possivél encontrar esse livro.");

                            return;
                        }

                        List<Emprestimo> emprestimos = biblioteca.getHistoricoEmprestimosPorCliente(cliente);

                        if(emprestimos.isEmpty()) {
                            System.out.println("Este cliente não tem nenhum emprestimo");
                        } else {
                            System.out.println(emprestimos);
                        }
                    }

                    if(tipoDaBusca.equals("livro")) {
                        System.out.println("Digite o titulo do livro: ");

                        String tituloLivro = scanner.nextLine();

                        Livro livro = biblioteca.buscarLivroPorTitulo(tituloLivro);
                        if(livro == null) {
                            System.out.println("Não foi possivél encontrar esse livro.");

                            return;
                        }

                        List<Emprestimo> emprestimos = biblioteca.getHistoricoEmprestimosPorLivro(livro);

                        if(emprestimos.isEmpty()) {
                            System.out.println("Este livro não tem nenhum emprestimo");
                        } else {
                            System.out.println(emprestimos);
                        }
                    }

                }

                if(escolha.equals("buscar livros") || escolha.equals("buscar")) {
                    System.out.println("Como deseja listar os livros? (todos/nome)");

                    String tipoDaBusca = scanner.nextLine();

                    List<Livro> livros = biblioteca.getLivros();
                    if(tipoDaBusca.equals("todos")) {


                        for (Livro livro : livros) {
                            System.out.println("Livro: " + livro.getTitulo() + ", por: " + livro.getAutor().getName() + ", cod: " + livro.getId());
                        }
                    }

                    if(tipoDaBusca.equals("nome")) {
                        System.out.println("Digite o titulo:");
                        String tituloLivro = scanner.nextLine();

                        Livro livroEncontrado = livros.stream().filter(l -> l.getTitulo().contains(tituloLivro)).findFirst().orElse(null);

                        if(livroEncontrado != null) {
                            System.out.println("Livro: " + livroEncontrado.getTitulo() + ", por: " + livroEncontrado.getAutor().getName() + ", cod: " + livroEncontrado.getId());
                        } else {
                            System.out.println("Não foi possivél encontrar esse livro.");
                        }
                    }
                }

                if(escolha.equals("clientes")) {
                    System.out.println("Lista de clientes: ");

                    List<Cliente> clientes = biblioteca.getClients();


                    for(Cliente cliente : clientes) {
                        System.out.println("nome: " + cliente.getName() + " email:" + " " + cliente.getEmail());
                    }
                }

                if(escolha.equals("cadastro")) {
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

                        biblioteca.setClientes(novoCliente);

                        System.out.println("Seja bem vindo " + novoCliente.getName() + " Começe já escolhendo um livro!");

                    }catch (ParseException e) {
                        e.printStackTrace();
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