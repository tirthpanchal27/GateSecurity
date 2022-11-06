package com.example.gatesecurutiyapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyViewHolder2> {

    Context context;
    ArrayList<fetchsocservice> fetchsocserviceArrayList;

    public MyAdapter2(Context context, ArrayList<fetchsocservice> fetchsocserviceArrayList) {

        this.context = context;
        this.fetchsocserviceArrayList = fetchsocserviceArrayList;
    }
    @NonNull
    @Override
    public MyAdapter2.MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item2, parent, false);

        return new MyAdapter2.MyViewHolder2(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter2.MyViewHolder2 holder, int position) {

        fetchsocservice fetchsocservice = fetchsocserviceArrayList .get(position);

        holder.plumber.setText(fetchsocservice.plumber);
        holder.cleaner.setText(fetchsocservice.cleaner);
        holder.electricity.setText(fetchsocservice.electricity);
        holder.housemaid.setText(fetchsocservice.housemaid);
        holder.watersupplier.setText(fetchsocservice.watersupplier);
        holder.wifiprovider.setText(fetchsocservice.wifiprovider);

    }

    @Override
    public int getItemCount() {
        return fetchsocserviceArrayList.size();
    }

    public static class MyViewHolder2 extends RecyclerView.ViewHolder
    {
        TextView plumber, cleaner, electricity, housemaid, watersupplier, wifiprovider;
        public MyViewHolder2(@NonNull View itemView)
        {
            super(itemView);
            plumber = itemView.findViewById(R.id.tvplumber);
            cleaner = itemView.findViewById(R.id.tvcleaner);
            electricity = itemView.findViewById(R.id.tvelectricity);
            housemaid = itemView.findViewById(R.id.tvmaid);
            watersupplier = itemView.findViewById(R.id.tvwater);
            wifiprovider = itemView.findViewById(R.id.tvwifi);

        }
    }
}
