package com.alura.conversoralura.calculos;

import com.alura.conversoralura.comunicacionAPI.BusquedaExchangeRate;
import com.alura.conversoralura.comunicacionAPI.SeleccionDivisas;

import java.io.IOException;
import java.util.ArrayList;

public class ConversorUnoEne {

    public ArrayList<SeleccionDivisas> conversionUnoEne (String divisaInicial, String[] divisaResultante) throws IOException {
        BusquedaExchangeRate solicitud = new BusquedaExchangeRate();
        var resultado = solicitud.solicitudDeDivisasMultiple(divisaInicial, divisaResultante);
        return resultado;
    }
}
