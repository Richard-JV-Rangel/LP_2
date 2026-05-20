package main;

import model.Precipitacao;
import service.EstatisticasService;
import util.LeitorCsv;

import java.util.List;

public class Aplicacao {
    public static void main(String[] args) {
        // 1. Instancio minha classe que lê o arquivo
        LeitorCsv leitor = new LeitorCsv();

        // 2. Carrego todos os dados para a memória (usando o ArrayList)
        List<Precipitacao> dados = leitor.lerArquivo("PluviometriaFuncemeNormalizada_2026-05-19T21_02_25.csv");

        // 3. Passo os dados pro meu Service que vai fazer os cálculos
        EstatisticasService service = new EstatisticasService(dados);

        // O enunciado pede para passar os parâmetros na chamada dos métodos
        int anoAlvo = 2025;
        int mesAlvo = 3; // Mês 3 = Março

        System.out.println("=== RELATÓRIO DE PRECIPITAÇÃO ACARAÚ-CE (" + anoAlvo + ") ===");

        System.out.printf("Total precipitação mês %d: %.2f mm%n", mesAlvo, service.totalPrecipitacaoPorMes(mesAlvo, anoAlvo));
        System.out.println("Dia maior precipitação: " + service.diaMaiorPrecipitacao(anoAlvo));
        System.out.println("Dia menor precipitação: " + service.diaMenorPrecipitacao(anoAlvo));
        System.out.println("Mês de maior precipitação: " + service.mesMaiorPrecipitacao(anoAlvo));
        System.out.println("Mês de menor precipitação: " + service.mesMenorPrecipitacao(anoAlvo));
        System.out.printf("Média de precipitação anual: %.2f mm%n", service.mediaPrecipitacaoAno(anoAlvo));
        System.out.printf("Média de precipitação mês %d: %.2f mm%n", mesAlvo, service.mediaPrecipitacaoMes(mesAlvo, anoAlvo));

        System.out.println("\n--- Top 10 dias com maior precipitação ---");
        List<Precipitacao> top10 = service.top10DiasMaiorPrecipitacao(anoAlvo);

        // Faço um for simples para imprimir o top 10 com a numeração
        for (int i = 0; i < top10.size(); i++) {
            System.out.println((i + 1) + "º lugar: " + top10.get(i));
        }
    }
}