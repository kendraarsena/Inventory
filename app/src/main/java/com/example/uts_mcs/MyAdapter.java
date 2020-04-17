package com.example.uts_mcs;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<Product> arrItem;
    private OnNoteListener onNoteListener;
    Context ctx;
    DBHelper dbHelper;

    public MyAdapter(ArrayList<Product> arrItem, Context ctx, OnNoteListener onNoteListener) {
        this.arrItem = arrItem;
        this.ctx = ctx;
        this.onNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.item_view, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v, onNoteListener);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Product product = arrItem.get(position);
        int max = 100;
        holder.nama.setText(product.getName());
        if (product.getDesc().length() < max) holder.desc.setText(product.getDesc());
        else holder.desc.setText(product.getDesc().substring(0, max)+"...");
        holder.qty.setText("Quantity : " + product.getQty());
    }

    @Override
    public int getItemCount() {
        return arrItem.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView nama, desc, qty;
        OnNoteListener onNoteListener;

        public MyViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            nama = itemView.findViewById(R.id.nama);
            desc = itemView.findViewById(R.id.desc);
            qty = itemView.findViewById(R.id.qty);
            this.onNoteListener = onNoteListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}
