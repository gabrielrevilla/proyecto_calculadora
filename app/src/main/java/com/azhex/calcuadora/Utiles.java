package com.azhex.calcuadora;
import java.text.DecimalFormat;

public final class Utiles {

    public static final Double formatResultado(double numero){
        String formatted = "";
        DecimalFormat df = new DecimalFormat("#.#####");
        formatted = df.format(numero);
        return Double.valueOf(formatted);
    }

}
