package model;

import java.time.LocalDate;

public class Precipitacao {
    // Atributos baseados nas colunas do arquivo CSV
    private int id;
    private double valor;
    private LocalDate data;
    private int posto;

    // Construtor para preencher os dados na hora que ler do arquivo
    public Precipitacao(int id, double valor, LocalDate data, int posto) {
        this.id = id;
        this.valor = valor;
        this.data = data;
        this.posto = posto;
    }

    // Só criei os Getters que vou realmente usar nas estatísticas para o código ficar limpo
    public double getValor() { return valor; }
    public LocalDate getData() { return data; }

    // Sobrescrevi o toString para imprimir bonitinho no console em vez daquele hash feio da memória
    @Override
    public String toString() {
        return "Data: " + data + " | Choveu: " + valor + "mm";
    }
}