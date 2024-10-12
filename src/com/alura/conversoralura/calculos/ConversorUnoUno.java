package com.alura.conversoralura.calculos;

import com.alura.conversoralura.comunicacionAPI.BusquedaExchangeRate;

import java.io.IOException;

public class ConversorUnoUno {

    public double conversionUnoUno (String divisaInicial, String divisaResultante) throws IOException {
        BusquedaExchangeRate solicitud = new BusquedaExchangeRate();
        return solicitud.solicitudDeDivisas(divisaInicial, divisaResultante);
    }
}
