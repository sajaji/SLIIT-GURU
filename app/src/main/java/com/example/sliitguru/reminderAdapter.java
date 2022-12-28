package com.example.sliitguru;


import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class reminderAdapter extends RecyclerView.Adapter<reminderAdapter.ModelViewHolder> {
    Context context;
    ArrayList<reminderDataModel> modelArrayList=new ArrayList<>();
    SQLiteDatabase sqLiteDatabase;

    //genereate Constructor

    public reminderAdapter(Context context, int reminderdatasingle, ArrayList<reminderDataModel> modelArrayList, SQLiteDatabase sqLiteDatabase) {
        this.context = context;
        this.modelArrayList = modelArrayList;
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @NonNull
    @Override
    public reminderAdapter.ModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.reminderdatasingle,null);

        return new ModelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull reminderAdapter.ModelViewHolder holder, int position) {
        final reminderDataModel model=modelArrayList.get(position);
        holder.txttitle.setText(model.getTitle());
        holder.txtdetail.setText(model.getDetail());
        holder.txtdate.setText("Date: "+model.getDate());
        holder.txttime.setText("Time: "+model.getTime());

        //click button to go to ClinicReport Reminder
        holder.btedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                bundle.putInt("id",model.getId());
                bundle.putString("title",model.getTitle());
                bundle.putString("detail",model.getDetail());
                bundle.putString("date",model.getDate());
                bundle.putString("time",model.getTime());
                Intent i = new Intent(context,reminder.class);
                i.putExtra("reminderdata",bundle); //clinicdata
                context.startActivity(i);
            }
        });

        holder.btdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reminderDbHelper remdbhelper= new reminderDbHelper(context);
                sqLiteDatabase=remdbhelper.getReadableDatabase();
                long recdelete=sqLiteDatabase.delete("reminder","id="+model.getId(),null);
                if(recdelete!=-1){
                    Toast.makeText(context,"Data Deleted!",Toast.LENGTH_LONG).show();
                    modelArrayList.remove(position);
                    notifyDataSetChanged();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ModelViewHolder extends RecyclerView.ViewHolder {
        TextView txttitle,txtdetail,txtdate,txttime;
        Button btedit,btdelete;
        public ModelViewHolder(@NonNull View itemView) {
            super(itemView);
            txttitle=(TextView) itemView.findViewById(R.id.Titlesingle);
            txtdetail=(TextView) itemView.findViewById(R.id.Detailsingle);
            txtdate=(TextView) itemView.findViewById(R.id.Datesingle);
            txttime=(TextView) itemView.findViewById(R.id.Timesingle);
            btedit=(Button) itemView.findViewById(R.id.edit);
            btdelete=(Button) itemView.findViewById(R.id.delete);

        }
    }
}
