public class Anticompressor {
    String[][] dicionario = new String[0][0];

    public Anticompressor(String[][] dicionario) {
        this.dicionario = dicionario;
    }

    public String anticompressor(String[][] dicionario) {
        StringBuilder resultadoFinal = new StringBuilder();
        String raiz = this.dicionario[0][0];
        System.out.println("Raiz do dicionário: " + raiz);

        resultadoFinal.append(buscaValor(raiz));
        return resultadoFinal.toString();
    }

    private String buscaValor(String chave) {
        String valor = null;
        for (String[] par : this.dicionario) {
            if (par[0].equals(chave)) {
                valor = par[1];
                break;
            }
        }
        return (valor == null || valor.isEmpty()) ? chave : adicionaValor(valor);
    }

    private String adicionaValor(String valor) {
        StringBuilder resultado = new StringBuilder();
        for (char c : valor.toCharArray()) {
            resultado.append(buscaValor(String.valueOf(c)));
        }
        return resultado.toString();
    }

    public String dicionarioString(String[][] dicionario) {
        StringBuilder dicionarioString = new StringBuilder();
        for (String[] linha : dicionario) {
            // Ignorar entradas inválidas
            if (linha == null || linha.length != 2) {
                continue;
            }
            String chave = linha[0];
            String valor = linha[1];
            dicionarioString.append(chave).append(" ").append(valor).append("\n");
        }
        return dicionarioString.toString();
    }
}