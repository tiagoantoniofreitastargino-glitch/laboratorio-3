package filmnow;

import java.util.Objects;

/**
 * Representa um filme do sistema
 */
public class Filme {

    private String nome;
    private String ano;
    private String local;

    /**
     * Cria um filme
     * @param nome Nome do filme.
     * @param ano Ano de lançamento.
     * @param local Local onde o filme pode ser assistido.
     */
    public Filme(String nome, String ano, String local) {
        this.nome = nome;
        this.ano = ano;
        this.local = local;
    }

    /**
     * Nome do filme
     * @return O nome
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Dois filmes iguais se mesmo nome e mesno ano
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Filme outro = (Filme) o;
        return Objects.equals(this.nome, outro.nome) && Objects.equals(this.ano, outro.ano);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.nome, this.ano);
    }

    /**
     * Representação textual "nome, ano, local".
     */
    @Override
    public String toString() {
        return this.nome + ", " + this.ano + ", " + this.local;
    }
}
