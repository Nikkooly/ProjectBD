package bstu.fit.yarmolik.myapplication.workWithBd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class MethodsDB {
    public void workWithAudit(DBHelper dbHelper) {
        dbHelper.queryData("CREATE TABLE IF NOT EXISTS type( id INTEGER PRIMARY KEY AUTOINCREMENT, type TEXT, UNIQUE (type) ON CONFLICT IGNORE )");
        dbHelper.queryData("INSERT INTO type(type) values('аудитория лекционного типа')");
        dbHelper.queryData("INSERT INTO type(type) values('аудитория для семенарских занятий')");
        dbHelper.queryData("INSERT INTO type(type) values('компьютерная аудитория')");
        dbHelper.queryData("INSERT INTO type(type) values('лабораторная аудитория')");
        dbHelper.queryData("INSERT INTO type(type) values('многофункциональная учебная аудитория')");
    }
     public void workWithBd(DBHelper dbHelper){
         dbHelper.queryData("CREATE TABLE IF NOT EXISTS faculty( id INTEGER PRIMARY KEY AUTOINCREMENT, faculty TEXT)");
         // dbHelper.queryData("INSERT INTO faculty(faculty) values('FIT')");
         // dbHelper.queryData("INSERT INTO faculty(faculty) values('TOV')");

         dbHelper.queryData("CREATE TABLE IF NOT EXISTS department( id INTEGER PRIMARY KEY AUTOINCREMENT, department TEXT)");
         // dbHelper.queryData("INSERT INTO department(department) values('PI')");
         // dbHelper.queryData("INSERT INTO users(login,password) values('AdminOfApp','sqd1063')");
         dbHelper.queryData("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT,surname TEXT, name TEXT, " +
                 "email TEXT, phone_number TEXT, photo BLOB, faculty TEXT,department TEXT,login TEXT,password TEXT, " +
                 "foreign key(faculty) references faculty(faculty)," +
                 " foreign key (department) references department(department), UNIQUE (login) ON CONFLICT IGNORE)");
     }
     public void createAudit(DBHelper dbHelper)
     {
         dbHelper.queryData("CREATE TABLE IF NOT EXISTS audience (id INTEGER PRIMARY KEY AUTOINCREMENT,type TEXT, number_corps TEXT, " +
                 "number TEXT, capacity TEXT, projector TEXT, interactive TEXT ," +
                 "foreign key(type) references type(type), UNIQUE (number) ON CONFLICT IGNORE)");
     }

}
