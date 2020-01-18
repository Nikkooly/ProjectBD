package bstu.fit.yarmolik.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import bstu.fit.yarmolik.myapplication.workWithBd.DBHelper;

public class AuditActivity extends AppCompatActivity implements LoadFaculty {
    public static DBHelper dbHelper;
    EditText number_corps, number, capacity;
    Spinner type_audit;
    Button add_audit;
    Cursor c;
    String name="";
    String check1="no", check2="no";
    CheckBox proj,inter;
    //CheckBox projector, interactive;
    ArrayList<String> type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audit);
        init();
        LoadFaculty();
        add_audit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkNumber("select * from audience where number="+number.getText().toString()+" and number_corps="+"'"+number_corps.getText().toString()+"'","number");
                if(name.equals("")) {
                    dbHelper.insertAudit(
                            type_audit.getSelectedItem().toString(),
                            number_corps.getText().toString(),
                            number.getText().toString(),
                            capacity.getText().toString(),
                            check1,
                            check2
                    );

                    Toast.makeText(getApplicationContext(), "Successfully add", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext(), "Auditory consist", Toast.LENGTH_SHORT).show();
                name="";
            }
        });
    }
    public void onCheckboxClicked(View view) {
        // Получаем флажок
        CheckBox language = (CheckBox) view;
        // Получаем, отмечен ли данный флажок
        boolean checked = language.isChecked();
        // Смотрим, какой именно из флажков отмечен
        switch(view.getId()) {
            case R.id.projector:
                if (checked)
                    check1 = "yes";
                    else
                        check1="no";

                break;
            case R.id.interactive:
                if (checked)
                    check2= "yes";
                else
                    check2="no";
                break;
        }
        //Log.d("check1",check1);
        //Log.d("check2",check2);
    }
    public void LoadFaculty(){
        Spinner spinner = (Spinner) findViewById(R.id.type_audit);
        LoadData("Select * from type",type,"type",spinner);
    }
    private void checkNumber(String query, String name_of_column)
    {
        dbHelper = new DBHelper(this, "project.db", null, 1);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        c = database.rawQuery(query,new String[] {});
        c.moveToFirst();
        while (!c.isAfterLast()) {
             name= c.getString(c.getColumnIndex(name_of_column));
            c.moveToNext();
        }
        c.close();
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
    public void init(){
       type_audit= findViewById(R.id.type_audit);
       number_corps=findViewById(R.id.number_corps);
       number=findViewById(R.id.number);
       capacity= findViewById(R.id.capacity);
        add_audit=findViewById(R.id.add_audit);
        proj=findViewById(R.id.projector);
        inter=findViewById(R.id.interactive);
       // projector= findViewById(R.id.projector);
        //interactive= findViewById(R.id.interactive);
    }
}
