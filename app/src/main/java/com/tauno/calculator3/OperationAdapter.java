package com.tauno.calculator3;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Tauno on 11.04.2016.
 */
public class OperationAdapter extends CursorAdapter {
    private final LayoutInflater layoutInflater;
    private UOW uow;
    private ViewGroup parentViewGroup;




    public OperationAdapter(Context context, Cursor cursor, UOW uow){
        super(context, cursor, 0);
        layoutInflater = LayoutInflater.from(context);
        this.uow = uow;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        final View view=layoutInflater.inflate(R.layout.day_statistic, parent, false);
        parentViewGroup = parent;
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textViewName =(TextView) view.findViewById(R.id.name);

        Operation op = uow.operationRepo.cursorToEntity(cursor);
        textViewName.setText(op.toString());
    }
}
