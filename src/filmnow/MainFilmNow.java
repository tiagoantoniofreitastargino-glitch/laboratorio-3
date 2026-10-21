package filmnow;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Interface com menus texto para manipular o sistema FilmNow.
 *
 * @author eliane
 *
 */
public class MainFilmNow {

    public static void main(String[] args) {
        FilmNow fn = new FilmNow();

        System.out.println("Carregando filmes ...");
        try {
            /*
             * Essa é a maneira de lidar com possíveis erros por falta do arquivo.
             */
            carregaFilmes("filmes_inicial.csv", fn);
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro lendo arquivo: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        String escolha = "";
        while (true) {
            escolha = menu(scanner);
            try {
                comando(escolha, fn, scanner);
            } catch (FilmNowException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    /**
     * Exibe o menu e captura a escolha do/a usuário/a.
     *
     * @param scanner Para captura da opção do usuário/a.
     * @return O comando escolhido.
     */

    private static String menu(Scanner scanner) {
        exibeMenu();
        return scanner.next().toUpperCase();
    }

    /**
     * Exibe o menu de opções.
     */
    static void exibeMenu() {
        System.out.println(
                "\n---\nMENU\n" +
                        "(A)Adicionar filme\n" +
                        "(M)Mostrar todos\n" +
                        "(D)Detalhar filme\n" +
                        "(H)Atribuir filme à hot list\n" +
                        "(R)Remover filme da hot list\n" +
                        "(L)Listar hot list\n" +
                        "(S)air\n" +
                        "\n" +
                        "Opção> ");
    }

    /**
     * Interpreta a opção escolhida por quem está usando o sistema.
     *
     * @param opcao   Opção digitada.
     * @param fn  O sistema FilmNow que estamos manipulando.
     * @param scanner Objeto scanner para o caso do comando precisar de mais input.
     */
    private static void comando(String opcao, FilmNow fn, Scanner scanner) throws FilmNowException {
        switch (opcao) {
            case "A":
                adicionaFilme(fn, scanner);
                break;
            case "M":
                mostrarFilmes(fn);
                break;
            case "D":
                detalharFilme(fn, scanner);
                break;
            case "H":
                atribuiHot(fn, scanner);
                break;
            case "R":
                removeHot(fn, scanner);
                break;
            case "L":
                mostrarHotList(fn);
                break;
            case "S":
                sai();
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    /**
     * Imprime lista de filmes.
     *
     * @param fn O sistema FilmNow a ser manipulado.
     */
    private static void mostrarFilmes(FilmNow fn) {

        String[] filmes = fn.getFilmes();
        for (int i = 0; i < filmes.length; i++) {
            if (filmes[i] != null) {
                System.out.println(formataFilme(i, filmes[i]));
            }
        }
    }

    /**
     * Imprime os detalhes de um dos filmes.
     *
     * @param fn O sistema FilmNow a ser manipulado.
     * @param scanner Scanner para capturar qual contato.
     */
    private static void detalharFilme(FilmNow fn, Scanner scanner) throws FilmNowException {
        System.out.print("\nQual filme> ");
        int posicao = scanner.nextInt();
        System.out.println("Dados do filme:\n" + fn.detalhaFilme(posicao));
    }

    /**
     * Coloca um filme na hot list.
     *
     * @param fn O sistema FilmNow a ser manipulado.
     * @param scanner Scanner para capturar as posições.
     */
    private static void atribuiHot(FilmNow fn, Scanner scanner) throws FilmNowException {
        System.out.print("\nPosição do filme> ");
        int posicaoFilme = scanner.nextInt();
        System.out.print("\nPosição na hot list> ");
        int posicaoHot = scanner.nextInt();
        fn.atribuiHot(posicaoFilme, posicaoHot);
    }

    /**
     * Remove um filme da hot list.
     *
     * @param fn O sistema FilmNow a ser manipulado.
     * @param scanner Scanner para capturar a posição.
     */
    private static void removeHot(FilmNow fn, Scanner scanner) throws FilmNowException {
        System.out.print("\nPosição na hot list> ");
        fn.removeHot(scanner.nextInt());
    }

    /**
     * Imprime a hot list.
     *
     * @param fn O sistema FilmNow a ser manipulado.
     */
    private static void mostrarHotList(FilmNow fn) {
        List<String> hot = fn.listaHotList();
        if (hot.isEmpty()) {
            System.out.println("Hot list vazia.");
        }
        for (String linha : hot) {
            System.out.println(linha);
        }
    }

    /**
     * Formata um filme para impressão.
     *
     * @param posicao A posição do filme (que é exibida)/
     * @param filme O filme a ser impresso.
     * @return A String formatada.
     */
    private static String formataFilme(int posicao, String filme) {
        return posicao + " - " + filme;
    }

    /**
     * Cadastra um filme no sistema.
     *
     * @param fn O sistema FilmNow a ser manipulado.
     * @param scanner Scanner para pedir informações do contato.
     */
    private static void adicionaFilme(FilmNow fn, Scanner scanner) throws FilmNowException {
        System.out.print("\nPosição no sistema> ");
        int posicao = scanner.nextInt();
        System.out.print("\nNome> ");
        String nome = scanner.next();
        System.out.print("\nAno> ");
        String ano = scanner.next();
        System.out.print("\nLocal> ");
        String local = scanner.next();
        fn.adicionaFilme(posicao, nome, ano, local);
    }

    /**
     * Sai da aplicação.
     */
    private static void sai() {
        System.out.println("\nVlw flw o/");
        System.exit(0);
    }

    /**
     * Lê carga de filmes de um arquivo csv.
     *
     * @param arquivoFilmes O caminho para o arquivo.
     * @param fn O sistema FilmNow a ser populado com os dados.
     * @throws IOException Caso o arquivo não exista ou não possa ser lido.
     */
    private static void carregaFilmes(String arquivoFilmes, FilmNow fn) throws FileNotFoundException, IOException {
        LeitorFilmNow leitor = new LeitorFilmNow();

        int carregados =  leitor.carregaContatos(arquivoFilmes, fn);
        System.out.println("Carregamos " + carregados + " registros.");
    }
}