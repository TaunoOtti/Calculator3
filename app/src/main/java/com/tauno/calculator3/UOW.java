package com.tauno.calculator3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


/**
 * Created by Tauno on 10.04.2016.
 */
public class UOW {

    // Database fields
    private static SQLiteDatabase database;
    private static MySQLiteHelper dbHelper;

    private final Context context;

    public DayStatisticRepo dayStatisticRepo;
    public OperandTypeRepo operandTypeRepo;
    public OperationRepo operationRepo;

    public UOW(Context context){
        this.context = context;

        dbHelper = new MySQLiteHelper(context);
        database = dbHelper.getWritableDatabase();


        operandTypeRepo = new OperandTypeRepo(database, dbHelper.TABLE_OPERANDTYPE, dbHelper.ALLCOLUMNS_OPERANDTYPE);
        dayStatisticRepo = new DayStatisticRepo(database, dbHelper.TABLE_DAYSTATISTIC, dbHelper.ALLCOLUMNS_DAYSTATISTIC, operandTypeRepo);
        operationRepo = new OperationRepo(database, dbHelper.TABLE_OPERATION, dbHelper.ALLCOLUMNS_OPERATION, operandTypeRepo);
    }

    public void DropCreateDatabase(){
        dbHelper.dropCreateDatabase(database);
    }

    public void SeedData(){
        if(operationRepo.getAll().isEmpty()){
            OperandType plus = operandTypeRepo.add(new OperandType("+", 0));
            OperandType minus = operandTypeRepo.add(new OperandType("-", 0));
            OperandType divide = operandTypeRepo.add(new OperandType("/", 0));
            OperandType multiply = operandTypeRepo.add(new OperandType("*", 0));
        }

    }

    public void addToStatistic(String op, Double n1, Double n2, Double ans){
        OperandType oper = operandTypeRepo.getOperandType(op);
        oper.setCounter(oper.getCounter() + 1);
        operandTypeRepo.update(oper);

        dayStatisticRepo.addOneToDayCounter(oper.getId());

        Operation operation = new Operation();
        operation.setOperandId(oper.getId());
        operation.setNum1(n1);
        operation.setNum2(n2);
        operation.setRes(ans);
        operation.setTimeStamp(System.currentTimeMillis());
        operationRepo.add(operation);
    }

}
