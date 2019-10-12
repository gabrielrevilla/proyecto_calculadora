package com.azhex.calcuadora;
import java.text.DecimalFormat;

public final class Utiles {

    public static final Double formatResultado(double numero){
        String formatted = "";
        DecimalFormat df = new DecimalFormat("#.#####");
        formatted = df.format(numero);
        return Double.valueOf(formatted);
    }

    public static String addCommasToNumericString(String digits, String separacionXMil) {
        String result = "";
        for (int i=1; i <= digits.length(); ++i) {
            char ch = digits.charAt(digits.length() - i);
            if (i % 3 == 1 && i > 1) {
                //busco en Recursos strings para ver el locale, se cambiara si es español o inglés.
                result = separacionXMil + result;
            }
            result = ch + result;
        }
        return result;
    }

}
