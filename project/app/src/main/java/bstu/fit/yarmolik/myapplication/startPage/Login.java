package bstu.fit.yarmolik.myapplication.startPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import bstu.fit.yarmolik.myapplication.Admin;
import bstu.fit.yarmolik.myapplication.R;
import bstu.fit.yarmolik.myapplication.startPage.Registration;
import bstu.fit.yarmolik.myapplication.workWithBd.DBHelper;
import bstu.fit.yarmolik.myapplication.workWithBd.MethodsDB;

public class Login extends AppCompatActivity {
    private EditText loginField,passwordField;
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

    public void Authorization(View view){
       /* String log="";
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
            login= c.getString(8).toString();
            password= c.getString(9).toString();
            c.moveToNext();
        }
        c.close();
        Log.d(name,surname);

       if(log.equals("*− −** −− ** −*") && pass.equals("sqd1063"))
        {*/
            Intent intent = new Intent(this, Admin.class);
            startActivity(intent);
            Log.d("Admin open", "Success");
        /*}
        if(name.equals("")||surname.equals("")) {
            Toast.makeText(getApplicationContext(), "Fiasko bro", Toast.LENGTH_SHORT).show();
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
