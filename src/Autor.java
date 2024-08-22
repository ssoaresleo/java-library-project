import java.util.Date;

public class Autor {
    private int id;
    private String name;
    private Date dataNascimento;

    public Autor(int id, String name, Date dataNascimento) {
        this.id = id;
        this.name = name;
        this.dataNascimento = dataNascimento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                ", name='" + name + '\'' +
                ", dataNascimento=" + dataNascimento +
                '}';
    }
}
