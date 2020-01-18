package bstu.fit.yarmolik.myapplication.startPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import bstu.fit.yarmolik.myapplication.adminPanel.Admin;
import bstu.fit.yarmolik.myapplication.MainActivity;
import bstu.fit.yarmolik.myapplication.R;
import bstu.fit.yarmolik.myapplication.workWithBd.DBHelper;
import bstu.fit.yarmolik.myapplication.workWithBd.MethodsDB;

public class Login extends AppCompatActivity {
    private EditText loginField,passwordField;
    public static String log_name;
    String password;
    Cursor c;
    String surname="",name="",email,phone,faculty,department,login,query;
    ImageView imageView;
    Button loginBtn;
    public static DBHelper dbHelper;
    MethodsDB methodsDB = new MethodsDB();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        dbHelper = new DBHelper(this, "project.db", null, 1);
        methodsDB.workWithBd(dbHelper);
        loginBtn=findViewById(R.id.btn_sign_in);

    }
    public void Registration (View view){
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
        Log.d("Registration open","Success");
    }

    public void init(){
        loginField = findViewById(R.id.et_login);
        passwordField = findViewById(R.id.et_password);
    }
public void per(String log){
        String name="";
}
    public void Authorization(View view){
        log_name=loginField.getText().toString();
        String log="";
        String pass="";
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        log=loginField.getText().toString();
        pass=passwordField.getText().toString();
        query= "select * from users where login=" +"'"+log+"'" +" and password="+"'"+pass+"'" ;
        c = database.rawQuery(query,null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            name= c.getString(1).toString();
            surname= c.getString(2).toString();
            login= c.getString(8).toString();
            password= c.getString(9).toString();
            c.moveToNext();
        }
        c.close();
        Log.d(name,surname);

       if(log.equals("AdminOfApp") && pass.equals("sqd1063"))
        {
            Intent intent = new Intent(this, Admin.class);
            startActivity(intent);
            Log.d("Admin open", "Success");
        }
        if(name.equals("")||surname.equals("")) {
            Toast.makeText(getApplicationContext(), "Fiasko bro", Toast.LENGTH_SHORT).show();
        }
        else {
            per(log);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            Log.d("Teacher open", "Success");
        }
        if(name.equals("")) {
            Toast.makeText(getApplicationContext(), "Fiasko bro", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            Log.d("Registration open", "Success");
        }
        name="";
        surname="";
    }

}
