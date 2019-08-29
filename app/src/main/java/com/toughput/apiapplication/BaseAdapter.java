package com.toughput.apiapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.ViewHolder> {
    ArrayList<Puguh> puguhArrayList;

    public void setPuguhArrayList(ArrayList<Puguh> puguhArrayList) {
        this.puguhArrayList = puguhArrayList;
    }

    @NonNull
    @Override
    public BaseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_puguh, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseAdapter.ViewHolder holder, int position) {

        Puguh puguh = puguhArrayList.get(position);
        holder.tvUsername.setText(puguh.usernme);
        holder.tvEmail.setText(puguh.email);
        holder.tvPassword.setText(puguh.password);

    }

    @Override
    public int getItemCount() {
        return puguhArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvUsername, tvEmail, tvPassword;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tv_username);
            tvEmail = itemView.findViewById(R.id.tv_email);
            tvPassword = itemView.findViewById(R.id.tv_password);
        }
    }
}
