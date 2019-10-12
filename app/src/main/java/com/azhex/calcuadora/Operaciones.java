package com.azhex.calcuadora;

public class Operaciones {
    public static Double sumar(String primerNumero, String segundoNumero){
        Double suma = 0.0;
        suma = Double.valueOf(primerNumero) + Double.valueOf(segundoNumero);
        return suma;
    }

    /**
     * @param primerNumero String que contiene solo numeros
     * @param segundoNumero String que contiene solo numeros, esta resta a la primera String
     * @return int, resultado de la resta
     */
    public static Double resta(String primerNumero, String segundoNumero){
        Double resta = 0.0;
        resta = Double.valueOf(primerNumero) - Double.valueOf(segundoNumero);
        return resta;
    }



}
