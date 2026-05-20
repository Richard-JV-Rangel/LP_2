package service;

import model.Precipitacao;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EstatisticasService {
    private List<Precipitacao> dados;

    // Recebo a lista de dados carregada do CSV aqui no construtor
    public EstatisticasService(List<Precipitacao> dados) {
        this.dados = dados;
    }

    // Criei esse método auxiliar privado para sempre filtrar pelo ano que o usuário pedir
    // Assim não repito código nos outros métodos
    private List<Precipitacao> filtrarPorAno(int ano) {
        return dados.stream()
                .filter(p -> p.getData().getYear() == ano)
                .collect(Collectors.toList());
    }

    public double totalPrecipitacaoPorMes(int mes, int ano) {
        return filtrarPorAno(ano).stream()
                .filter(p -> p.getData().getMonthValue() == mes) // Pega só o mês pedido
                .mapToDouble(Precipitacao::getValor) // Extrai só os valores de chuva
                .sum(); // Soma tudo
    }

    public Precipitacao diaMaiorPrecipitacao(int ano) {
        return filtrarPorAno(ano).stream()
                .max(Comparator.comparing(Precipitacao::getValor)) // Compara pra achar o maior
                .orElse(null); // Se a lista tiver vazia, retorna null
    }

    public Precipitacao diaMenorPrecipitacao(int ano) {
        return filtrarPorAno(ano).stream()
                .min(Comparator.comparing(Precipitacao::getValor)) // Compara pra achar o menor
                .orElse(null);
    }

    public Month mesMaiorPrecipitacao(int ano) {
        // Aqui eu agrupo os dados por Mês e já somo o total de cada mês
        Map<Month, Double> totalPorMes = filtrarPorAno(ano).stream()
                .collect(Collectors.groupingBy(p -> p.getData().getMonth(), Collectors.summingDouble(Precipitacao::getValor)));

        // Pego qual foi a "chave" (mês) que teve o maior "valor" (soma)
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
                .average() // A API de Streams já tem o método average que faz a média
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
                .sorted(Comparator.comparing(Precipitacao::getValor).reversed()) // Ordeno do maior pro menor (reversed)
                .limit(10) // Pego só os 10 primeiros
                .collect(Collectors.toList());
    }
}