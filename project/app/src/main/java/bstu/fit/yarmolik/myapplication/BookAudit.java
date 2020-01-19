package bstu.fit.yarmolik.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import bstu.fit.yarmolik.myapplication.startPage.Login;
import bstu.fit.yarmolik.myapplication.workWithBd.DBHelper;

public class BookAudit extends AppCompatActivity {
    String[] times = {"8.00-9.35", "9.50-11.25", "11.40-13.15", "13.50-15.25", "15.40-17.15", "17.30-19.05","19.20-20.55"};
    String selectedDate="",log="",query;
    Integer id=0;
    public static DBHelper dbHelper;
    Cursor c;
    TextView textView;
    Spinner time,type,corp,number;
    ArrayList<String> types,numbers;
    String p="",information="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        init();
        LoadFaculty();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, times);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        time.setAdapter(adapter);

        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                LoadData("Select distinct number_corps from audience where type=" + "'" + type.getSelectedItem().toString() + "'", numbers, "number_corps", corp);
                Log.d("vfdf", p);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

            corp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    //Spinner spinner = (Spinner) findViewById(R.id.corp_number);
                    LoadData("Select distinct number from audience where number_corps=" + "'" + corp.getSelectedItem().toString() + "'"+ "and type="+"'"+type.getSelectedItem().toString()+"'", numbers, "number", number);
                    if(corp.getSelectedItem().toString()!=""){
                       load();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView1);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year,
                                            int month, int dayOfMonth) {
                int mYear = year;
                int mMonth = month;
                int mDay = dayOfMonth;
                selectedDate = new StringBuilder().append(mDay)
                        .append("-").append(mMonth+1).append("-").append(mYear)
                        .append(" ").toString();

            }
        });
        }
    public void LoadFaculty(){
        Spinner spinner = (Spinner) findViewById(R.id.type_audit1);
        LoadData("Select * from type",types,"type",spinner);
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
    private void init(){
        time = (Spinner) findViewById(R.id.time);
        type=findViewById(R.id.type_audit1);
        corp=findViewById(R.id.corp_number);
        number=findViewById(R.id.number4);
        textView=findViewById(R.id.textView7);
    }
    public void BookData(View view){
        log=Login.log_name;

                dbHelper.insertBook(
                        type.getSelectedItem().toString(),
                        number.getSelectedItem().toString(),
                        corp.getSelectedItem().toString(),
                        selectedDate,
                        time.getSelectedItem().toString(),
                        log
                );
                Toast.makeText(getApplicationContext(), "Succesfully book", Toast.LENGTH_LONG).show();
    }
    public void load(){
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        query= "select distinct * from audience where number_corps=" + "'" + corp.getSelectedItem().toString() + "'"+ "and type="+"'"+type.getSelectedItem().toString()+"'";
        c = database.rawQuery(query,null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
information="Вместимость: "+c.getString(4)+" человек(а)"+"\n"+"Проектор: "+c.getString(5)+"\n"+"Интерактивная доска: "+c.getString(5);
textView.setText(information);
            c.moveToNext();
        }

        c.close();
    }
}
