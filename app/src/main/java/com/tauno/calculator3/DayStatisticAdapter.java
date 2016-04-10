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
public class DayStatisticAdapter extends CursorAdapter {

    private final LayoutInflater layoutInflater;
    private UOW uow;
    private ViewGroup parentViewGroup;

    public DayStatisticAdapter(Context context, Cursor cursor, UOW uow){
        super(context, cursor, 0);
        layoutInflater = LayoutInflater.from(context);
        this.uow = uow;
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        final View view=layoutInflater.inflate(R.layout.content_main,parent,false);
        parentViewGroup = parent;
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textViewName =(TextView) view.findViewById(R.id.txtView);

        DayStatistic stat = uow.dayStatisticRepo.cursorToEntity(cursor);
        textViewName.setText(stat.getDayCounter());
        //displayContactsView(view, context, person);
    }

    /* private void displayContactsView(View view, Context context, Person person) {
        // get the contactsListView
        LinearLayout contactsListView = (LinearLayout) view.findViewById(R.id.contactsListView);

        // if this gets called multiple times, first clean all up
        // otherwise you will add same childs several times
        contactsListView.removeAllViews();

        for (Contact contact :
                uow.contactRepo.getForPerson(person.getId())) {

            // load the xml structure of your row
            View child = layoutInflater.inflate(R.layout.contact_details,parentViewGroup,false);

            TextView textViewContactValue =(TextView) child.findViewById(R.id.contactValue);
            TextView textViewContactType =(TextView) child.findViewById(R.id.contactType);

            textViewContactValue.setText(contact.getValue());
            textViewContactType.setText(uow.contactTypeRepo.getById(contact.getContactTypeId()).getValue());

            contactsListView.addView(child);
        }


    }*/
}
