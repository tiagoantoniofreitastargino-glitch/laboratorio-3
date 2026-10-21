package filmnow;

import java.util.ArrayList;
import java.util.List;

/**
 * Sistema que mantém os seus filmes prediletos. Podem existir 100 filmes.
 *
 * @author eliane
 *
 */
public class FilmNow {

    private static final int TAMANHO = 100;
    private static final int TAMANHO_HOT = 10;

    private Filme[] filmes; //uma representacao simploria da lista de filmes
    private Filme[] hotList; //os filmes em destaque

    /**
     * Cria o FilmNow.
     */
    public FilmNow() {
        this.filmes = new Filme[TAMANHO];
        this.hotList = new Filme[TAMANHO_HOT];
    }

    /**
     * Acessa a lista de filmes mantida.
     * @return O array de filmes.
     */
    public String[] getFilmes() {
        String[] nomes = new String[TAMANHO];
        for (int i = 0; i < TAMANHO; i++) {
            if (this.filmes[i] != null) {
                nomes[i] = this.filmes[i].getNome();
            }
        }
        return nomes;
    }

    /**
     * Acessa os dados de um filme específico.
     * @param posicao Posição do filme no sistema.
     * @return Dados do filme. Null se não há filme na posição.
     */
    public String getFilme(int posicao) {
        Filme filme = filmes[posicao];
        return filme == null ? null : filme.getNome();
    }

    /**
     * Adiciona um filme em uma posição. Se já existir filme na posição, sobrescreve o anterior.
     * @param posicao Posição do filme.
     * @param nome Nome do filme.
     * @param ano Ano de lançamento do filme.
     * @param local Local onde o filme pode ser assitido.
     */
    public void cadastraFilme(int posicao, String nome, String ano, String local) {
        this.filmes[posicao] = new Filme(nome, ano, local);
    }

    /**
     * Adiciona um filme em uma posição, validando os dados.
     * Se já existir filme na posição, sobrescreve o anterior.
     * @param posicao Posição do filme (0 a 99).
     * @param nome Nome do filme.
     * @param ano Ano de lançamento do filme.
     * @param local Local onde o filme pode ser assistido.
     * @throws FilmNowException Se a posição for inválida ou o nome for vazio.
     */
    public void adicionaFilme(int posicao, String nome, String ano, String local) throws FilmNowException {
        validaPosicao(posicao, TAMANHO, "Posição de filme inválida.");
        if (nome == null || nome.trim().isEmpty()) {
            throw new FilmNowException("Nome do filme não pode ser vazio.");
        }
        this.filmes[posicao] = new Filme(nome, ano, local);
    }

    /**
     * Lista os filmes cadastrados.
     * @return Uma lista com "posição - dados" de cada filme existente.
     */
    public List<String> listaFilmes() {
        return lista(this.filmes);
    }

    /**
     * Detalha um filme.
     * @param posicao Posição do filme.
     * @return Nome, ano e local do filme.
     * @throws FilmNowException Se a posição for inválida ou não houver filme nela.
     */
    public String detalhaFilme(int posicao) throws FilmNowException {
        validaPosicao(posicao, TAMANHO, "Posição de filme inválida.");
        if (this.filmes[posicao] == null) {
            throw new FilmNowException("Não há filme na posição " + posicao + ".");
        }
        return this.filmes[posicao].toString();
    }

    /**
     * Coloca um filme na hot list. Se já existir filme na posição da hot list, sobrescreve o anterior.
     * @param posicaoFilme Posição do filme no sistema.
     * @param posicaoHot Posição na hot list (0 a 9).
     * @throws FilmNowException Se alguma posição for inválida, não houver filme na posição
     *                          ou o filme já estiver na hot list.
     */
    public void atribuiHot(int posicaoFilme, int posicaoHot) throws FilmNowException {
        validaPosicao(posicaoFilme, TAMANHO, "Posição de filme inválida.");
        validaPosicao(posicaoHot, TAMANHO_HOT, "Posição da hot list inválida.");
        Filme filme = this.filmes[posicaoFilme];
        if (filme == null) {
            throw new FilmNowException("Não há filme na posição " + posicaoFilme + ".");
        }
        for (int i = 0; i < TAMANHO_HOT; i++) {
            if (i != posicaoHot && filme.equals(this.hotList[i])) {
                throw new FilmNowException("Filme já está na hot list.");
            }
        }
        this.hotList[posicaoHot] = filme;
    }

    /**
     * Remove um filme da hot list (o filme continua cadastrado no sistema).
     * @param posicaoHot Posição na hot list (0 a 9).
     * @throws FilmNowException Se a posição for inválida.
     */
    public void removeHot(int posicaoHot) throws FilmNowException {
        validaPosicao(posicaoHot, TAMANHO_HOT, "Posição da hot list inválida.");
        this.hotList[posicaoHot] = null;
    }

    /**
     * Lista a hot list.
     * @return Uma lista com "posição - dados" de cada filme na hot list.
     */
    public List<String> listaHotList() {
        return lista(this.hotList);
    }

    private List<String> lista(Filme[] origem) {
        List<String> resultado = new ArrayList<>();
        for (int i = 0; i < origem.length; i++) {
            if (origem[i] != null) {
                resultado.add(i + " - " + origem[i].toString());
            }
        }
        return resultado;
    }

    private void validaPosicao(int posicao, int limite, String mensagem) throws FilmNowException {
        if (posicao < 0 || posicao >= limite) {
            throw new FilmNowException(mensagem);
        }
    }

}
