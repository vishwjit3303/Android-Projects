package com.example.numberconverter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.spinner);
        EditText edittext = findViewById(R.id.edittext);
        Button button = findViewById(R.id.btn);
        TextView textview = findViewById(R.id.textview);

        ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.conversion_options,
                android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        button.setOnClickListener(v ->{
            String input = edittext.getText().toString();
            String selectedOption = spinner.getSelectedItem().toString();

            switch (selectedOption) {
                case "Decimal": {
                    int decimal = Integer.parseInt(input);
                    textview.setText("Binary: "+decimalToBinary(decimal)+"\n" + "Octal: "+ decimalToOctal(decimal) +"\n"+ "Hexadecimal: "+ decimalToHexadecimal(decimal));

                    break;
                }

                case "Binary": {
                    int decimal = binaryToDecimal(input);
                    textview.setText("Decimal: " + decimal + "\n" + "Octal: " + decimalToOctal(decimal) + "\n" + "Hexadecimal: " + decimalToHexadecimal(decimal));
                    break;
                }
                case "Octal": {
                    int decimal = octalToDecimal(input);
                    textview.setText("Decimal: " + decimal + "\n"
                            + "Binary: " + decimalToBinary(decimal) + "\n"
                            + "Hexadecimal: " + decimalToHexadecimal(decimal));
                    break;
                }
                case "Hexadecimal": {
                    int decimal = hexadecimalToDecimal(input);
                    textview.setText("Decimal: " + decimal + "\n"
                            + "Binary: " + decimalToBinary(decimal) + "\n"
                            + "Octal: " + decimalToOctal(decimal));
                    break;
                }
            }
        });
    }

    private String decimalToBinary(int decimal) {
        return Integer.toBinaryString(decimal);
    }

    private String decimalToOctal(int decimal) {
        return Integer.toOctalString(decimal);
    }

    private String decimalToHexadecimal(int decimal) {
        return Integer.toHexString(decimal).toUpperCase();
    }

    private int binaryToDecimal(String binary) {
        return Integer.parseInt(binary,2);
    }
    private int octalToDecimal(String octal) {
        return Integer.parseInt(octal,8);

    }

    private int hexadecimalToDecimal(String hexadecimal) {
        return Integer.parseInt(hexadecimal,16);
    }
}