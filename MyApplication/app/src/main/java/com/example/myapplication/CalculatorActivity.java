package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {
    TextView input;
    TextView output;
    TextView historyTextView;
    ScrollView scrollView;
    double result;
    String operator;
    StringBuilder currentNumber;
    StringBuilder history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output = findViewById(R.id.editTextNumber2);
        input=findViewById(R.id.textview);
        currentNumber = new StringBuilder();
        result = 0;
        operator = "";
        history = new StringBuilder();
        input.setText("");
    }

    public void onButtonClick(View view) {
        Button btn = (Button) view;
        String btnText = btn.getText().toString();

        switch (btnText) {
            case "AC":
                clearAll();
                break;

            case "Exit":
                finish();
                break;

            case "Clear":
                clearInput();
                break;

            case "=":
                if (!operator.isEmpty()) {
                    displayResult();
                }
                break;

            case "+":
            case "-":
            case "*":
            case "/":
                setOperator(btnText);
                break;

            default:
                appendNumber(btnText);
                break;
        }
    }

    public void clearAll() {
        input.setText("");
        output.setText("0");
        currentNumber.setLength(0);
        result = 0;
        operator = "";
    }

    public void clearInput() {
        input.setText("");
        result = 0;

    }

    public void setOperator(String operator) {
        this.operator = operator;
        result = Double.parseDouble(currentNumber.toString());
        currentNumber.setLength(0);
        input.append(operator);
    }

    private void appendNumber(String number) {
        currentNumber.append(number);
        input.append(number);
    }

    public void displayResult() {
        double number = Double.parseDouble(currentNumber.toString());

        switch (operator) {
            case "+":
                result += number;
                break;
            case "-":
                result -= number;
                break;
            case "*":
                result *= number;
                break;
            case "/":
                if (number != 0) {
                    result /= number;
                } else {
                    input.setText("Error");
                    return;
                }
                break;
        }

        //String equation = output.getText().toString() + " = " + result + "\n";
        output.setText(String.valueOf(result));

        currentNumber.setLength(0);
        currentNumber.append(result);
        operator = "";
    }
}
