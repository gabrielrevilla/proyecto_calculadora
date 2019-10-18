package com.azhex.calcuadora;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //TODO
    /*
    1. Hacer funcionar una opearcion, la suma.
    2. Acceso al repositorio
    3.
    4.
    legibilidad y organización del código (indentado, estructura, comentarios) [1 punto]
    ➢ layout y presentación de la interfaz [1 punto]
    ➢ manejo y presentación en pantalla del input del teclado numérico [1,5 puntos]
    ➢ ejecución correcta de las operaciones +,-,x, / al ejecutar = [2 puntos]
    ➢ funcionamiento correcto de la tecla +/- [1 punto]
    ➢ funcionamiento correcto de la tecla % [1 punto]
    ➢ funcionamiento correcto de los botones de memoria [1 punto]
    HECHO ➢ añadir icono de app [0,5 puntos]
    ➢ cambiar nombre app y texto de calculadora (. / ,) en función del idioma [1 punto]
     */

    //Declaramos dos variables: el numeroEnMemoria y el numeroIntroducido;
    //Los declaramos como String -> por que el usario introduce un numero a la vez, por lo que podremos concatenarlos y entonces convertirlos en un int de más de una cifra

    private static TextView numeroIntroducido_caja;
    private static TextView numeroMemoria_caja;
    private static Double numeroMemoria;
    private String separacionXMil;
    private static TextView textView_simbolo_memoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicialización de textViews
        numeroIntroducido_caja = findViewById(R.id.numeroIntroducido);
        numeroMemoria_caja = findViewById(R.id.numeroMemoria);

        //buscar en los recursos: coma o punto
        separacionXMil =  getResources().getString(R.string.decimal);
        //simbolo memoria
        textView_simbolo_memoria = findViewById(R.id.simboloMemodia);

    }


    //CONTROL DE LISTENERS --> NUMEROS, OPERACIONES, MEMORIA...
    //NUMEROS

    /**
     * Permite escribir numeros.
     * @param view Boton numerico de la calculadora pulsado
     */
    public void escribirNumero(View view) {
        switch (view.getId()){
            case R.id.button_cero:
                if (!numeroIntroducido_caja.getText().toString().matches("")){
                    numeroIntroducido_caja.append("0");
                }
                break;
            case R.id.btnCalc1:
                numeroIntroducido_caja.append("1");
                break;
            case R.id.btnCalc2:
                numeroIntroducido_caja.append("2");
                break;
            case R.id.btnCalc3:
                numeroIntroducido_caja.append("3");
                break;
            case R.id.btnCalc4:
                numeroIntroducido_caja.append("4");
                break;
            case R.id.btnCalc5:
                numeroIntroducido_caja.append("5");
                break;
            case R.id.btnCalc6:
                numeroIntroducido_caja.append("6");
                break;
            case R.id.btnCalc7:
                numeroIntroducido_caja.append("7");
                break;
            case R.id.btnCalc8:
                numeroIntroducido_caja.append("8");
                break;
            case R.id.btnCalc9:
                numeroIntroducido_caja.append("9");
                break;
            case R.id.button_coma:
                Button buttonComa = (Button) view;
                if (!buttonComa.getText().toString().isEmpty()) {
                    numeroIntroducido_caja.append("0");
                    numeroIntroducido_caja.append(getResources().getString(R.string.decimal));
                }else{
                    numeroIntroducido_caja.append(getResources().getString(R.string.decimal));
                }
                break;
        }

        //todo separación por mil

    }


    //Si se pulsa cualquier boton de operacion,
    //      1. el primerNumeroIntroducido debe pasar a ser numeroMemoria
    //      2. primeroNumero setearlo para coger el segundo numero
    //      --Teniendo el segundo numero introducido--
    //          2.1. Si le da a otra operación: el resultado de la operacion anterior se guarda en el numeroMemoria, se empieza el punto 2. otra vez.
    //          2.2. Si le da al boton igual "=" -> mostrar resultado

    //OPERACIONES
    //TODO DEBE DEVOVLER EL TIPO DE OPERACIÓN
    public void operacionElegida(View view) {
        numeroMemoria_caja.setText(numeroIntroducido_caja.getText());
        numeroIntroducido_caja.setText("");
        if (!(numeroMemoria_caja.getText().toString().matches(""))){
            Button buttonOperacion = (Button) view;
            switch (buttonOperacion.getId()){
                case R.id.btnCalcPlus:
                    numeroMemoria_caja.append("+");
                    break;
                case R.id.btnCalcMinus:
                    numeroMemoria_caja.append("-");
                    break;
                case R.id.btnCalcDivide:
                    numeroMemoria_caja.append("/");
                    break;
                case R.id.btnCalcMultiply:
                    numeroMemoria_caja.append("*");
                    break;
                    /*
                case R.id.btnCalcPercent:
                    //TODO control de resultado
                    break;

                     */
            }
        }
    }
    public void pulsarBotonIgual(View view) {
        //identificar la operacion
        String operacion = String.valueOf(numeroMemoria_caja.getText().toString().charAt(0));
        //quitar la operacion del memoria_caja
        String segundoNumeroSinOperacion = numeroMemoria_caja.getText().toString().substring(1);
        //castear String to Double para hacer los operaciones
        Double primerNumero =  Double.parseDouble(numeroMemoria_caja.getText().toString());
        Double segundoNumero = Double.parseDouble(numeroIntroducido_caja.getText().toString());
        Double resultadosAux = 0.0;
        switch (operacion){
            case "+":
                resultadosAux = primerNumero + segundoNumero;
                break;
            case "-":
                resultadosAux = primerNumero - segundoNumero;
                break;
            case "/":
                resultadosAux = primerNumero / segundoNumero;
                break;
            case "*":
                resultadosAux = primerNumero * segundoNumero;
                break;
        }
        numeroMemoria_caja.setText(String.valueOf(resultadosAux));
        numeroIntroducido_caja.setText("");
    }

    public void borrarCalculadora(View view) {
        numeroMemoria_caja.setText("");
        numeroIntroducido_caja.setText("");
    }

    public void cambiarSigno(View view) {
        Double numero = Double.valueOf(numeroMemoria_caja.getText().toString());
        if (numero != 0){
            if (numero > 0){
                // + --> -
                numero = numero * (-1);
            } else if (numero < 0){
                // - --> +
                numero = numero * (-1);
            }
        }
    }

    public void calcularPorcentaje(View view) {
        Double numero = Double.valueOf(numeroMemoria_caja.getText().toString());
        if (numero > 100) {
            numero = numero / 100;
        }
    }

    public void borrarMemoria(View view) {
        numeroMemoria = 0.0;
        textView_simbolo_memoria.setText("");
    }
}
