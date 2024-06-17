package com.example.project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView formula, endResult;
    private Button one, two, three, four, five, six, seven, eight, nine, zero;
    private Button plus, minus, multiple, division, result, sqrt, square, proc;
    private Button delete;
    private double valueFirst = Double.NaN;
    private double valueSecond;
    private char action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        setupView();

        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                formula.setText(formula.getText().toString() + button.getText().toString());
            }
        };
        one.setOnClickListener(numberClickListener);
        two.setOnClickListener(numberClickListener);
        three.setOnClickListener(numberClickListener);
        four.setOnClickListener(numberClickListener);
        five.setOnClickListener(numberClickListener);
        six.setOnClickListener(numberClickListener);
        seven.setOnClickListener(numberClickListener);
        eight.setOnClickListener(numberClickListener);
        nine.setOnClickListener(numberClickListener);
        zero.setOnClickListener(numberClickListener);

        View.OnClickListener actionClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                String currentFormula = formula.getText().toString();

                if (currentFormula.isEmpty() || "+-*/".indexOf(currentFormula.charAt(currentFormula.length() - 1)) != -1) {
                    return;
                }

                calculate();
                action = button.getText().toString().charAt(0);
                formula.setText(String.valueOf(valueFirst) + action);
                endResult.setText(null);
            }
        };

        plus.setOnClickListener(actionClickListener);
        minus.setOnClickListener(actionClickListener);
        multiple.setOnClickListener(actionClickListener);
        division.setOnClickListener(actionClickListener);

        sqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Double.isNaN(valueFirst)) {
                    valueFirst = Math.sqrt(valueFirst);
                    endResult.setText(String.valueOf(valueFirst));
                }
            }
        });

        square.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Double.isNaN(valueFirst)) {
                    valueFirst = Math.pow(valueFirst, 2);
                    endResult.setText(String.valueOf(valueFirst));
                }
            }
        });

        proc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Double.isNaN(valueFirst)) {
                    valueFirst = valueFirst / 100;
                    endResult.setText(String.valueOf(valueFirst));
                }
            }
        });

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
                action = '=';
                endResult.setText(String.valueOf(valueFirst));
                formula.setText(null);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                formula.setText("");
                endResult.setText("0");
                valueFirst = Double.NaN;
                valueSecond = Double.NaN;
            }
        });
    }


    private void setupView() {
        one = findViewById(R.id.One);
        two = findViewById(R.id.Two);
        three = findViewById(R.id.Three);
        four = findViewById(R.id.Four);
        five = findViewById(R.id.Five);
        six = findViewById(R.id.Six);
        seven = findViewById(R.id.Seven);
        eight = findViewById(R.id.Eight);
        nine = findViewById(R.id.Nine);
        zero = findViewById(R.id.Zero);
        plus = findViewById(R.id.Plus);
        minus = findViewById(R.id.Minus);
        multiple = findViewById(R.id.Multiple);
        division = findViewById(R.id.Devision);
        sqrt = findViewById(R.id.Sqrt);
        square = findViewById(R.id.Square);
        proc = findViewById(R.id.Proc);
        result = findViewById(R.id.Result);
        delete = findViewById(R.id.Delete);
        endResult = findViewById(R.id.EndResult);
        formula = findViewById(R.id.Formula);
    }

    private void calculate() {
        if (!Double.isNaN(valueFirst)) {
            String textFormula = formula.getText().toString();
            int indexAction = textFormula.indexOf(action);
            if (indexAction != -1) {
                String number = textFormula.substring(indexAction + 1);
                valueSecond = Double.parseDouble(number);

                switch (action) {
                    case '+':
                        valueFirst += valueSecond;
                        break;
                    case '-':
                        valueFirst -= valueSecond;
                        break;
                    case '*':
                        valueFirst *= valueSecond;
                        break;
                    case '/':
                        if (valueSecond == 0) {
                            valueFirst = 0.0;
                        } else {
                            valueFirst /= valueSecond;
                        }
                        break;
                    case '=':
                        valueFirst = valueSecond;
                        break;
                }
            }

        } else {
            valueFirst = Double.parseDouble(formula.getText().toString());
        }
        endResult.setText(String.valueOf(valueFirst));
        formula.setText("");
    }
}
