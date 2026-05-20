package model;

import java.time.LocalDate;

public class Precipitacao {
    private int id;
    private double valor;
    private LocalDate data;
    private int posto;

    public Precipitacao(int id, double valor, LocalDate data, int posto) {
        this.id = id;
        this.valor = valor;
        this.data = data;
        this.posto = posto;
    }

    public double getValor() { return valor; }
    public LocalDate getData() { return data; }

    @Override
    public String toString() {
        return "Data: " + data + " | Valor: " + valor + "mm";
    }
}