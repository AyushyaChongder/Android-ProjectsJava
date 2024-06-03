package com.example.cat3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.ViewHolder>{

    List<FriendModel> modelList;
    Context context;

    public FriendsAdapter(Context context,List<FriendModel> modelList) {
        this.context=context;
        this.modelList=modelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_friends,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String nameOfStudent=modelList.get(position).getName();

        holder.itemName.setText(nameOfStudent);


    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView itemName;


        public ViewHolder(View itemView) {
            super(itemView);
            itemName=itemView.findViewById(R.id.studentName);

        }
    }
}