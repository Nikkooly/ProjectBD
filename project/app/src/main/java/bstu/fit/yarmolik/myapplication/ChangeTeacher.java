package bstu.fit.yarmolik.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import bstu.fit.yarmolik.myapplication.startPage.Login;

public class ChangeTeacher extends AppCompatActivity {
Login login;
String log="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_teacher);
        log=Login.log_name;
         Toast.makeText(getApplicationContext(), log, Toast.LENGTH_LONG).show();
    }
}
