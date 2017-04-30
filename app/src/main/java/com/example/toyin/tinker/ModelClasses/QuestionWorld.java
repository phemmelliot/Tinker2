package com.example.toyin.tinker.ModelClasses;

import java.math.BigDecimal;

/**
 * Created by Toyin on 28/12/2016.
 */
public class QuestionWorld {
    public double number1, number2;
    public double result;
    private String output;


    public double getResult(){
        return result;
    }
    public void setQestion(int time){
        number1 = (Math.random() * 10);
        number2 = (Math.random() * 10);
        //Add increment of 1 to each number in case any of them is 0.
        number1 = number1 + 1;
        number1 = round(number1, 2);
        number2 = number2 + 1;
        number2 = round(number2, 2);
        if (number1 < number2 && time < 5) {
            output = "What is " + number1 + " + " + number2;
            result = number1 + number2;
            result = round(result, 2);
        }
        else if (((number1 < number2)|| (number1 >= number2)) && time >= 10) {
            output = "What is " + number1 + " x " + number2;
            result = number1 * number2;
            result = round(result, 2);
        }
        else if(((number1 < number2) || (number1 >= number2)) && time >= 20){
            output = "What is " + number1 + " exp(" + number2 + ")";
            result = Math.pow(number1, number2);
            result = round(result, 2);
        }
        else if (number1 == number2) {
            number1 = (number2 * (int) (Math.random() * 10)) - (int) (Math.random() * 10);
            output = "What is " + number1 + " / " + number2;
            result = number1 / number2;
            result = round(result, 2);
        } else {
            output = "What is " + number1 + " - " + number2;
            result = number1 - number2;
            result = round(result, 2);
        }
    }
    public String getOutput(){
        return output;
    }

    public static double round(double d, int dp){
        BigDecimal bigDecimal = new BigDecimal(Double.toString(d));
        bigDecimal = bigDecimal.setScale(dp, BigDecimal.ROUND_HALF_UP);
        return bigDecimal.doubleValue();
    }
}
