package bstu.fit.yarmolik.myapplication;

import android.content.Context;
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

public class AudienceAdapter extends RecyclerView.Adapter<AudienceAdapter.AudienceViewHolder > {
    ArrayList<Audience> audiences;
    public String number_audit="";
    public AudienceAdapter( ArrayList<Audience> audiences){
        this.audiences=audiences;
    }
    public class AudienceViewHolder extends RecyclerView.ViewHolder{
        TextView number;
        public AudienceViewHolder(View view) {
            super(view);
            number=view.findViewById(R.id.number_at);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position= getAdapterPosition();
                    Context context=view.getContext();
                    Intent intent = new Intent(context, ChangeAudit.class);
                    intent.putExtra("content",audiences.get(position).getNumber());
                    context.startActivity(intent);

                }
            });
        }
    }
    @NonNull
    @Override
    public AudienceAdapter.AudienceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
       return new AudienceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AudienceAdapter.AudienceViewHolder  holder, final int position) {
holder.number.setText(audiences.get(position).getNumber());
Log.d("name",number_audit);
    }

    @Override
    public int getItemCount() {
        return audiences.size();
    }

}

