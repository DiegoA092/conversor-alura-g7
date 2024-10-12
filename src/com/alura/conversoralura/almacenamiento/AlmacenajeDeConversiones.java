package com.alura.conversoralura.almacenamiento;

import java.util.ArrayList;
import java.util.List;

public class AlmacenajeDeConversiones {
    private final List<AlmacenajeUnoUno> listaDeConversiones;
    private final String nombreDeUsuario;

    public AlmacenajeDeConversiones(String nombreDeUsuario) {
        this.listaDeConversiones = new ArrayList<>();
        this.nombreDeUsuario = nombreDeUsuario;
    }

    public boolean almacenarConversionUnoUno(AlmacenajeUnoUno almacenajeUnoUno){
        this.listaDeConversiones.add(almacenajeUnoUno);
        return true;
    }

    public List<AlmacenajeUnoUno> getListaDeConversiones() {
        return listaDeConversiones;
    }

    public String getNombreDeUsuario() {
        return nombreDeUsuario;
    }

}
