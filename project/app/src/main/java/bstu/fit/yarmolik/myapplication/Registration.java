package bstu.fit.yarmolik.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Registration extends AppCompatActivity {
    final int REQUEST_CODE_GALLERY = 999;
    ImageView imageView;
    EditText surname,name, email, phone,login, password;
    Spinner faculty, department;
    Button register, choose;
    String queryFaculty,queryDepartment,passs;
    public static DBHelper dbHelper;
    Cursor c;
    ArrayList<String> faculties,departments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_registration);
        setContentView(R.layout.activity_registration);
        init();
        LoadFaculty();
        LoadDepartment();
        department = (Spinner) findViewById(R.id.department);
        faculty= (Spinner) findViewById(R.id.faculty);
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        Registration.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    try {
                        queryFaculty = faculty.getSelectedItem().toString();
                        queryDepartment = faculty.getSelectedItem().toString();
                        dbHelper.insertData(
                                surname.getText().toString(),
                                name.getText().toString(),
                                email.getText().toString(),
                                phone.getText().toString(),
                                imageViewToByte(imageView),
                                queryFaculty,
                                queryDepartment,
                                login.getText().toString(),
                                password.getText().toString()
                        );
                        Toast.makeText(getApplicationContext(), "Successfully registred", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        startActivity(intent);
                        Log.d("Registration open", "Success");
                        //imageView.setImageResource(R.mipmap.ic_launcher);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Error. Check fields", Toast.LENGTH_SHORT).show();
                    }
            }
        });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else{
                Toast.makeText(getApplicationContext(), "You don't have permisson to access file location",Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                imageView.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private byte[] imageViewToByte(ImageView image){
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
    public void LoadFaculty(){
        Spinner spinner = (Spinner) findViewById(R.id.faculty);
        LoadData("Select * from faculty",faculties,"faculty",spinner);
    }
    public void LoadDepartment(){
        Spinner spinner = (Spinner) findViewById(R.id.department);
       LoadData("Select * from department",departments,"department",spinner);
    }
    public void LoadData(String query, ArrayList<String> arraylist,String name_of_column, Spinner spinner){
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
        surname = (EditText) findViewById(R.id.surname);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        register = (Button) findViewById(R.id.Register);
        choose = (Button) findViewById(R.id.Choose);
        imageView = (ImageView) findViewById(R.id.imageView);
        login=(EditText) findViewById(R.id.login);
        password=(EditText) findViewById(R.id.password);
        //checkbox=(CheckBox) findViewById(R.id.)
    }

}
