package com.tauno.calculator3;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Tauno on 10.04.2016.
 */
public class OperationRepo extends Repo<Operation> {

    public OperationRepo(SQLiteDatabase database, String tableName, String[] allColumns){
        super(database, tableName, allColumns);
    }

    @Override
    public Operation cursorToEntity(Cursor cursor) {
        Operation operation = new Operation();
        operation.setId(cursor.getLong(0));
        operation.setOperandId(cursor.getLong(1));
        operation.setNum1(cursor.getDouble(2));
        operation.setNum2(cursor.getDouble(3));
        operation.setRes(cursor.getDouble(4));
        operation.setTimeStamp(cursor.getInt(5));

        return operation;
    }

    @Override
    public ContentValues entityToContentValues(Operation entity) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(MySQLiteHelper.COLUMN_OPERATION_OPERANDID, entity.getOperandId());
        contentValues.put(MySQLiteHelper.COLUMN_OPERATION_NUM1, entity.getNum1());
        contentValues.put(MySQLiteHelper.COLUMN_OPERATION_NUM2, entity.getNum2());
        contentValues.put(MySQLiteHelper.COLUMN_OPERATION_RES, entity.getRes());
        contentValues.put(MySQLiteHelper.COLUMN_OPERATION_TIMESTAMP, entity.getTimeStamp());


        return contentValues;
    }
}
