package com.tauno.calculator3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Tauno on 3.04.2016.
 */
public class DayStatistic implements IEntity {

    private long _id;
    private long _dayStamp;
    private long _dayCounter;
    private long operandId;

    public String _operand;

    public DayStatistic(){

    }

    public DayStatistic(long dayStamp, long operandId,long dayCounter){
        setDayStamp(dayStamp);
        setDayCounter(dayCounter);
        setOperandId(operandId);
    }

    public String getOperand() {
        return this._operand;
    }

    public void setOperand(String operand) {
        this._operand = operand;
    }
    public long getId(){
        return this._id;
    }

    public void setId(long id){
        this._id = id;
    }

    public long getDayStamp(){
        return this._dayStamp;
    }

    public void setDayStamp(long dayStamp){
        this._dayStamp = dayStamp;
    }

    public long getOperandId(){
        return this.operandId;
    }

    public void setOperandId(long operandId){
        this.operandId = operandId;
    }

    public long getDayCounter(){
        return this._dayCounter;
    }

    public void setDayCounter(long dayCounter){
        this._dayCounter = dayCounter;
    }

    @Override
    public String toString() {
        return "Date: \"" + dayStampToDate(getDayStamp()) + "\" \nOperand is: "+getOperand()+" Count: "+getDayCounter();
    }

    private String dayStampToDate(long l) {
        SimpleDateFormat inFormatter = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat outFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = inFormatter.parse(String.valueOf(l));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outFormatter.format(date);
    }
}

