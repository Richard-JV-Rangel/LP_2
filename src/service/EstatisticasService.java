package service;

import model.Precipitacao;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EstatisticasService {
    private List<Precipitacao> dados;

    public EstatisticasService(List<Precipitacao> dados) {
        this.dados = dados;
    }

    private List<Precipitacao> filtrarPorAno(int ano) {
        return dados.stream().filter(p -> p.getData().getYear() == ano).collect(Collectors.toList());
    }

    public double totalPrecipitacaoPorMes(int mes, int ano) {
        return filtrarPorAno(ano).stream()
                .filter(p -> p.getData().getMonthValue() == mes)
                .mapToDouble(Precipitacao::getValor)
                .sum();
    }

    public Precipitacao diaMaiorPrecipitacao(int ano) {
        return filtrarPorAno(ano).stream()
                .max(Comparator.comparing(Precipitacao::getValor))
                .orElse(null);
    }

    public Precipitacao diaMenorPrecipitacao(int ano) {
        return filtrarPorAno(ano).stream()
                .min(Comparator.comparing(Precipitacao::getValor))
                .orElse(null);
    }

    public Month mesMaiorPrecipitacao(int ano) {
        Map<Month, Double> totalPorMes = filtrarPorAno(ano).stream()
                .collect(Collectors.groupingBy(p -> p.getData().getMonth(), Collectors.summingDouble(Precipitacao::getValor)));
        return totalPorMes.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public Month mesMenorPrecipitacao(int ano) {
        Map<Month, Double> totalPorMes = filtrarPorAno(ano).stream()
                .collect(Collectors.groupingBy(p -> p.getData().getMonth(), Collectors.summingDouble(Precipitacao::getValor)));
        return totalPorMes.entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public double mediaPrecipitacaoAno(int ano) {
        return filtrarPorAno(ano).stream()
                .mapToDouble(Precipitacao::getValor)
                .average()
                .orElse(0.0);
    }

    public double mediaPrecipitacaoMes(int mes, int ano) {
        return filtrarPorAno(ano).stream()
                .filter(p -> p.getData().getMonthValue() == mes)
                .mapToDouble(Precipitacao::getValor)
                .average()
                .orElse(0.0);
    }

    public List<Precipitacao> top10DiasMaiorPrecipitacao(int ano) {
        return filtrarPorAno(ano).stream()
                .sorted(Comparator.comparing(Precipitacao::getValor).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }
}