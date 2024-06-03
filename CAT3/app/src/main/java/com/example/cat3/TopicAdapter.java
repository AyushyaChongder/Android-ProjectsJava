package com.example.cat3;



import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.ViewHolder>{

    List<Model> modelList;
    Context context;

    public TopicAdapter(Context context, List<Model> modelList) {
        this.context=context;
        this.modelList=modelList;
    }
    // Define an interface for the click listener
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_topic,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String nameOfTopic=modelList.get(position).getTopName();
        int images=modelList.get(position).getTopImage();
        holder.itemName.setText(nameOfTopic);
        holder.itemImg.setImageResource(images);
        Model model = modelList.get(position);
        holder.cardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the button click for the card at the given position
                // You can open a new activity or perform any desired action here
                switch (position) {
                    case 0:
                        // Handle button click for the first card
                        Intent intent = new Intent(context, Maths.class);
                        context.startActivity(intent);
                        break;
                    case 1:
                        // Handle button click for the second card
                        break;
                    // Repeat for other positions
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView itemName;
        ImageView itemImg;
        Button cardButton;

        public ViewHolder(View itemView) {
            super(itemView);
            itemName=itemView.findViewById(R.id.topicName);
            itemImg=itemView.findViewById(R.id.topicImage);
            cardButton = itemView.findViewById(R.id.topicOpen);
        }
    }
}
