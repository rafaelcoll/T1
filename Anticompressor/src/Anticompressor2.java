import java.util.HashMap;
import java.util.Map;

public class Anticompressor2 {
    Map<String, String> dicionario = new HashMap<>();
    Map<String, Long> cache = new HashMap<>();

    public Anticompressor2(String[][] dicionario) {
        for (String[] par : dicionario) {
            this.dicionario.put(par[0], par[1]);
        }
    }

    public long anticompressor() {
        String raiz = encontraRaiz();
        System.out.println("Raiz do dicionário: " + raiz);
        processaDicionario();
        // long resultadoBusca = buscaValor(raiz);
        // System.out.println("buscaValor: " + resultadoBusca);
        return this.cache.get(raiz);
    }

    public String encontraRaiz() {
        for (Map.Entry<String, String> par : this.dicionario.entrySet()) {
            if (par.getValue().isEmpty()) {
                continue;
            }

            String chave = par.getKey();
            boolean isRaiz = true;
            for (String valor : this.dicionario.values()) {
                if (valor.contains(chave)) {
                    isRaiz = false;
                    break;
                }
            }

            if (isRaiz) {
                return chave;
            }
        }
        throw new IllegalStateException("Nenhuma raiz encontrada no dicionário.");
    }

    private long buscaValor(String chave) {
        String valor = this.dicionario.get(chave);
        if (valor == null || valor.isEmpty()) {
            return 1;
        }

        long resultado = 0;
        for (char c : valor.toCharArray()) {
            resultado += buscaValor(String.valueOf(c));
        }
        return resultado;
    }

    private void processaDicionario() {
        for (Map.Entry<String, String> par : this.dicionario.entrySet()) {
            if (par.getValue().isEmpty()) {
                this.cache.put(par.getKey(), 1L);
                System.out.println("Cache atualizado: " + par.getKey() + " = 1");
            }
        }
        
        int dicionarioSize = this.dicionario.size();

        while (this.cache.size() < dicionarioSize) {
            for (Map.Entry<String, String> par : this.dicionario.entrySet()) {
                if (this.cache.containsKey(par.getKey())) {
                    continue;
                }

                String valor = par.getValue();
                boolean todasChavesEmCache = true;

                for (char c : valor.toCharArray()) {
                    if (!this.cache.containsKey(String.valueOf(c))) {
                        todasChavesEmCache = false;
                        break;
                    }
                }

                if (todasChavesEmCache) {
                    long valorCalculado = 0;
                    for (char c : valor.toCharArray()) {
                        valorCalculado += this.cache.get(String.valueOf(c));
                    }
                    this.cache.put(par.getKey(), valorCalculado);
                    System.out.println("Cache atualizado: " + par.getKey() + " = " + valorCalculado);
                }
            }
        }
    }

    public String dicionarioString() {
        StringBuilder dicionarioString = new StringBuilder();
        for (Map.Entry<String, String> par : this.dicionario.entrySet()) {
            if (par.getKey().isEmpty()) {
                continue;
            }
            String chave = par.getKey();
            String valor = par.getValue();
            dicionarioString.append(chave).append(" ").append(valor).append("\n");
        }
        return dicionarioString.toString();
    }
}