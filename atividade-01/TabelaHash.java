import java.util.Random;

public class TabelaHash {

    static class No {
        int valor;
        No proximo;

        No(int valor) {
            this.valor = valor;
            this.proximo = null;
        }
    }

    public static void main(String[] args) {
        int tamanho = 100;
        int quantidade = 1000;
        int limite = 100001; // vai de 0 a 100000

        Random random = new Random(1090);
        No[] tabela = new No[tamanho];
        int[] tamanhos = new int[tamanho];

        // três últimos valores que caíram em lista encadeada
        int ultimo1 = -1; // mais recente
        int ultimo2 = -1;
        int ultimo3 = -1;

        for (int i = 0; i < quantidade; i++) {
            int valor = random.nextInt(limite);
            int posicao = valor % tamanho;
            No novo = new No(valor);

            if (tabela[posicao] == null) {
                tabela[posicao] = novo;
            } else {
                // colisão: vai até o fim da lista
                No atual = tabela[posicao];
                while (atual.proximo != null) {
                    atual = atual.proximo;
                }
                atual.proximo = novo;

                ultimo3 = ultimo2;
                ultimo2 = ultimo1;
                ultimo1 = valor;
            }
            tamanhos[posicao]++;
        }

        int maior = 0;
        int posicaoMaior = 0;
        int quantidadeListas = 0;
        int vazias = 0;

        for (int i = 0; i < tamanho; i++) {
            if (tamanhos[i] == 0) {
                vazias++;
            }
            if (tamanhos[i] > 1) {
                quantidadeListas++;
            }
            if (tamanhos[i] > maior) {
                maior = tamanhos[i];
                posicaoMaior = i;
            }
        }

        System.out.println("Maior lista encadeada: posicao " + posicaoMaior + " com " + maior + " elementos");
        System.out.print("Valores: ");
        No atual = tabela[posicaoMaior];
        while (atual != null) {
            System.out.print(atual.valor);
            if (atual.proximo != null) {
                System.out.print(" -> ");
            }
            atual = atual.proximo;
        }
        System.out.println();
        System.out.println("Listas encadeadas geradas (posicoes com colisao): " + quantidadeListas);
        System.out.println("Posicoes vazias: " + vazias);
        System.out.println("Tres ultimos valores inseridos em listas encadeadas: "
                + ultimo3 + ", " + ultimo2 + ", " + ultimo1);
    }
}
