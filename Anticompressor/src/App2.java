import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class App2 {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java App <caminho_dicionario>");
            return;
        }

        System.out.println("Carregando dicionário... " + args[0]);
        String[][] dicionario = carregarDicionario(args[0]);
        Anticompressor2 anticompressor = new Anticompressor2(dicionario);

        System.out.println("Dicionário carregado com sucesso.");
        System.out.println(anticompressor.dicionarioString());

        long inicio = System.nanoTime();
        long resultado = anticompressor.anticompressor();
        long fim = System.nanoTime();
        double tempoMs = (fim - inicio) / 1_000_000.0;

        System.out.println("Comprimento do arquivo de saída: " + resultado);
        System.out.println("Tempo de execução: " + String.format("%.3fms", tempoMs));
    }

    private static String[][] carregarDicionario(String caminhoArquivo) {
        String[][] pares = new String[0][0];

        try {
            List<String> linhas = Files.readAllLines(Path.of(caminhoArquivo));
            pares = new String[linhas.size()][2];

            for (int i = 0; i < linhas.size(); i++) {
                String[] linha = linhas.get(i).split(" ", 2);

                String chave;
                String valor;
                chave = linha[0];
                valor = linha[1];

                if (chave.isEmpty()) {
                    throw new RuntimeException("Formato inválido na linha: " + linha);
                }

                pares[i] = new String[] { chave, valor };
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao ler dicionario: " + caminhoArquivo, e);
        }

        return pares;
    }
}
