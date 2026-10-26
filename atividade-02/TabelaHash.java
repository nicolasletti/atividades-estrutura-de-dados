import java.util.Random;

public class TabelaHash {

    public static void main(String[] args) {
        int tamanho = 100;
        int tentativas = 100;
        int limiteOcupacao = 90; // 90% de 100 posições

        Random random = new Random(1090);
        int[] tabela = new int[tamanho];
        boolean[] ocupada = new boolean[tamanho];

        int[] valores = new int[tentativas];
        int[] colisoesPorValor = new int[tentativas];
        int inseridos = 0;
        int totalColisoes = 0;
        int rejeitados = 0;

        for (int i = 0; i < tentativas; i++) {
            int valor = random.nextInt(100001);

            if (inseridos >= limiteOcupacao) {
                rejeitados++;
                continue;
            }

            int posicao = valor % tamanho;
            int colisoes = 0;
            while (ocupada[posicao]) {
                colisoes++;
                posicao = (posicao + 1) % tamanho;
            }

            tabela[posicao] = valor;
            ocupada[posicao] = true;
            valores[inseridos] = valor;
            colisoesPorValor[inseridos] = colisoes;
            totalColisoes = totalColisoes + colisoes;
            inseridos++;
        }

        System.out.println("Elementos inseridos: " + inseridos);
        System.out.println("Elementos barrados pelo limite de 90%: " + rejeitados);
        System.out.println("Total de colisões: " + totalColisoes);

        System.out.println("Três elementos que mais colidiram:");
        boolean[] usado = new boolean[tentativas];
        for (int k = 0; k < 3; k++) {
            int melhor = -1;
            for (int i = 0; i < inseridos; i++) {
                if (!usado[i] && (melhor == -1 || colisoesPorValor[i] > colisoesPorValor[melhor])) {
                    melhor = i;
                }
            }
            usado[melhor] = true;
            System.out.println("  " + valores[melhor] + " (" + colisoesPorValor[melhor] + " colisões)");
        }

        System.out.print("Posições livres: ");
        for (int i = 0; i < tamanho; i++) {
            if (!ocupada[i]) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
}
