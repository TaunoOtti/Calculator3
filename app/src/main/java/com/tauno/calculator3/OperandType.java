package com.tauno.calculator3;

/**
 * Created by Tauno on 3.04.2016.
 */
public class OperandType implements IEntity {
    private long _id;
    private String operand;
    private long _lifeTimeCounter;

    public OperandType(){

    }

    public OperandType(String operand, int Counter){
        setOperand(operand);
        setCounter(Counter);
    }

    public long getId(){
        return this._id;
    }

    public void setId(long id){
        this._id = id;
    }

    public String getOperand(){
        return this.operand;
    }

    public void setOperand(String operand){ this.operand = operand; }

    public long getCounter(){
        return this._lifeTimeCounter;
    }

    public void setCounter(long counter){
        this._lifeTimeCounter = counter;
    }

    @Override
    public String toString() {
        return "Operand \"" + getOperand() + "\" has been used " + getCounter() + " times";
    }
}
