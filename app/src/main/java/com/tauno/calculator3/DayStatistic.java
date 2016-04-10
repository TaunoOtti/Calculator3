package com.tauno.calculator3;

/**
 * Created by Tauno on 3.04.2016.
 */
public class DayStatistic implements IEntity {

    private long _id;
    private int _dayStamp;
    private int _dayCounter;
    private long operandId;

    public DayStatistic(){

    }

    public DayStatistic(int dayStamp, long operandId,int dayCounter){
        setDayStamp(dayStamp);
        setDayCounter(dayCounter);
        setOperandId(operandId);
    }

    public long getId(){
        return this._id;
    }

    public void setId(long id){
        this._id = id;
    }

    public int getDayStamp(){
        return this._dayStamp;
    }

    public void setDayStamp(int dayStamp){
        this._dayStamp = dayStamp;
    }

    public long getOperandId(){
        return this.operandId;
    }

    public void setOperandId(long operandId){
        this.operandId = operandId;
    }

    public int getDayCounter(){
        return this._dayCounter;
    }

    public void setDayCounter(int dayCounter){
        this._dayCounter = dayCounter;
    }
}

