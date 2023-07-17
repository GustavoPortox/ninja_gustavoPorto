/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ninjateste_;

/**
 *
 * @author gusta
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Comparator;

public class NinjaTeste {

    // Função para processar os dados do arquivo de entrada
    public static void processarDados(String input) {
        String[] linhas = input.trim().split("\n");
        Map<String, Piloto> temposTotais = new HashMap<>();
        //controlar a primeira linha do arquivo de entrada
        boolean primeiraLinha = false;

        for (String linha : linhas) {
            if(!primeiraLinha){
                primeiraLinha = true;
                continue;
            }
            String[] campos = linha.trim().split("\\s{2,}"); // expressao regular para dividir os campos contendo dois ou mais espaços em branco.
            String numeroVoltaStr = campos[2]; // campo de linha é dado como string, realizado conversão na linha a baixo para obter o valor e manipula-lo.
            int numeroVolta = Integer.parseInt(numeroVoltaStr);

            if (numeroVolta <= 4) {
                String codigoPiloto = campos[1].split(" – ")[0].trim(); // dividindo o codigo do piloto apos o hifen com base no indice[0].
                String nomePiloto = campos[1].split(" – ")[1].trim(); // divide o nome do piloto baseado no proprio campo apos o hifen.
                String tempoVoltaFormatado = campos[3].trim();

                if (!temposTotais.containsKey(codigoPiloto)) {
                    Piloto piloto = new Piloto(nomePiloto, codigoPiloto);
                    temposTotais.put(codigoPiloto, piloto);
                }

                Piloto piloto = temposTotais.get(codigoPiloto);
                double tempoVoltaEmSegundos = converterTempoEmSegundos(tempoVoltaFormatado);
                piloto.incrementarVoltasCompletadas();
                piloto.adicionarTempoTotal(tempoVoltaEmSegundos);
            }
        }

        Resultado[] resultadosOrdenados = temposTotais.values().stream()
                .filter(piloto -> piloto.getVoltasCompletadas() == 4) //Apenas pilotos que completaram a prova.
                .sorted(Comparator.comparingDouble(Piloto::getTempoTotal))
                .map(piloto -> new Resultado(piloto.getCodigoPiloto(), piloto.getNomePiloto(),
                        piloto.getVoltasCompletadas(), converterSegundosEmTempo(piloto.getTempoTotal())))
                .toArray(Resultado[]::new);

        StringBuilder conteudoResultado = new StringBuilder();
        conteudoResultado.append(" Posição Chegada ; Código Piloto ; Nome Piloto ; Qtde Voltas Completadas ; Tempo Total de Prova\n");
        for (int i = 0; i < resultadosOrdenados.length; i++) {
            Resultado resultado = resultadosOrdenados[i];
            conteudoResultado.append(String.format("%d ; %s ; %s ; %d ; %s\n",
                    i + 1, resultado.getCodigoPiloto(), resultado.getNomePiloto(),
                    resultado.getVoltasCompletadas(), resultado.getTempoTotalProva()));
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("resultados.txt"))) {
            writer.write(conteudoResultado.toString());
            System.out.println("Arquivo resultados.txt gerado com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao escrever o arquivo de resultados: " + e.getMessage());
        }
    }

    // Função auxiliar para converter tempo no formato minutos segundos e milisegundos em segundos para facilitar a manipulacao da saida de dados.
    public static double converterTempoEmSegundos(String tempo) {
        String[] partesTempo = tempo.split(":|\\."); //expressao regular para dividir os campos em : e . da forma como o tempo esta sendo mostrado. 1:02.852
        int minutos = Integer.parseInt(partesTempo[0]); //pega a primeira parte do `tempo` 1:
        int segundos = Integer.parseInt(partesTempo[1]); 
        int milissegundos = Integer.parseInt(partesTempo[2]);
        return minutos * 60 + segundos + milissegundos / 1000.0; // retorna o valor do tempo em segundos
    }

    // Função auxiliar para converter segundos em tempo no formato mm:ss.SSS
    public static String converterSegundosEmTempo(double segundos) {
        int minutos = (int) segundos / 60; 
        int segundosRestantes = (int) segundos % 60;
        int milissegundos = (int) Math.round((segundosRestantes - Math.floor(segundosRestantes)) * 1000);
        return String.format("%02d:%02d.%03d", minutos, segundosRestantes, milissegundos);
    }

    public static void main(String[] args) {
        String nomeDoArquivo = "./ninjateste_/arquivo_de_entrada.txt";
        try {
            InputStream inputStream = NinjaTeste.class.getClassLoader().getResourceAsStream(nomeDoArquivo);
            if (inputStream != null) {
                InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                StringBuilder stringBuilder = new StringBuilder();
                char[] buffer = new char[4096];
                int bytesRead;
                while ((bytesRead = reader.read(buffer)) != -1) {
                    stringBuilder.append(buffer, 0, bytesRead);
                }
                String data = stringBuilder.toString();
                processarDados(data);
            } else {
                System.err.println("Arquivo não encontrado: " + nomeDoArquivo);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo de entrada: " + e.getMessage());
        }
    }


    private static class Resultado {
        private String codigoPiloto;
        private String nomePiloto;
        private int voltasCompletadas;
        private String tempoTotalProva;

        public Resultado(String codigoPiloto, String nomePiloto, int voltasCompletadas, String tempoTotalProva) {
            this.codigoPiloto = codigoPiloto;
            this.nomePiloto = nomePiloto;
            this.voltasCompletadas = voltasCompletadas;
            this.tempoTotalProva = tempoTotalProva;
        }

        public String getCodigoPiloto() {
            return codigoPiloto;
        }

        public String getNomePiloto() {
            return nomePiloto;
        }

        public int getVoltasCompletadas() {
            return voltasCompletadas;
        }

        public String getTempoTotalProva() {
            return tempoTotalProva;
        }
    }
}
