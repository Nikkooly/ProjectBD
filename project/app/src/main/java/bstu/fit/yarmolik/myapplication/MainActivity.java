package bstu.fit.yarmolik.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import bstu.fit.yarmolik.myapplication.startPage.Login;
import bstu.fit.yarmolik.myapplication.workWithBd.DBHelper;
import bstu.fit.yarmolik.myapplication.workWithBd.MethodsDB;

public class MainActivity extends AppCompatActivity  {
    public static DBHelper dbHelper;
    public String name="";
    private FrameLayout fragmentContainer;
    MethodsDB methodsDB = new MethodsDB();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this, "project.db", null, 1);
        methodsDB.bookAudit(dbHelper);

    }
    public void Book(View view){
        Intent intent = new Intent(this,BookAudit.class);
        startActivity(intent);
    }
    public void See(View view){
        Intent intent = new Intent(this,ChangeTeacher.class);
        startActivity(intent);
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
