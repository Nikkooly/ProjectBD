package bstu.fit.yarmolik.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class TeachersAdapter extends RecyclerView.Adapter<TeachersAdapter.TeachersViewHolder> {
    ArrayList<Teachers> teachers;
    public TeachersAdapter( ArrayList<Teachers> teachers){
        this.teachers=teachers;
    }
    public class TeachersViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView login;
        public TeachersViewHolder(View view) {
            super(view);
            name=view.findViewById(R.id.name);
            login=view.findViewById(R.id.login);
        }
    }
    @NonNull
    @Override
    public TeachersAdapter.TeachersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
       return new TeachersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeachersAdapter.TeachersViewHolder holder, int position) {
holder.name.setText(teachers.get(position).getName());
holder.login.setText(teachers.get(position).getLogin());
    }

    @Override
    public int getItemCount() {
        return teachers.size();
    }
}
