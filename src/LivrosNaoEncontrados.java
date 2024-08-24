public class LivrosNaoEncontrados extends Exception{
    public LivrosNaoEncontrados (){
        super ("A Biblioteca não tem livros cadastrados.");
    }
}