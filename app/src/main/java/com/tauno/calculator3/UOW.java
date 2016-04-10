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

        dayStatisticRepo = new DayStatisticRepo(database, dbHelper.TABLE_DAYSTATISTIC, dbHelper.ALLCOLUMNS_DAYSTATISTIC);
        operandTypeRepo = new OperandTypeRepo(database, dbHelper.TABLE_OPERANDTYPE, dbHelper.ALLCOLUMNS_OPERANDTYPE);
        operationRepo = new OperationRepo(database, dbHelper.TABLE_OPERATION, dbHelper.ALLCOLUMNS_OPERATION);
    }

    public void DropCreateDatabase(){
        dbHelper.dropCreateDatabase(database);
    }

    public void SeedData(){

        OperandType plus = operandTypeRepo.add(new OperandType("+", 0));
        OperandType minus = operandTypeRepo.add(new OperandType("-", 0));
        OperandType divide = operandTypeRepo.add(new OperandType("/", 0));
        OperandType multiply = operandTypeRepo.add(new OperandType("*", 0));

        Operation op = operationRepo.add(new Operation(1,2,0.5,111111, plus.getId()));
        Operation op1 = operationRepo.add(new Operation(1,3,0.5,111111, minus.getId()));
        Operation op2 = operationRepo.add(new Operation(1,4,0.5,111111, multiply.getId()));
        Operation op3 = operationRepo.add(new Operation(1,5,0.5,111111, divide.getId()));
        Operation op4 = operationRepo.add(new Operation(1,6,0.5,111111, plus.getId()));

        operandTypeRepo.add(plus);
        operandTypeRepo.add(minus);
        operandTypeRepo.add(divide);
        operandTypeRepo.add(multiply);

        operationRepo.add(op);
        operationRepo.add(op1);
        operationRepo.add(op2);
        operationRepo.add(op3);
        operationRepo.add(op4);

    }

}
