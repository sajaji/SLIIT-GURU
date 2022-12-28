package com.example.sliitguru;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ModelViewHolder>  {
    Context context;
    ArrayList<TodoModel>modelArrayList=new ArrayList<>();
    SQLiteDatabase sqLiteDatabase;

    public TodoAdapter(Context context, int todosingle, ArrayList<TodoModel> modelArrayList, SQLiteDatabase sqLiteDatabase) {
        this.context = context;
        this.modelArrayList = modelArrayList;
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @NonNull
    @Override
    public TodoAdapter.ModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.todosingle,null);
        return new ModelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoAdapter.ModelViewHolder holder, int position) {
        final TodoModel model=modelArrayList.get(position);
        holder.txttitle.setText(model.getTitle());
        holder.txtdesc.setText(model.getDescription());

        holder.Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                bundle.putInt("id",model.getId());
                bundle.putString("title",model.getTitle());
                bundle.putString("description",model.getDescription());
                Intent i= new Intent(context,AddToDoActivity.class);
                i.putExtra("tododetails",bundle);
                context.startActivity(i);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            todo_DB_Helper helper= new todo_DB_Helper(context);
            @Override
            public void onClick(View view) {
                sqLiteDatabase=helper.getReadableDatabase();
                long delete = sqLiteDatabase.delete("tododetails","id="+model.getId(),null);
                if(delete!=1){
                    Toast.makeText(context,"Data Deleted Successfully!",Toast.LENGTH_LONG).show();
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
        TextView txttitle,txtdesc;
        Button Edit,delete;
        ImageView share;
        public ModelViewHolder(@NonNull View itemView) {
            super(itemView);
            txttitle=itemView.findViewById(R.id.texttitle);
            txtdesc=itemView.findViewById(R.id.textdesc);
            Edit=itemView.findViewById(R.id.edit);
            delete=itemView.findViewById(R.id.delete);
            share=itemView.findViewById(R.id.share);
        }
    }
}

