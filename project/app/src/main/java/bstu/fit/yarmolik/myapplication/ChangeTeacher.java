package bstu.fit.yarmolik.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import bstu.fit.yarmolik.myapplication.adminPanel.Admin;
import bstu.fit.yarmolik.myapplication.MainActivity;
import bstu.fit.yarmolik.myapplication.R;
import bstu.fit.yarmolik.myapplication.workWithBd.DBHelper;
import bstu.fit.yarmolik.myapplication.workWithBd.MethodsDB;

import bstu.fit.yarmolik.myapplication.startPage.Login;
import bstu.fit.yarmolik.myapplication.workWithBd.DBHelper;

public class ChangeTeacher extends AppCompatActivity {
Login login;
EditText surname,name,phone,email;
TextView l;
    Spinner faculty, department;
ImageView imageView;
String log="",query;
    ArrayList<String> faculties,departments;
Cursor c;
    public static DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_teacher);
        log=Login.log_name;
        dbHelper = new DBHelper(this, "project.db", null, 1);
        init();
        load();
        LoadFaculty();
        LoadDepartment();
    }
    public void load(){
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        query= "select distinct * from users where login=" +"'"+log+"'";
        c = database.rawQuery(query,null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            String s=c.getString(1);
            surname.setText(c.getString(1));
           name.setText(c.getString(2));
            email.setText(c.getString(3));
            phone.setText(c.getString(4));
            l.setText(c.getString(8).toString());
            byte [] image = c.getBlob(5);
            Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
            imageView.setImageBitmap(bitmap);
            c.moveToNext();
        }
        c.close();
    }
    public void LoadFaculty(){
        Spinner spinner = (Spinner) findViewById(R.id.facultyt);
        LoadData("Select * from faculty",faculties,"faculty",spinner);
    }
    public void LoadDepartment(){
        Spinner spinner = (Spinner) findViewById(R.id.type_auditt);
        LoadData("Select * from department",departments,"department",spinner);
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
        surname = (EditText) findViewById(R.id.surnamet);
        name = (EditText) findViewById(R.id.namet);
        email = (EditText) findViewById(R.id.emailt);
        phone = (EditText) findViewById(R.id.phonet);
        imageView = (ImageView) findViewById(R.id.imageViewt);
        department = (Spinner) findViewById(R.id.type_auditt);
        faculty= (Spinner) findViewById(R.id.facultyt);
        l=findViewById(R.id.logint);
    }
    public void SaveInfo(View view){
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("project.db", MODE_PRIVATE, null);
        db.execSQL("UPDATE users SET surname = '"+surname.getText().toString()+"' WHERE login = '"+log+"';");
        db.execSQL("UPDATE users SET name = '"+name.getText().toString()+"' WHERE login = '"+log+"';");
        db.execSQL("UPDATE users SET email = '"+email.getText().toString()+"' WHERE login = '"+log+"';");
        db.execSQL("UPDATE users SET phone_number = '"+phone.getText().toString()+"' WHERE login = '"+log+"';");
        db.execSQL("UPDATE users SET faculty = '"+faculty.getSelectedItem().toString()+"' WHERE login = '"+log+"';");
        db.execSQL("UPDATE users SET department = '"+department.getSelectedItem().toString()+"' WHERE login = '"+log+"';");
        //""' and phone_number = '"+phone_s+"' and social_network = '"+social_s+"' and course = '"+lang+"' and profession = '"+profession+"'  WHERE name = '"+name+"'");
        Toast.makeText(getApplicationContext(), "Updated successfully!", Toast.LENGTH_SHORT).show();
    }
}
