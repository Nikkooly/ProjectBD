package bstu.fit.yarmolik.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    private EditText loginField,passwordField;
    String password;
    Cursor c;
    String surname="",name="",email,phone,faculty,department,login,query;
    ImageView imageView;
    Button loginBtn;
    public static DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        dbHelper = new DBHelper(this, "project.db", null, 1);
        workWithBd();
        loginBtn=findViewById(R.id.btn_sign_in);

    }
    public void Registration (View view){
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
        Log.d("Registration open","Success");
    }
    public void workWithBd(){
        dbHelper.queryData("CREATE TABLE IF NOT EXISTS faculty( id INTEGER PRIMARY KEY AUTOINCREMENT, faculty TEXT)");
        //dbHelper.queryData("INSERT INTO faculty(faculty) values('FIT')");
        //dbHelper.queryData("INSERT INTO faculty(faculty) values('TOV')");

        dbHelper.queryData("CREATE TABLE IF NOT EXISTS department( id INTEGER PRIMARY KEY AUTOINCREMENT, department TEXT)");
       // dbHelper.queryData("INSERT INTO department(department) values('PI')");
       dbHelper.queryData("INSERT INTO users(login,password) values('AdminOfApp','sqd1063')");
        dbHelper.queryData("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT,surname TEXT, name TEXT, " +
                "email TEXT, phone_number TEXT, photo BLOB, faculty TEXT,department TEXT,login TEXT,password TEXT, foreign key(faculty) references faculty(faculty)," +
                " foreign key (department) references department(department), UNIQUE (login) ON CONFLICT IGNORE)");
    }
    public void init(){
        loginField = findViewById(R.id.et_login);
        passwordField = findViewById(R.id.et_password);
    }
    public void Authorization(View view){
        String log="";
        String pass="";
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        log=loginField.getText().toString();
        pass=passwordField.getText().toString();
        query= "select * from users where login=" +"'"+log+"'" +" and password="+"'"+pass+"'" ;
//query="select * from users where login='ar' and password='1234567'";
        c = database.rawQuery(query,null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            name= c.getString(1).toString();
            surname= c.getString(2).toString();
            password= c.getString(9).toString();
            c.moveToNext();
        }
        c.close();
        Log.d(name,surname);
       if(name.equals("")||surname.equals("")) {
            Toast.makeText(getApplicationContext(), "Fiasko bro", Toast.LENGTH_SHORT).show();
        }
       else if(login.equals("AdminOfApp") && password.equals("sqd1063"))
        {
            Intent intent = new Intent(this, Admin.class);
            startActivity(intent);
            Log.d("Admin open", "Success");
        }
        else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            Log.d("Teacher open", "Success");
        }
        /*if(name.equals("")) {
            Toast.makeText(getApplicationContext(), "Fiasko bro", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            Log.d("Registration open", "Success");
        }*/
        name="";
        surname="";
    }

}
