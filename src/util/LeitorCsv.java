package util;

import model.Precipitacao;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LeitorCsv {
    public List<Precipitacao> lerArquivo(String caminhoArquivo) {
        // Escolhi ArrayList porque vamos fazer MUITAS leituras e buscas depois.
        // É bem mais rápido que o LinkedList pra esse cenário.
        List<Precipitacao> dados = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha = br.readLine(); // Leio a primeira linha só pra pular o cabeçalho do CSV

            // Vai lendo linha por linha até o arquivo acabar (ficar null)
            while ((linha = br.readLine()) != null) {
                // O CSV é separado por ponto e vírgula, então corto a string aqui
                String[] colunas = linha.split(";");

                // Convertendo cada coluna pro tipo certo da minha classe Precipitacao
                int id = Integer.parseInt(colunas[0]);
                double valor = Double.parseDouble(colunas[1]);
                LocalDate data = LocalDate.parse(colunas[2], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                int posto = Integer.parseInt(colunas[3]);

                // Instancio o objeto e jogo dentro da minha lista
                dados.add(new Precipitacao(id, valor, data, posto));
            }
        } catch (IOException e) {
            System.err.println("Deu erro na leitura: " + e.getMessage());
        }
        return dados;
    }
}