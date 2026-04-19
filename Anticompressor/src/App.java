import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class App {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java App <caminho_dicionario>");
            return;
        }

        System.out.println("Carregando dicionário... " + args[0]);
        String[][] dicionario = carregarDicionario(args[0]);
        Anticompressor anticompressor = new Anticompressor(dicionario);

        System.out.println("Dicionário carregado com sucesso.");
        System.out.println(anticompressor.dicionarioString(dicionario));

        long inicio = System.currentTimeMillis();
        String result = anticompressor.anticompressor(dicionario);
        long fim = System.currentTimeMillis();

        // System.out.println("Resultado: " + result);
        System.out.println("Comprimento do resultado: " + result.length());
        System.out.println("Tempo de execução: " + (fim - inicio) + "ms");
    }

    private static String[][] carregarDicionario(String caminhoArquivo) {
        String[][] pares;

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
