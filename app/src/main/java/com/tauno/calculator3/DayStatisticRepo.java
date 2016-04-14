package com.tauno.calculator3;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Tauno on 10.04.2016.
 */
public class DayStatisticRepo extends Repo<DayStatistic> {

    private OperandTypeRepo operandTypeRepo;

    public DayStatisticRepo(SQLiteDatabase database, String tableName, String[] allColumns, OperandTypeRepo operandTypeRepo){
        super(database, tableName, allColumns);
        this.operandTypeRepo = operandTypeRepo;
    }

    @Override
    public DayStatistic cursorToEntity(Cursor cursor) {

        DayStatistic stat = new DayStatistic();
        stat.setId(cursor.getLong(0));
        stat.setDayStamp(cursor.getLong(1));
        stat.setDayCounter(cursor.getLong(2));
        stat.setOperandId(cursor.getLong(3));
        stat.setOperand(operandTypeRepo.getOperandforResult(stat.getOperandId()));
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

    private long getDateStamp() {
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return Long.parseLong(sdf.format(date));
    }

    public Cursor getForOperand(long operandId){
        Cursor cursor = getDatabase().query(getTableName(),
                getAllColumns(), "operandId = " + operandId, null, null, null, null);
        cursor.moveToFirst();
        return cursor;
    }

    public DayStatistic addOneToDayCounter(long id) {
        DayStatistic newEntity;
        long dayStamp=getDateStamp();
        Cursor cursor = database.query(tableName,
                allColumns, allColumns[3] + " = "+ id +" and " + allColumns[1] +" = "+dayStamp ,
                null, null, null, null);

        if (cursor == null || cursor.getCount()<1) {
            DayStatistic stat = new DayStatistic();
            stat.setDayCounter(1);
            stat.setOperandId(id);
            stat.setDayStamp(dayStamp);
            newEntity = add(stat);
        } else {
            cursor.moveToFirst();
            newEntity = cursorToEntity(cursor);
            newEntity.setDayCounter(newEntity.getDayCounter() + 1);
            update(newEntity);
        }

        return newEntity;
    }


}