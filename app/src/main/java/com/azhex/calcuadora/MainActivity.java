package com.azhex.calcuadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private static TextView segundoNumero_caja;
    private String separacionXMil;
    private static TextView textView_simbolo_memoria;

    /*
    OPERACIONES:

    0 -> nada
    1 -> SUMAR
    2 -> RESTAR
    3 -> MULTIPLICAR
    4 -> DIVIDIR
     */
    private int operacion = 0;
    private double numeroMemoria = 0;
    private double numeroIntroducido = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicialización de textViews
        numeroIntroducido_caja = findViewById(R.id.numeroIntroducido);
        segundoNumero_caja = findViewById(R.id.numeroMemoria);

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
                if (!numeroIntroducido_caja.getText().toString().equals("")){
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
                    numeroIntroducido_caja.append(".");
                }else{
                    numeroIntroducido_caja.append(".");
                }
                break;
        }

        numeroIntroducido = Double.parseDouble(numeroIntroducido_caja.getText().toString());
        numeroIntroducido_caja.setText(numeroIntroducido_caja.getText().toString().replaceAll(
                "[.]", getResources().getString(R.string.decimal)));
    }


    //Si se pulsa cualquier boton de operacion,
    //      1. el primerNumeroIntroducido debe pasar a ser numeroMemoria
    //      2. primeroNumero setearlo para coger el segundo numero
    //      --Teniendo el segundo numero introducido--
    //          2.1. Si le da a otra operación: el resultado de la operacion anterior se guarda en el numeroMemoria, se empieza el punto 2. otra vez.
    //          2.
    // 2. Si le da al boton igual "=" -> mostrar resultado

    //OPERACIONES
    //TODO DEBE DEVOVLER EL TIPO DE OPERACIÓN
    public void operacionElegida(View view) {
        segundoNumero_caja.setText(numeroIntroducido_caja.getText());


        if (!(segundoNumero_caja.getText().toString().isEmpty())){
            Button buttonOperacion = (Button) view;

            numeroMemoria = Double.parseDouble(segundoNumero_caja.getText().toString());

            switch (buttonOperacion.getId()){
                case R.id.btnCalcPlus:
                    operacion = 1;
                    segundoNumero_caja.append("+");
                    break;
                case R.id.btnCalcMinus:
                    operacion = 2;
                    segundoNumero_caja.append("-");
                    break;
                case R.id.btnCalcDivide:
                    operacion = 3;
                    segundoNumero_caja.append("÷");
                    break;
                case R.id.btnCalcMultiply:
                    operacion = 4;
                    segundoNumero_caja.append("*");
                    break;
            }

            numeroIntroducido_caja.setText("");
        }else{
            numeroMemoria = 0;
        }
    }
    public void pulsarBotonIgual(View view) {
        if (operacion != 0){
        numeroIntroducido = Double.parseDouble(numeroIntroducido_caja.getText().toString());

        Double primerNumero =  numeroMemoria;
        Double segundoNumero = numeroIntroducido;

        Double resultadosAux = 0.0;
        switch (operacion){
            case 1:
                resultadosAux = primerNumero + segundoNumero;
                break;
            case 2:
                resultadosAux = primerNumero - segundoNumero;
                break;
            case 3:
                resultadosAux = primerNumero / segundoNumero;
                break;
            case 4:
                resultadosAux = primerNumero * segundoNumero;
                break;
        }
        numeroIntroducido_caja.setText(String.valueOf(resultadosAux));
        segundoNumero_caja.setText("");
        operacion = 0;
        }
    }

    public void borrarCalculadora(View view) {
        segundoNumero_caja.setText("");
        numeroIntroducido_caja.setText("");
    }

    public void cambiarSigno(View view) {
        Double numero = Double.valueOf(segundoNumero_caja.getText().toString());
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
        Double numero = Double.valueOf(segundoNumero_caja.getText().toString());
        if (numero > 100) {
            numero = numero / 100;
        }
    }

    //MC (Memory Clear)
    public void borrarMemoria(View view) {
        numeroMemoria = 0.0;
        textView_simbolo_memoria.setText("");
    }

    /*
   MR (Memory Recall):
   Recupera el número almacenado en memoria. El número permanece en memoria.
    */
    public void llamarMemoria(View view) {


    }

    /*
        M+:
        Suma el número mostrado a otro número que se encuentre en memoria pero no muestra la suma de estos números.
     */
    public void sumarMemoria(View view) {

    }

    /*
        M-:
        Resta el número mostrado a otro número que se encuentre en memoria pero no muestra la resta de estos números.
     */
    public void restarMemoria(View view) {

    }
}
