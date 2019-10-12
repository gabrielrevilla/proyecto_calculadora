package com.azhex.calcuadora;

public class Operaciones {
    public static int sumar(String primerNumero, String segundoNumero){
        int suma = 0;
        suma = Integer.valueOf(primerNumero) + Integer.valueOf(segundoNumero);
        return suma;
    }

    /**
     * @param primerNumero String que contiene solo numeros
     * @param segundoNumero String que contiene solo numeros, esta resta a la primera String
     * @return int, resultado de la resta
     */
    public static int resta(String primerNumero, String segundoNumero){
        int suma = 0;
        suma = Integer.valueOf(primerNumero) - Integer.valueOf(segundoNumero);
        return suma;
    }



}
