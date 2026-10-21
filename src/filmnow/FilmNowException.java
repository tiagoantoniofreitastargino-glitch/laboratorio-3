package filmnow;

/**
 * Exceção lançada em operações inválidas do sistema FilmNow.
 */
public class FilmNowException extends Exception {

    /**
     * Cria a exceção com uma mensagem.
     * @param mensagem Descrição do erro.
     */
    public FilmNowException(String mensagem) {
        super(mensagem);
    }
}
