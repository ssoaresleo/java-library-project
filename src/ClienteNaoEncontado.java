public class ClienteNaoEncontado extends RuntimeException{
    public ClienteNaoEncontado (){
        super ("Não foi possivél encontrar o cliente.");
    }
}