package com.example.gatesecurutiyapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>
{
    Context context;
    ArrayList<fetchResident> residentArrayList;

    public MyAdapter(Context context, ArrayList<fetchResident> residentArrayList) {
        this.context = context;
        this.residentArrayList = residentArrayList;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

        fetchResident fetchresident = residentArrayList .get(position);

        holder.name.setText(fetchresident.name);
        holder.Block.setText(fetchresident.Block);


    }

    @Override
    public int getItemCount() {
        return residentArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView name, Block;
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            name = itemView.findViewById(R.id.tvname);
            Block = itemView.findViewById(R.id.tvblock);
        }
    }
}
