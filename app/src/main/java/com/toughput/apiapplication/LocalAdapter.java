package com.toughput.apiapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LocalAdapter extends RecyclerView.Adapter<LocalAdapter.ViewHolder> {

    ArrayList<Local> localArrayList;

    public void setLocalArrayList(ArrayList<Local> localArrayList) {
        this.localArrayList = localArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_puguh, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Local local = localArrayList.get(position);
        holder.tvNama.setText(local.namalengkap);
        holder.tvttl.setText(local.email);
        holder.tvttl.setText(local.tempatlahir);
    }

    @Override
    public int getItemCount() {
        return localArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvNama, tvEmail, tvttl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tv_username);
            tvEmail = itemView.findViewById(R.id.tv_email);
            tvttl = itemView.findViewById(R.id.tv_password);
        }
    }
}
