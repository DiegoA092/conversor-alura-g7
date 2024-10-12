package com.alura.conversoralura.almacenamiento;

public class AlmacenajeUnoUno {
    private final String divisaInicial;
    private final String divisaResultante;
    private final double monto;
    private final double resultadoConversion;

    public AlmacenajeUnoUno(String divisaInicial, String divisaResultante, double monto, double resultadoConversion) {
    this.divisaInicial = divisaInicial;
    this.divisaResultante = divisaResultante;
    this.monto = monto;
    this.resultadoConversion = resultadoConversion;
    }

    @Override
    public String toString() {
        return  String.format("""
                \nValor inicial = %.2f %s --> Valor resultante = %.2f %s \n""", monto, divisaInicial, resultadoConversion, divisaResultante);
    }
}
