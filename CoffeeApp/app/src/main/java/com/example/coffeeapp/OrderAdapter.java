package com.example.coffeeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    List<Model> modelList;
    Context context;
    public OrderAdapter(Context context, List<Model> modelList) {
       this.context=context;
       this.modelList=modelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.listitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String nameOfDrink=modelList.get(position).getItemName();
        String descriptionOfDrink=modelList.get(position).getItemDescription();
        int images=modelList.get(position).getItemImg();
        holder.itemName.setText(nameOfDrink);
        holder.itemDescription.setText(descriptionOfDrink);
        holder.itemImg.setImageResource(images);

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView itemName, itemDescription;
        ImageView itemImg;

        public ViewHolder(View itemView) {
            super(itemView);
            itemName=itemView.findViewById(R.id.coffeeName);
            itemDescription=itemView.findViewById(R.id.description);
            itemImg=itemView.findViewById(R.id.coffeeImage);
        }
    }
}
