package bstu.fit.yarmolik.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import java.util.ArrayList;

import bstu.fit.yarmolik.myapplication.workWithBd.DBHelper;
import bstu.fit.yarmolik.myapplication.workWithBd.MethodsDB;

public class Admin extends AppCompatActivity {
    public static DBHelper dbHelper;
    MethodsDB methodsDB = new MethodsDB();
    Button change_audit,list_teachers;
    RecyclerView recyclerView;
    Cursor c;
    ArrayList<String> arraylist,arraylistnext;
    Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        dbHelper = new DBHelper(this, "project.db", null, 1);
        init();
        methodsDB.workWithAudit(dbHelper);
        methodsDB.createAudit(dbHelper);
        change_audit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView = (RecyclerView) findViewById(R.id.list);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                ArrayList<Teachers> teachers= new ArrayList<Teachers>();
                dbHelper = new DBHelper(context, "project.db", null, 1);
                SQLiteDatabase database = dbHelper.getWritableDatabase();
                c = database.rawQuery("select * from users",new String[] {});
                c.moveToFirst();
                arraylist = new ArrayList<String>();
                arraylistnext = new ArrayList<String>();
                while (!c.isAfterLast()) {
                    String name= c.getString(c.getColumnIndex("name"));
                    String login=c.getString(c.getColumnIndex("login"));
                    c.moveToNext();
                    arraylist.add(name);
                    arraylistnext.add(login);
                    teachers.add(new Teachers(arraylist.toString(),arraylistnext.toString()));
                }
            }
        });
    }

    public void AuditActivity(View view){
        Intent intent = new Intent(this, AuditActivity.class);
        startActivity(intent);
        Log.d("Audit Open", "Success");
    }
   // private void workWithRecycler(String query, ArrayList<String> arraylist,ArrayList<String> arraylistnext, String name_of_column, String name_of_column_next){
   // }
    public void LoadData(String query, ArrayList<String> arraylist, String name_of_column, Spinner spinner){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraylist);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
    private void init(){
       change_audit=findViewById(R.id.change_audit) ;
       list_teachers=findViewById(R.id.list_teacher);
    }

}
