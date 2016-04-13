package com.tauno.calculator3;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Tauno on 11.04.2016.
 */
public class CalculatorBrain extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        UOW uow = new UOW(context);
        double savedValue = 0;
        if(isOrderedBroadcast()){
            Bundle extras = intent.getExtras();
            if(extras != null){
                char operator = extras.getChar("operator");
                double a = extras.getDouble("number1");
                double b = extras.getDouble("number2");

                switch (operator) {
                    case '+':
                        savedValue = a + b;
                        break;
                    case '-':
                        savedValue = a - b;
                        break;
                    case '*':
                        savedValue = a * b;
                        break;
                    case '/':
                        savedValue = a / b;
                        break;
                }
                    savedValue = round(savedValue);
                    uow.addToStatistic(String.valueOf(operator), a, b, savedValue);
                }
            }
        setResultCode(Activity.RESULT_OK);
        setResultData(String.valueOf(savedValue));
        }



    public double round(double a) {
        return (double) Math.round(a * 100.0) / 100.0;
    }
}