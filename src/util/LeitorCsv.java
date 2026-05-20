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
        List<Precipitacao> dados = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha = br.readLine(); // Para pular o cabeçalho

            while ((linha = br.readLine()) != null) {
                String[] colunas = linha.split(";");
                int id = Integer.parseInt(colunas[0]);
                double valor = Double.parseDouble(colunas[1]);
                LocalDate data = LocalDate.parse(colunas[2], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                int posto = Integer.parseInt(colunas[3]);

                dados.add(new Precipitacao(id, valor, data, posto));
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return dados;
    }
}