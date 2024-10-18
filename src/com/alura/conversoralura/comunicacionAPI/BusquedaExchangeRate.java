package com.alura.conversoralura.comunicacionAPI;

import com.google.gson.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class BusquedaExchangeRate {
    private ArrayList<SeleccionDivisas> listaDeConversiones;
    private String apiKey = "438a4d95173e2a40769f1803";
    public double solicitudDeDivisas(String divisaInicial, String divisaResultante) throws IOException {

        String direccion = "https://v6.exchangerate-api.com/v6/"+apiKey+"/latest/" + divisaInicial;

        // Making Request
        URL url = new URL(direccion);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        // Convert to JSON
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject jsonobj = root.getAsJsonObject();

        // Accessing object
        JsonObject conversionRates = jsonobj.get("conversion_rates").getAsJsonObject();


        return conversionRates.get(divisaResultante).getAsDouble();
    }

    public ArrayList<SeleccionDivisas> solicitudDeDivisasMultiple(String divisaInicial, String[] divisasResultantes) throws IOException {

        String direccion = "https://v6.exchangerate-api.com/v6/"+apiKey+"/latest/" + divisaInicial;

        // Making Request
        URL url = new URL(direccion);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        // Convert to JSON
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject jsonobj = root.getAsJsonObject();

        // Accessing object
        JsonObject conversionRates = jsonobj.get("conversion_rates").getAsJsonObject();


        this.listaDeConversiones = new ArrayList<>();

        for (String divisaResultante : divisasResultantes) {
            double conversion = conversionRates.get(divisaResultante).getAsDouble();
            SeleccionDivisas divisaSeleccionada = new SeleccionDivisas(divisaResultante, conversion);
            listaDeConversiones.add(divisaSeleccionada);
        }
        return listaDeConversiones;
    }
}
