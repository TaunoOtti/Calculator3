package com.tauno.calculator3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private TextView textView;
    private UOW uow;
    private DayStatisticAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        uow = new UOW(getApplicationContext());

        listView = (ListView) findViewById(R.id.list);
        textView = (TextView) findViewById(R.id.text);

        //uow.DropCreateDatabase();
        uow.SeedData();
        displayOperatorsListView();
    }

    private void displayOperandsListView() {
        String s = "Statistic with operands";
        textView.setText(s);
        OperandAdapter adapter = new OperandAdapter(this, uow.operandTypeRepo.getCursorAll(), uow);
        listView.setAdapter(adapter);
    }

    private void displayDayStatsListView() {
        String s = "Day statistic";
        textView.setText(s);
        DayStatisticAdapter adapter = new DayStatisticAdapter(this, uow.dayStatisticRepo.getCursorAll(), uow);
        listView.setAdapter(adapter);
    }

    private void displayOperatorsListView() {
        String s = "Statistic";
        textView.setText(s);
        OperationAdapter adapter = new OperationAdapter(this, uow.operationRepo.getCursorAll(), uow);
        listView.setAdapter(adapter);
    }

    public void refreshClicked() {
        refreshActivity();
    }

    private void refreshActivity() {
        displayOperandsListView();
        displayDayStatsListView();
        displayOperatorsListView();
    }

    public void deleteClicked() {
        new AlertDialog.Builder(this)
                .setTitle("REALLY???")
                .setMessage("Do you really want to delete whole database?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        uow.DropCreateDatabase();
                        Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
                        refreshActivity();
                    }})
                .setNegativeButton(android.R.string.no, null).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete) {
            deleteClicked();
            uow.SeedData();
        } else if (id == R.id.action_refresh) {
            refreshClicked();
        } else if (id == R.id.action_show_history) {
            displayOperatorsListView();
        } else if (id == R.id.action_show_daysstats) {
            displayDayStatsListView();
        } else if (id == R.id.action_show_operandstats) {
            displayOperandsListView();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshClicked();
    }

    @Override
    protected void onStart() {
        super.onStart();
       refreshClicked();
    }
}
