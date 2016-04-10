package com.tauno.calculator3;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Tauno on 10.04.2016.
 */
public class DayStatisticRepo extends Repo<DayStatistic> {

    public DayStatisticRepo(SQLiteDatabase database, String tableName, String[] allColumns){
        super(database, tableName, allColumns);
    }

    @Override
    public DayStatistic cursorToEntity(Cursor cursor) {
        DayStatistic stat = new DayStatistic();
        stat.setId(cursor.getLong(0));
        stat.setDayStamp(cursor.getInt(1));
        stat.setDayCounter(cursor.getInt(2));
        stat.setOperandId(cursor.getLong(3));
        return stat;
    }

    @Override
    public ContentValues entityToContentValues(DayStatistic stat) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(MySQLiteHelper.COLUMN_DAYSTATISTIC_DAYSTAMP, stat.getDayStamp());
        contentValues.put(MySQLiteHelper.COLUMN_DAYSTATISTIC_DAYCOUNTER, stat.getDayCounter());
        contentValues.put(MySQLiteHelper.COLUMN_DAYSTATISTIC_OPERANDID, stat.getOperandId());

        return contentValues;
    }

    public DayStatistic getOperationInDay(int dayStamp, long operation) {
        Cursor cursor = getDatabase().query(getTableName(),
                getAllColumns(), "operandId = " + operation + " and dayStamp = " + dayStamp, null,
                null, null, null);
        DayStatistic statistics = null;
        if(cursor.moveToFirst()){
            statistics = cursorToEntity(cursor);
        }
        // make sure to close the cursor
        cursor.close();
        return statistics;
    }

}
