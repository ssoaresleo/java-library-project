import java.util.Date;

public class Cliente {
    private static int contadorId = 1;
    private int id;
    private String name;
    private Date dataDeNascimento;
    private String email;

    public Cliente(String name, Date dataDeNascimento, String email) {
        this.id = contadorId++;
        this.name = name;
        this.dataDeNascimento = dataDeNascimento;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public Date getDataDeNascimento() {
        return dataDeNascimento;
    }

    public String getEmail() {
        return email;
    }


}
