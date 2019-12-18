package bstu.fit.yarmolik.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class DBHelper  extends SQLiteOpenHelper {

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public void insertData(String surname,String name, String email, String number, byte[] image, String faculty,String department,String login, String password){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO users VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, surname);
        statement.bindString(2, name);
        statement.bindString(3, email);
        statement.bindString(4, number);
        statement.bindBlob(5,image);
        statement.bindString(6,faculty);
        statement.bindString(7,department);
        statement.bindString(8,login);
        statement.bindString(9,password);

        statement.executeInsert();
    }

    public  void deleteData(String name) {
        SQLiteDatabase database = getWritableDatabase();
        //
        String sql = "DELETE FROM users WHERE name = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, name);

        statement.execute();
        database.close();
    }

    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}