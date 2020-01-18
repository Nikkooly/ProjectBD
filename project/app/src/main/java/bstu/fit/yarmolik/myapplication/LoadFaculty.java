package bstu.fit.yarmolik.myapplication;

import android.widget.Spinner;

import java.util.ArrayList;

public interface LoadFaculty {
    void LoadFaculty();
    void LoadData(String query, ArrayList<String> arraylist, String name_of_column, Spinner spinner);
}
