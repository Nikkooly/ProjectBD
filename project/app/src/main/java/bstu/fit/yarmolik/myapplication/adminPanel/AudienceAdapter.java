package bstu.fit.yarmolik.myapplication.adminPanel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

import bstu.fit.yarmolik.myapplication.R;

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

