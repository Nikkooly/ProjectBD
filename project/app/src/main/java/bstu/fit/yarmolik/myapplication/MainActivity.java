package bstu.fit.yarmolik.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bstu.fit.yarmolik.myapplication.startPage.Login;
import bstu.fit.yarmolik.myapplication.workWithBd.DBHelper;
import bstu.fit.yarmolik.myapplication.workWithBd.MethodsDB;

public class MainActivity extends AppCompatActivity  {
    public static DBHelper dbHelper;
    public String name="",query;
    ListView listView;
    Cursor c;
    private List<Auditories> auditories;
    Auditories auditor;
    private ArrayAdapter<Auditories> adapter;
    private FrameLayout fragmentContainer;
    MethodsDB methodsDB = new MethodsDB();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this, "project.db", null, 1);
        methodsDB.bookAudit(dbHelper);
        init();

    }
    public void Book(View view){
        listView.setVisibility(View.INVISIBLE);
        auditories.clear();
        Intent intent = new Intent(this,BookAudit.class);
        startActivity(intent);
    }
    public void See(View view){
        listView.setVisibility(View.INVISIBLE);
        auditories.clear();
        Intent intent = new Intent(this,ChangeTeacher.class);
        startActivity(intent);
   }
   public void AddNote(View view){
       listView.setVisibility(View.INVISIBLE);
       auditories.clear();
       Intent intent = new Intent(this,Note.class);
       startActivity(intent);
   }
   public void SeeAudit(View view){
       auditories.clear();
        listView.setVisibility(View.VISIBLE);
        load();
   }
    public void load(){
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        query= "select distinct * from book where login=" +"'"+Login.log_name+"'";
        c = database.rawQuery(query,null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            auditor = new Auditories(c.getString(1), c.getString(2)+"-"+c.getString(3), c.getString(4),c.getString(5));
            auditories.add(auditor);
            c.moveToNext();
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, auditories);
        listView.setAdapter(adapter);
        c.close();
    }
   public void init(){
       auditories = new ArrayList<>();
       listView=findViewById(R.id.listAudience);
   }
   /*
        FragmentManager fm = getSupportFragmentManager();

        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
        if (fragment == null) {
            fragment = new TeacherFragment();
            Bundle bundle = new Bundle();
            bundle.putString(TeacherFragment.KEY, "Hello, World!");
            fragment.setArguments(bundle);
            fm.beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit();
        }
    }*/
    }
