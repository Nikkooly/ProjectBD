package bstu.fit.yarmolik.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import bstu.fit.yarmolik.myapplication.workWithBd.DBHelper;

public class ChangeAudit extends AppCompatActivity  {
    public static DBHelper dbHelper;
    EditText number_corps, number1, capacity;
    Spinner type_audit;
    Button change;
    Cursor c;
    String name="",projector="",interactive="";
    String check1="", check2="";
    CheckBox project,interact;
    ArrayList<String> type;
String number,num,corp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        number= getIntent().getExtras().getString("content");
        num= number.substring(0,number.lastIndexOf('-'));
        corp=number.substring(number.lastIndexOf('-')+1,number.length());
        init();
        LoadFaculty();
        Load("select * from audience where number="+num+" and number_corps="+"'"+corp+"'","capacity","projector","interactive");

    }
    public void LoadFaculty(){
        Spinner spinner = (Spinner) findViewById(R.id.type_audit1);
        LoadData("Select * from type",type,"type",spinner);
    }
    public void LoadData(String query, ArrayList<String> arraylist, String name_of_column, Spinner spinner){
        dbHelper = new DBHelper(this, "project.db", null, 1);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        c = database.rawQuery(query,new String[] {});
        c.moveToFirst();
        arraylist = new ArrayList<String>();
        while (!c.isAfterLast()) {
            String name= c.getString(c.getColumnIndex(name_of_column));
            c.moveToNext();
            arraylist.add(name);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraylist);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
    private void Load(String query, String name_of_column, String projector, String interactive) {
        dbHelper = new DBHelper(this, "project.db", null, 1);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        c = database.rawQuery(query, new String[]{});
        c.moveToFirst();
        if (c.moveToFirst()) {
            do {
                {
                    capacity.setText(c.getString(4));
                    if (c.getString(5).equals("yes")) {
                        project.setChecked(true);
                    } else {
                        project.setChecked(false);
                    }
                }
                {
                    if (c.getString(6).equals("yes")) {
                        interact.setChecked(true);
                    } else {
                        interact.setChecked(false);
                    }
                }
                Log.d("prop",c.getString(4));
                Log.d("prop",c.getString(5));
                Log.d("prop",c.getString(6));
            }
            while (c.moveToNext());
            c.close();
        }
    }
    public void init(){
        type_audit= findViewById(R.id.type_audit1);
        number_corps=findViewById(R.id.number_corps1);
        number1=findViewById(R.id.number1);
        capacity= findViewById(R.id.capacity1);
        //change=findViewById(R.id.change_audit);
        number_corps.setText(corp);
        number1.setText(num);
        project=findViewById(R.id.projector1);
        interact=findViewById(R.id.interactive1);
        // projector= findViewById(R.id.projector);
        //interactive= findViewById(R.id.interactive);
    }
    public void onCheckboxClicked(View view) {
        CheckBox language = (CheckBox) view;
        boolean checked = language.isChecked();
        switch (view.getId()) {
            case R.id.projector1:
                if (checked)
                    check1 = "yes";
                else
                    check1 = "no";
                Log.d("na", check1);
                break;
            case R.id.interactive1:
                if (checked)
                    check2 = "yes";
                else
                    check2 = "no";
                Log.d("na", check2);
                break;
        }
    }
    public void Change(View view){
        dbHelper.updateData(
                type_audit.getSelectedItem().toString(),
                number_corps.getText().toString(),
                number1.getText().toString(),
                capacity.getText().toString(),
                check1,
                check2,
                num,
                corp
        );
        Log.d("111",type_audit.getSelectedItem().toString());
        Log.d("111",number_corps.getText().toString());
        Log.d("111",number1.getText().toString());
        Log.d("111",capacity.getText().toString());
        Log.d("111",project.getText().toString());
        Log.d("111",check2);
        Log.d("111",num);
        Log.d("111",corp);
        Toast.makeText(getApplicationContext(), "Successfully update", Toast.LENGTH_SHORT).show();
        Intent intent= new Intent(this, Admin.class);
        startActivity(intent);
    }

        //Log.d("check1",check1);
        //Log.d("check2",check2);
}
