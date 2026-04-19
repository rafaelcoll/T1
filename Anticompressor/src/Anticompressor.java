import java.util.HashMap;
import java.util.Map;

public class Anticompressor {
    Map<String, String> mapaDicionario = new HashMap<>();

    public Anticompressor(String[][] dicionario) {
        for (String[] par : dicionario) {
            mapaDicionario.put(par[0], par[1]);
        }
    }

    public String anticompressor(String[][] dicionario) {
        StringBuilder resultadoFinal = new StringBuilder();
        String valorInicial = mapaDicionario.get(dicionario[0][0]);
        System.out.println("Valor inicial: " + valorInicial);

        resultadoFinal.append(adicionaValor(valorInicial, mapaDicionario));
        
        return resultadoFinal.toString();
    }
    
    private static String buscaValor(String chave, Map<String, String> mapaDicionario) {
        String valor = mapaDicionario.get(chave);
        if (valor == null || valor.isEmpty()) {
            return chave;
        }
        return adicionaValor(valor, mapaDicionario);
    }
    
    private static String adicionaValor(String valor, Map<String, String> mapaDicionario) {
        StringBuilder resultado = new StringBuilder();
        for (char c : valor.toCharArray()) {
            resultado.append(buscaValor(String.valueOf(c), mapaDicionario));
        }
        return resultado.toString();
    }

    public String stringDicionario(String[][] dicionario) {
        StringBuilder resultado = new StringBuilder();
        for (String[] linha : dicionario) {
            // Ignorar entradas inválidas
            if (linha == null || linha.length != 2) {
                continue; 
            }

            String chave = linha[0];
            String valor = linha[1];
            resultado.append(chave).append(" ").append(valor).append("\n");
        }
        return resultado.toString();
    }
}