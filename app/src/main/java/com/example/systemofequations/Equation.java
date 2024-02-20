package com.example.systemofequations;

import java.util.Random;

public class Equation {
    private int a, b, c;
    private static int x1,x2;

    Equation(int coefficient){
        Random random = new Random();

        a = random.nextInt(coefficient) + 1;
        b = random.nextInt(coefficient) + 1;
        c = random.nextInt(coefficient) + 1;
    }

    public static void setX(int xResult1, int xResult2){
        x1 = xResult1;
        x2 = xResult2;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getC() {
        return c;
    }

    public static boolean checkResult(int answer1, int answer2){
        if(x1 == answer1 && x2 == answer2){
            return true;
        }
        return false;
    }

    public static int getX1(){
        return x1;
    }
    public static int getX2(){
        return x2;
    }
}
