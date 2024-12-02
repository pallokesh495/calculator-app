package com.pallokesh495.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    private TextView display;
    private double firstOperand = 0, secondOperand = 0;
    private String operator = "";
    private boolean isOperatorClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_main);


        display = findViewById(R.id.textView2);

        // Set up button click listeners
        int[] numberButtonIds = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        };

        for (int id : numberButtonIds) {
            findViewById(id).setOnClickListener(this::onNumberClick);
        }

        findViewById(R.id.btnAdd).setOnClickListener(v -> onOperatorClick("+"));
        findViewById(R.id.btnSub).setOnClickListener(v -> onOperatorClick("-"));
        findViewById(R.id.btnMul).setOnClickListener(v -> onOperatorClick("x"));
        findViewById(R.id.btnDevide).setOnClickListener(v -> onOperatorClick("/"));

        findViewById(R.id.btnResult).setOnClickListener(v -> calculateResult());
        findViewById(R.id.btnClear).setOnClickListener(v -> clearCalculator());
    }

    private void onNumberClick(View view) {
        Button button = (Button) view;
        String currentText = display.getText().toString();


        if (isOperatorClicked) {
            currentText = "";
            isOperatorClicked = false;
        }

        String newText = currentText.equals("0") ? button.getText().toString() : currentText + button.getText().toString();
        display.setText(newText);
    }

    private void onOperatorClick(String selectedOperator) {
        firstOperand = Double.parseDouble(display.getText().toString());
        operator = selectedOperator;
        display.setText(display.getText().toString() + operator);
        isOperatorClicked = true;
    }

    private void calculateResult() {
        try {

            String displayText = display.getText().toString();
            int operatorIndex = displayText.indexOf(operator);
            secondOperand = Double.parseDouble(displayText.substring(operatorIndex + 1));

            double result = 0;
            switch (operator) {
                case "+":
                    result = firstOperand + secondOperand;
                    break;
                case "-":
                    result = firstOperand - secondOperand;
                    break;
                case "x":
                    result = firstOperand * secondOperand;
                    break;
                case "/":
                    if (secondOperand != 0) {
                        result = firstOperand / secondOperand;
                    } else {
                        display.setText("Error");
                        return;
                    }
                    break;
            }

            String ans;
            ans = Double.toString(result);
            display.setText(ans);

        } catch (Exception e) {
            display.setText("Error");
        }


        operator = "";
        isOperatorClicked = false;
    }

    private void clearCalculator() {
        display.setText("0");
        firstOperand = 0;
        secondOperand = 0;
        operator = "";
        isOperatorClicked = false;
    }
}
