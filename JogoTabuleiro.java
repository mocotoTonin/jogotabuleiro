import java.util.Random;
import java.util.Scanner;

public class JogoTabuleiro {
    private static final int TOTAL_CASAS = 33;
    private static final int[] casasVermelhas = {5, 7, 10, 13, 15, 18, 19, 24, 26, 29};
    private static final int[] casasVerdes = {1, 4, 8, 12, 14, 17, 21, 23, 27, 28, 32};
    private int posicaoJogador1;
    private int posicaoJogador2;

    public JogoTabuleiro() {
        posicaoJogador1 = 0;
        posicaoJogador2 = 0;
    }
    public void jogar() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.println("OLÁ! ESTÉ É O JOGO DA FUGA IMPLACAVEL. NO QUAL, VOCÊ TEM QUE SE MOSTRAR SER BOM O SUFICIENTE PARA ESCAPAR DA PRISÃO MAS BEM VIGIADA DO MUNDO. DESDE JÁ, AGRADEÇO A ATENÇÃO, E TE DEIXO UMA DICA: SÓ 1 SAI VIVO HAHAHA. BOA SORTE");
        System.out.println();
        
        int jogadorAtual = 1;
        while (posicaoJogador1 < TOTAL_CASAS && posicaoJogador2 < TOTAL_CASAS) {
            System.out.println("Jogador " + jogadorAtual + ", CLIQUE Enter para lançar o dado");
            scanner.nextLine();

            int dado = random.nextInt(6) + 1;
            System.out.println("Jogador " + jogadorAtual + " acertou o dado: " + dado);

            if (jogadorAtual == 1) {
                moverJogador(dado, 1);
                verificarCasa(1);
                jogadorAtual = 2;
            } else {
                moverJogador(dado, 2);
                verificarCasa(2);
                jogadorAtual = 1;
            }
            if (posicaoJogador1 >= TOTAL_CASAS) {
                System.out.println("Jogador 1 escapou da Prisao ");
                break;
            } else if (posicaoJogador2 >= TOTAL_CASAS) {
                System.out.println("Jogador 2 escapou da Prisao");
                break;
            }
        }
        scanner.close();
    }
    private void moverJogador(int dado, int jogador) {
        if (jogador == 1) {
            posicaoJogador1 += dado;
            if (posicaoJogador1 >= TOTAL_CASAS) {
                posicaoJogador1 = TOTAL_CASAS;
            }
            System.out.println("Jogador 1 está na casa: " + posicaoJogador1);
        } else {
            posicaoJogador2 += dado;
            if (posicaoJogador2 >= TOTAL_CASAS) {
                posicaoJogador2 = TOTAL_CASAS;
            }
            System.out.println("Jogador 2 está na casa: " + posicaoJogador2);
        }
    }
    private void verificarCasa(int jogador) {
        int posicaoAtual = (jogador == 1) ? posicaoJogador1 : posicaoJogador2;
        if (contains(casasVermelhas, posicaoAtual)) {
            System.out.println("Casa Vermelha! Jogador " + jogador + " volta duas casas.");
            posicaoAtual -= 2;
        } else if (contains(casasVerdes, posicaoAtual)) {
            System.out.println("Casa Verde! Jogador " + jogador + " avança duas casas.");
            posicaoAtual += 2;
        }
        if (posicaoAtual < 0) {
            posicaoAtual = 0;
        }
        if (jogador == 1) {
            posicaoJogador1 = posicaoAtual;
            System.out.println("Jogador 1 agora está na casa: " + posicaoJogador1);
        } else {
            posicaoJogador2 = posicaoAtual;
            System.out.println("Jogador 2 agora está na casa: " + posicaoJogador2);
        }
        System.out.println();
    }
    private boolean contains(int[] array, int key) {
        for (int i : array) {
            if (i == key) {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        JogoTabuleiro jogo = new JogoTabuleiro();
        jogo.jogar();
    }
}