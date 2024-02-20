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
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    Button checkResult;
    TextView equationOneText;
    TextView equationTwoText;

    EditText answerX1;
    EditText answerX2;

    Equation equationOne;
    Equation equationTwo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkResult = findViewById(R.id.buttonCheckResult);
        equationOneText = findViewById(R.id.equationOne);
        equationTwoText = findViewById(R.id.equationTwo);
        answerX1 = findViewById(R.id.answerX1);
        answerX2 = findViewById(R.id.answerX2);

        createEquation(100);

        setTextEquation(equationOneText, equationOne);
        setTextEquation(equationTwoText, equationTwo);


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

                    createEquation(100);

                    setTextEquation(equationOneText, equationOne);
                    setTextEquation(equationTwoText, equationTwo);

                    answerX1.setText("");
                    answerX2.setText("");

                }else{
                    Toast.makeText(MainActivity.this, "Введены неправильные значения", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void createEquation(int coefficient){
        equationOne = new Equation(coefficient);
        equationTwo = new Equation(coefficient);

        findResultX();
    }

    private void findResultX(){
        int x1 = Math.round(
                (float) (equationOne.getC() * equationTwo.getB() - equationTwo.getC() * equationOne.getB()) /
                        (equationOne.getA()*equationTwo.getB() - equationTwo.getA()*equationOne.getB())
        );
        int x2 = Math.round(
                (float) (equationOne.getC()*equationTwo.getA() - equationTwo.getC()*equationOne.getA()) /
                           (equationOne.getB()*equationTwo.getA() - equationTwo.getB()*equationOne.getA())
        );

        Equation.setX(x1, x2);
    }

    private void setTextEquation(TextView text, Equation equation){
        text.setText(String.format(Locale.ENGLISH,"%dX1 + %dX2 = %d",
                equation.getA(),
                equation.getB(),
                equation.getC()));
    }
}
