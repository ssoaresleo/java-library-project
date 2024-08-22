import java.util.*;

public class Biblioteca {
    private List<Livro> livros = new ArrayList<>();
    private List<Autor> autores = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();

    private List<Cliente> clientes = new ArrayList<>();

    private Map<Cliente, List<Emprestimo>> historicoEmprestimosPorCliente = new HashMap<>();
    private Map<Livro, List<Emprestimo>> historicoEmprestimosPorLivro = new HashMap<>();

    public List<Livro> getLivros() {
        return livros;
    }

    public Livro buscarLivroPorTitulo(String titulo) {
        for(Livro livro : livros) {
            if(livro.getTitulo().equals(titulo)) {
                return livro;
            }
        }

        return null;
    }

    public List<Livro> buscarLivrosDisponiveis() {
        List<Livro> livrosDisponiveis = new ArrayList<>();

        for (Livro livro :  livros) {
            if(livro.isDisponivel()) {
                livrosDisponiveis.add(livro);
            }
        }

        return livrosDisponiveis;
    }

    public Livro buscaLivroPorId(int id) {
        Optional<Livro> livro = this.livros.stream().filter(livros -> livros.getId() == id).findFirst();

        return livro.orElse(null);
    }

    public Cliente buscarClientePorNome(String name) {
        for (Cliente cliente :  clientes) {
            if(cliente.getName().equals(name)) {
                return cliente;
            }
        }

        return null;
    }

    public void setLivros(Livro livro) {
        this.livros.add(livro);
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public List<Cliente> getClients() {
        return clientes;
    }

    public void setAutores(Autor autor) {
        this.autores.add(autor);
    }

    public void setClientes(Cliente cliente) {
        this.clientes.add(cliente);
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public List<Emprestimo> getHistoricoEmprestimosPorCliente(Cliente cliente) {
        return historicoEmprestimosPorCliente.getOrDefault(cliente, new ArrayList<>());
    }

    public List<Emprestimo> getHistoricoEmprestimosPorLivro(Livro livro) {
        return historicoEmprestimosPorLivro.getOrDefault(livro, new ArrayList<>());
    }

    public void emprestar(Livro livro, String nomePessoa) {
        if(livro.isDisponivel()) {
            Cliente cliente = this.buscarClientePorNome(nomePessoa);

            Emprestimo emprestimo = new Emprestimo(livro, cliente);
            this.emprestimos.add(emprestimo);
            livro.setDisponivel(false);

            // Atualiza o histórico de empréstimos
            historicoEmprestimosPorCliente.computeIfAbsent(cliente, k -> new ArrayList<>()).add(emprestimo);
            historicoEmprestimosPorLivro.computeIfAbsent(livro, k -> new ArrayList<>()).add(emprestimo);
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
