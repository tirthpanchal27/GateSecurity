package com.example.gatesecurutiyapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter3 extends RecyclerView.Adapter<MyAdapter3.MyViewHolder3>
{
    Context context;
    ArrayList<fetchannouncements> announcementsArrayList;

    public MyAdapter3(Context context, ArrayList<fetchannouncements> announcementsArrayList) {
        this.context = context;
        this.announcementsArrayList = announcementsArrayList;
    }

    @NonNull
    @Override
    public MyAdapter3.MyViewHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item3, parent, false);

        return new MyAdapter3.MyViewHolder3(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter3.MyViewHolder3 holder, int position) {

        fetchannouncements fetchannouncements = announcementsArrayList .get(position);

        holder.announcements.setText(fetchannouncements.announcements);



    }

    @Override
    public int getItemCount() {
        return announcementsArrayList.size();
    }

    public static class MyViewHolder3 extends RecyclerView.ViewHolder
    {
        TextView announcements;
        public MyViewHolder3(@NonNull View itemView)
        {
            super(itemView);
            announcements = itemView.findViewById(R.id.tvannouncements);

        }
    }
}
