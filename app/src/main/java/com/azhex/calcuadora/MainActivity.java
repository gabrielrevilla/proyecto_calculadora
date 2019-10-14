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
    public static String numeroIntroducido = "";
    public static String numeroMemoria;

    private static TextView numeroIntroducido_caja;
    private static TextView numeroMemoria_caja;
    private String separacionXMil;
    private int btnOperacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicialización de textViews
        numeroIntroducido_caja = findViewById(R.id.numeroIntroducido);
        numeroMemoria_caja = findViewById(R.id.numeroMemoria);

        //buscar en los recursos: coma o punto
        separacionXMil =  getResources().getString(R.string.decimal);

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
                if (!numeroIntroducido.isEmpty()){
                    numeroIntroducido = numeroIntroducido+0;
                }
                break;
            case R.id.btnCalc1:
                numeroIntroducido = numeroIntroducido+1;
                break;
            case R.id.btnCalc2:
                numeroIntroducido = numeroIntroducido+2;
                break;
            case R.id.btnCalc3:
                numeroIntroducido = numeroIntroducido+3;
                break;
            case R.id.btnCalc4:
                numeroIntroducido = numeroIntroducido+4;
                break;
            case R.id.btnCalc5:
                numeroIntroducido = numeroIntroducido+5;
                break;
            case R.id.btnCalc6:
                numeroIntroducido = numeroIntroducido+6;
                break;
            case R.id.btnCalc7:
                numeroIntroducido = numeroIntroducido+7;
                break;
            case R.id.btnCalc8:
                numeroIntroducido = numeroIntroducido+8;
                break;
            case R.id.btnCalc9:
                numeroIntroducido = numeroIntroducido+9;
                break;
        }
        String numeroConComas = Utiles.addCommasToNumericString(numeroIntroducido, separacionXMil);
        numeroIntroducido_caja.setText(numeroConComas);

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
        if (!(numeroIntroducido.equals("") && numeroMemoria.equals(""))){
            Button buttonOperacion = (Button) view;
            switch (buttonOperacion.getId()){
                case R.id.btnCalcPlus:
                    numeroMemoria  = numeroIntroducido_caja.getText().toString();
                    btnOperacion = R.id.btnCalcPlus;
                    break;
                case R.id.btnCalcMinus:
                    numeroMemoria  = numeroIntroducido_caja.getText().toString();
                    btnOperacion = R.id.btnCalcMinus;
                    break;
                case R.id.btnCalcDivide:
                    numeroMemoria  = numeroIntroducido_caja.getText().toString();
                    btnOperacion = R.id.btnCalcMultiply;
                    break;
                case R.id.btnCalcMultiply:
                    numeroMemoria  = numeroIntroducido_caja.getText().toString();
                    btnOperacion = R.id.btnCalcDivide;
                    break;
                case R.id.btnCalcAc:
                    break;
                case R.id.btnCalcPlusMinus:
                    break;
                case R.id.btnCalcPercent:
                    //TODO control de resultado
                    break;
            }
            numeroIntroducido_caja.setText("");
            numeroMemoria_caja.setText(numeroMemoria);
        }
    }
    public void pulsarBotonIgual(View view) {
        //castear String to Double para hacer los operaciones
        Double primerNumero =  Double.parseDouble(numeroMemoria_caja.getText().toString());
        Double segundoNumero = Double.parseDouble(numeroMemoria_caja.getText().toString());
        Double resultadosAux = 0.0;

        switch (btnOperacion){
            case R.id.btnCalcPlus:
                resultadosAux = primerNumero + segundoNumero;
                break;
            case R.id.btnCalcMinus:
                resultadosAux = primerNumero - segundoNumero;
                break;
            case R.id.btnCalcDivide:
                resultadosAux = primerNumero / segundoNumero;
                break;
            case R.id.btnCalcMultiply:
                resultadosAux = primerNumero * segundoNumero;
                break;
            //------------------------
            case R.id.btnCalcPercent:
                //TODO

                break;
            case R.id.btnCalcPlusMinus:
                if (segundoNumero  < 0){ //negativo
                    resultadosAux = 0+segundoNumero;
                } else{ //positivo
                    resultadosAux = 0-segundoNumero;
                }
                break;
            case R.id.btnCalcAc:
                break;

        }
        numeroMemoria = String.valueOf(resultadosAux);
        numeroMemoria_caja.setText(String.valueOf(resultadosAux ));
        numeroIntroducido = "";
        numeroIntroducido_caja.setText("");
    }

    public void escribirDecimal(View view) {
        Button buttonComa = (Button) view;
        if (!buttonComa.getText().toString().isEmpty()){
            buttonComa.append("0");
            buttonComa.append(separacionXMil);
        }else if (buttonComa.getText().toString().indexOf(separacionXMil) < 0){
            buttonComa.append(separacionXMil);
        }
    }


}
