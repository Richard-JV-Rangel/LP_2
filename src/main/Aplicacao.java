package main;

import model.Precipitacao;
import service.EstatisticasService;
import util.LeitorCsv;

import java.util.List;

public class Aplicacao {
    public static void main(String[] args) {
        LeitorCsv leitor = new LeitorCsv();
        List<Precipitacao> dados = leitor.lerArquivo("PluviometriaFuncemeNormalizada_2026-05-19T21_02_25.csv");

        EstatisticasService service = new EstatisticasService(dados);

        int anoAlvo = 2025;
        int mesAlvo = 3;

        System.out.println("=== RELATÓRIO DE PRECIPITAÇÃO ACARAÚ-CE (" + anoAlvo + ") ===");

        System.out.printf("Total precipitação mês %d: %.2f mm%n", mesAlvo, service.totalPrecipitacaoPorMes(mesAlvo, anoAlvo));
        System.out.println("Dia maior precipitação: " + service.diaMaiorPrecipitacao(anoAlvo));
        System.out.println("Dia menor precipitação: " + service.diaMenorPrecipitacao(anoAlvo));
        System.out.println("Mês de maior precipitação: " + service.mesMaiorPrecipitacao(anoAlvo));
        System.out.println("Mês de menor precipitação: " + service.mesMenorPrecipitacao(anoAlvo));
        System.out.printf("Média de precipitação anual: %.2f mm%n", service.mediaPrecipitacaoAno(anoAlvo));
        System.out.printf("Média de precipitação mês %d: %.2f mm%n", mesAlvo, service.mediaPrecipitacaoMes(mesAlvo, anoAlvo));

        System.out.println("\nTop 10 dias com maior precipitação:");
        List<Precipitacao> top10 = service.top10DiasMaiorPrecipitacao(anoAlvo);
        for (int i = 0; i < top10.size(); i++) {
            System.out.println((i + 1) + "º -> " + top10.get(i));
        }
    }
}