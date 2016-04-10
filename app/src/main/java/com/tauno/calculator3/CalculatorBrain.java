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

                    OperandType optype = uow.operandTypeRepo.getOperandType(String.valueOf(operator));
                    optype.setCounter(optype.getCounter() + 1);
                    uow.operandTypeRepo.update(optype);

                    int time = (int) (System.currentTimeMillis());
                    uow.operationRepo.add(new Operation(a,b,savedValue,time,optype.getId()));

                    Calendar cal = Calendar.getInstance();
                    int month = cal.get(Calendar.MONTH);
                    int day = cal.get(Calendar.DATE);
                    int year = cal.get(Calendar.YEAR);
                    int dayStamp = day*1000000 + month*10000 + year;

                    DayStatistic stat = uow.dayStatisticRepo.getOperationInDay(dayStamp, optype.getId());
                    if(stat == null){
                        stat = new DayStatistic(dayStamp, optype.getId(), 1);
                        uow.dayStatisticRepo.add(stat);
                    } else {
                        stat.setDayCounter(stat.getDayCounter()+1);
                        uow.dayStatisticRepo.update(stat);
                    }
                setResultCode(Activity.RESULT_OK);
                setResultData(String.valueOf(savedValue));
                }

            }
        }



    public double round(double a) {
        return (double) Math.round(a * 100.0) / 100.0;
    }
}