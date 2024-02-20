package com.example.systemofequations;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    Button checkResult;
    TextView equationText;

    EditText answerX1;
    EditText answerX2;

    Equation equation;

    final int COEFFICIENT = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkResult = findViewById(R.id.buttonCheckResult);
        equationText = findViewById(R.id.equationOne);
        answerX1 = findViewById(R.id.answerX1);
        answerX2 = findViewById(R.id.answerX2);

        createEquation(COEFFICIENT);

        setTextEquation(equationText, equation);


        answerX1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String result = answerX1.getText().toString();
                if(!hasFocus && !result.isEmpty()){
                    if(Integer.parseInt(result) != Equation.getX1()){
                        answerX1.setTextColor(Color.RED);
                    }
                    else{
                        answerX1.setTextColor(0xFF1B737E);
                    }
                } else if (!hasFocus && result.isEmpty()) {
                    answerX1.setHintTextColor(Color.RED);
                }
            }
        });

        answerX2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String result = answerX2.getText().toString();
                if(!hasFocus && !result.isEmpty()){
                    if(Integer.parseInt(result) != Equation.getX2()){
                        answerX2.setTextColor(Color.RED);
                    }
                    else{
                        answerX2.setTextColor(0xFF1B737E);
                    }
                } else if (!hasFocus && result.isEmpty()) {
                    answerX2.setHintTextColor(Color.RED);
                }
            }
        });

        checkResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result1 = answerX1.getText().toString();
                String result2 = answerX2.getText().toString();
                if(result1.isEmpty() || result2.isEmpty()){
                    Toast.makeText(MainActivity.this, "Заполните поля ответов", Toast.LENGTH_LONG).show();

                } else if (Equation.checkResult(Integer.parseInt(result1), Integer.parseInt(result2))) {
                    Toast.makeText(MainActivity.this, "Ответ правильный", Toast.LENGTH_LONG).show();

                    createEquation(COEFFICIENT);

                    setTextEquation(equationText, equation);

                    answerX1.setText("");
                    answerX2.setText("");

                }else{
                    Toast.makeText(MainActivity.this, "Введены неправильные значения", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void createEquation(int coefficient){
        equation = new Equation(coefficient);

        findResultX();
    }

    private void findResultX(){
        double discriminant = Math.sqrt(Math.pow(equation.getB(),2) - 4 * equation.getA() * equation.getC());
        if(discriminant >= 0) {
            int x1 = (int) Math.round((-equation.getB() + discriminant) / (2 * equation.getA()));
            int x2 = (int) Math.round((-equation.getB() - discriminant) / (2 * equation.getA()));

            Equation.setX(x1, x2);
        }
        else{
            createEquation(COEFFICIENT);
        }
    }

    private void setTextEquation(TextView text, Equation equation){
        text.setText(String.format(Locale.ENGLISH,"%dX1^2 + %dX2 +%d = 0",
                equation.getA(),
                equation.getB(),
                equation.getC()));

    }
}
