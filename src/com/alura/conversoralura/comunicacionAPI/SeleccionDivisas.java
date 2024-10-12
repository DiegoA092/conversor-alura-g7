package com.alura.conversoralura.comunicacionAPI;

public class SeleccionDivisas {
    private final String divisaResultante;
    private final double conversion;

    public SeleccionDivisas(String divisaResultante, double conversion) {
        this.divisaResultante = divisaResultante;
        this.conversion = conversion;
    }

    public String getDivisaResultante() {
        return divisaResultante;
    }

    public double getConversion() {
        return conversion;
    }

    @Override
    public String toString() {
        return  "Divisa resultante = " + divisaResultante +
                ", Valor resultante = " + conversion;
    }
}
