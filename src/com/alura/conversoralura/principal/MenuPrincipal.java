package com.alura.conversoralura.principal;

import com.alura.conversoralura.almacenamiento.AlmacenajeDeConversiones;
import com.alura.conversoralura.almacenamiento.AlmacenajeUnoUno;
import com.alura.conversoralura.calculos.ConversorUnoEne;
import com.alura.conversoralura.calculos.ConversorUnoUno;
import com.alura.conversoralura.comunicacionAPI.SeleccionDivisas;

import java.io.IOException;
import java.util.*;

public class MenuPrincipal {
    public static void main(String[] args) throws IOException {
        Scanner teclado = new Scanner(System.in);
        int seleccionDeTransaccion = 0;
        int contadorDeAlmacenaje = 0;
        String divisaResultante;
        String divisaInicial;
        boolean almacenajeExitoso;
                System.out.println("Ingrese su nombre:");
        String nombreDeUsuario = teclado.next();
        AlmacenajeDeConversiones almacenajeDeUsuario = new AlmacenajeDeConversiones(nombreDeUsuario);
        double monto;
        String bienvenida = String.format("""
                ************************************************
                
                ** Hola %s!
                ** Bienvenido al conversor de divisas de Alura
                ** Desarrollado por Diego Alvarado [G7]
                """, nombreDeUsuario);
        System.out.println(bienvenida);


        do {
            try {
                if (seleccionDeTransaccion > 4) {
                    System.out.println("Transaccion invalida");
                    seleccionDeTransaccion = 0;
                    continue;
                }
                String menuPrincipal = """
                        ************************************************
                        Seleccione la accion que desea realizar:
                        	1. Conversion 1 a 1
                        	2. Conversion 1 a n
                        	3. Consultar historial de conversiones
                        	4. Salir

                        Elija una opcion valida
                        *************************************************""";
                System.out.println(menuPrincipal);
                seleccionDeTransaccion = teclado.nextInt();
                String menuDivisas = """
                        ************************************************
                        Divisas disponibles:

                        ARS - Peso argentino
                        BOB - Boliviano boliviano
                        BRL - Real brasileño
                        CLP - Peso chileno
                        COP - Peso colombiano
                        USD - Dólar estadounidense
                        MXN - Peso mexicano

                        *************************************************""";

                switch (seleccionDeTransaccion) {
                    case 1:
                        System.out.println(menuDivisas);
                        divisaInicial = mensajeDeSolicitud(teclado, "Seleccione divisa inicial");
                        divisaResultante = mensajeDeSolicitud(teclado, "Seleccione divisa resultante");
                        System.out.println("Ingrese monto a convertir:");
                        monto = teclado.nextDouble();
                        ConversorUnoUno solicitudConversion = new ConversorUnoUno();
                        var resultadoConversion = solicitudConversion.conversionUnoUno(divisaInicial, divisaResultante);
                        double resultoMonto = resultadoConversion * monto;
                        AlmacenajeUnoUno almacenajeUnoUno = new AlmacenajeUnoUno(divisaInicial, divisaResultante, monto, resultoMonto);
                        almacenajeExitoso = almacenajeDeUsuario.almacenarConversionUnoUno(almacenajeUnoUno);
                        System.out.println("___________________________________");
                        System.out.printf("%.2f %s equivale a %.2f %s \n", monto, divisaInicial, resultoMonto, divisaResultante);
                        if(almacenajeExitoso){
                            System.out.println("Almacenaje exitoso!");
                        } else {
                            System.out.println("Ocurrio un error!");
                        }
                        break;
                    case 2:
                        System.out.println(menuDivisas);
                        divisaInicial = mensajeDeSolicitud(teclado, "Seleccione divisa inicial");
                        String[] divisasResultantes = mensajeDeSolicitudMultiple(teclado, "Seleccione divisas resultantes separadas por una coma (usd,ars,etc):");
                        System.out.println("Ingrese monto a convertir:");
                        monto = teclado.nextDouble();
                        ConversorUnoEne solicitudConversionEne = new ConversorUnoEne();
                        var resultadoConversionEne = solicitudConversionEne.conversionUnoEne(divisaInicial, divisasResultantes);
                        System.out.println("___________________________________");
                        System.out.printf("%.2f %s equivale a: \n", monto, divisaInicial);
                        for (SeleccionDivisas divisaSeleccionada : resultadoConversionEne) {
                            double montoConvertido = divisaSeleccionada.getConversion() * monto;
                            System.out.printf("%.2f %s \n", montoConvertido, divisaSeleccionada.getDivisaResultante());
                            AlmacenajeUnoUno almacenajeUnoEne = new AlmacenajeUnoUno(divisaInicial, divisaSeleccionada.getDivisaResultante(), monto, montoConvertido);
                            almacenajeExitoso = almacenajeDeUsuario.almacenarConversionUnoUno(almacenajeUnoEne);
                            if (almacenajeExitoso) {
                                contadorDeAlmacenaje++;
                            }
                        }
                        if(contadorDeAlmacenaje == resultadoConversionEne.size()){
                            System.out.println("Almacenaje exitoso!");
                            contadorDeAlmacenaje = 0;
                        } else {
                            System.out.println("Ocurrio un error!");
                        }
                        break;
                    case 3:
                        System.out.printf("Estas son tus transacciones %s:", nombreDeUsuario);
                        System.out.println(almacenajeDeUsuario.getListaDeConversiones());
                        break;
                    case 4:
                        break;
                    default:
                        System.out.println("Transaccion invalida");
                }
            } catch (InputMismatchException e) {
                System.out.println("Transaccion invalida");
                teclado.next();
            }
        } while (seleccionDeTransaccion != 4);
        teclado.close();
        System.out.println("Diego A. te agradece por usar el conversor alura!");
    }

    private static String mensajeDeSolicitud(Scanner teclado, String mensaje) {
        List<String> divisasValidas = Arrays.asList("MXN", "USD", "ARS", "BOB", "BRL", "CLP", "COP");
        boolean divisaCorrecta = false;
        String divisaSeleccionada = "";
        while (!divisaCorrecta) {
            System.out.println(mensaje);
            divisaSeleccionada = teclado.next().toUpperCase();
            if (divisasValidas.contains(divisaSeleccionada)) {
                divisaCorrecta = true;
            } else {
                System.out.printf("Error! %s no es una divisa valida%n",divisaSeleccionada);
            }
        }
        return divisaSeleccionada;
    }

    private static String[] mensajeDeSolicitudMultiple(Scanner teclado, String mensaje) {
        List<String> divisasValidas = Arrays.asList("MXN", "USD", "ARS", "BOB", "BRL", "CLP", "COP");
        boolean divisaCorrecta = false;
        String divisasSeleccionadas;
        String[] listadoDeDivisas = null;
        teclado.nextLine();
        while (!divisaCorrecta) {
            System.out.println(mensaje);
            divisasSeleccionadas = teclado.nextLine().toUpperCase().replaceAll("\\s+","");
            listadoDeDivisas = divisasSeleccionadas.split(",");
            for (String divisa : listadoDeDivisas) {
                if (divisasValidas.contains(divisa) && !divisasSeleccionadas.contains(" ")) {
                    divisaCorrecta = true;
                } else if (divisasSeleccionadas.contains(".")) {
                    System.out.println("Separe divisas con coma (,)");
                } else if (divisasSeleccionadas.contains(" ")) {
                    System.out.println("No coloque espacios");
                } else {
                    System.out.printf("Error! %s no es una divisa valida%n",divisa);
                }
            }
        }
        return listadoDeDivisas;
    }
}
