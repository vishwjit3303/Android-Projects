package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText display;
    private  String currentNumber = "";
    private  String operator = "";
    private  double operand1 = 0.0;
    private  double operand2 = 0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.editText);

        //setup button click listeners
        findViewById(R.id.btn11).setOnClickListener(this::onButtonClick);
        findViewById(R.id.btn12).setOnClickListener(this::onButtonClick);
        findViewById(R.id.btn13).setOnClickListener(this::onButtonClick);
        findViewById(R.id.btn14).setOnClickListener(this::onButtonClick);
        findViewById(R.id.btn21).setOnClickListener(this::onButtonClick);
        findViewById(R.id.btn22).setOnClickListener(this::onButtonClick);
        findViewById(R.id.btn23).setOnClickListener(this::onButtonClick);
        findViewById(R.id.btn24).setOnClickListener(this::onButtonClick);
        findViewById(R.id.btn31).setOnClickListener(this::onButtonClick);
        findViewById(R.id.btn32).setOnClickListener(this::onButtonClick);
        findViewById(R.id.btn33).setOnClickListener(this::onButtonClick);
        findViewById(R.id.btn34).setOnClickListener(this::onButtonClick);
        findViewById(R.id.btn41).setOnClickListener(this::onButtonClick);
        findViewById(R.id.btn42).setOnClickListener(this::onButtonClick);
        findViewById(R.id.btn43).setOnClickListener(this::onButtonClick);
        findViewById(R.id.btn44).setOnClickListener(this::onButtonClick);
        findViewById(R.id.btn51).setOnClickListener(this::onButtonClick);
        findViewById(R.id.btn52).setOnClickListener(this::onButtonClick);
        findViewById(R.id.btn53).setOnClickListener(this::onButtonClick);

    }

    private void onButtonClick (View view){
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        switch (buttonText) {
            case "AC":
                clear();
                break;
            case "=":
                calculate();
                break;
            case "DEL":
                del(view);
                break;
            case "+":
            case "-":
            case "x":
            case "/":
            case "%":

                setOperator(buttonText);
                break;
            default:
                appendNumber(buttonText);
                break;
        }
    }
    public void clear(){
        currentNumber = "";
        operator = "";
        operand1 = 0.0;
        operand2 = 0.0;
        display.setText("0");
    }
    public void del(View view) {
        String current = display.getText().toString();
        if (!currentNumber.isEmpty()) {
            currentNumber = currentNumber.substring(0, currentNumber.length() - 1);
            display.setText(currentNumber);
        }
    }
    private void setOperator(String op){
        operator = op;
        operand1 = Double.parseDouble(currentNumber);
        currentNumber = "";
    }
    private  void appendNumber(String number){
        currentNumber +=number;
        display.setText(currentNumber);
    }
    private  void calculate(){
        operand2 = Double.parseDouble(currentNumber);

        double result;
        switch (operator){

            case "+":
                result = operand1 + operand2;
                break;

            case  "-":
                result = operand1 - operand2;
                break;

            case "x":
                result = operand1 * operand2;
                break;

            case "/":
                result = operand2 != 0 ? operand1 / operand2 : Double.NaN;
                break;

            case "%":
                result = operand1 % operand2;
                break;

            default:
                result = 0.0;
            break;

        }

        display.setText(String.valueOf(result));
        currentNumber = String.valueOf(result);
    }
}