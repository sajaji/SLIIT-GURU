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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class linkerAdapter extends RecyclerView.Adapter<linkerAdapter.ModelViewHolder>  {
    Context context;
    ArrayList<linkerModel>modelArrayList=new ArrayList<>();
    SQLiteDatabase sqLiteDatabase;

    public linkerAdapter(Context context, int linkersingle, ArrayList<linkerModel> modelArrayList, SQLiteDatabase sqLiteDatabase) {
        this.context = context;
        this.modelArrayList = modelArrayList;
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @NonNull
    @Override
    public linkerAdapter.ModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.linkersingle,null);
        return new ModelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull linkerAdapter.ModelViewHolder holder, int position) {
        final linkerModel model=modelArrayList.get(position);
        holder.txttitle.setText(model.getTitle());
        holder.txtlink.setText(model.getLink());

        holder.Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                bundle.putInt("id",model.getId());
                bundle.putString("title",model.getTitle());
                bundle.putString("link",model.getLink());
                bundle.putString("description",model.getDescription());
                Intent i= new Intent(context,linkeradd.class);
                i.putExtra("linkdetails",bundle);
                context.startActivity(i);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            linkerDbHelper helper= new linkerDbHelper(context);
            @Override
            public void onClick(View view) {
                sqLiteDatabase=helper.getReadableDatabase();
                long delete = sqLiteDatabase.delete("linkerdetails","id="+model.getId(),null);
                if(delete!=1){
                    Toast.makeText(context,"Data Deleted Successfully!",Toast.LENGTH_LONG).show();
                    modelArrayList.remove(position);
                    notifyDataSetChanged();
                }

            }
        });

        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT,"Check this link about "+holder.txttitle.getText().toString()+" through this link "+holder.txtlink.getText().toString());
                intent.setType("text/plain");
                context.startActivity(Intent.createChooser(intent,"Send To"));
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ModelViewHolder extends RecyclerView.ViewHolder {
        TextView txttitle,txtlink;
        Button Edit,delete;
        ImageView share;
        public ModelViewHolder(@NonNull View itemView) {
            super(itemView);
            txttitle=itemView.findViewById(R.id.texttitle);
            txtlink=itemView.findViewById(R.id.textlink);
            Edit=itemView.findViewById(R.id.edit);
            delete=itemView.findViewById(R.id.delete);
            share=itemView.findViewById(R.id.share);
        }
    }
}
