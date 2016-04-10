package com.tauno.calculator3;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


/**
 * Created by Tauno on 10.04.2016.
 */
public class OperandTypeRepo extends Repo<OperandType> {

    public OperandTypeRepo(SQLiteDatabase database, String tableName, String[] allColumns){
        super(database, tableName, allColumns);
    }

    @Override
    public OperandType cursorToEntity(Cursor cursor) {
        OperandType operandType = new OperandType();
        operandType.setId(cursor.getLong(0));
        operandType.setOperand(cursor.getString(1));
        operandType.setCounter(cursor.getInt(2));

        return operandType;
    }

    @Override
    public ContentValues entityToContentValues(OperandType entity) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MySQLiteHelper.COLUMN_OPERANDTYPE_OPERAND, entity.getOperand());
        contentValues.put(MySQLiteHelper.COLUMN_OPERANDTYPE_LIFETIMECOUNTER, entity.getCounter());

        return contentValues;
    }

    public OperandType getOperandType(String type){
        Cursor cursor = getDatabase().query(getTableName(),
                getAllColumns(), "operand = '" + type+ "'", null, null, null, null);
        OperandType optype = null;
        if(cursor.moveToFirst()){
            optype = cursorToEntity(cursor);
        }
        // make sure to close the cursor
        cursor.close();
        return optype;
    }
}
