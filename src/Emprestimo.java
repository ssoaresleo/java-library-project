import java.util.Date;

public class Emprestimo {
    private static int contadorId = 1;
    private int id;
    private Date dataDoEmprestimo;
    private Date dataDaDevolucao;
    private Cliente cliente;
    private Livro livro;
    private boolean ativo;

    public Emprestimo(Livro livro, Cliente cliente) {
        this.id = contadorId ++;
        this.dataDoEmprestimo = new Date();
        this.dataDaDevolucao = new Date();
        this.cliente = cliente;
        this.livro = livro;
        this.ativo = true;
    }

    public int getId() {
        return id;
    }

    public Date getDataDoEmprestimo() {
        return dataDoEmprestimo;
    }

    public Cliente getClient() {
        return cliente;
    }

    public Livro getLivro() {
        return livro;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void devolver() {
        livro.setDisponivel(true);
        this.ativo = false;
        this.dataDaDevolucao = new Date();
    }

    @Override
    public String toString() {
        return "Emprestimo{\n" +
                "id=" + id + ",\n" +
                "dataDoEmprestimo=" + dataDoEmprestimo + ",\n" +
                "dataDaDevolucao=" + dataDaDevolucao + ",\n" +
                "nomeCliente='" + cliente.getNome() + "',\n" +
                "livro=" + livro.getTitulo() + ",\n" +
                "ativo=" + ativo + "\n" +
                '}';
    }
}
