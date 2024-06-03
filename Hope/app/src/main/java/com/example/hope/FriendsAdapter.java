package com.example.hope;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.ViewHolder> {

    List<FriendModel> modelList;
    Context context;

    public FriendsAdapter(Context context, List<FriendModel> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_friends, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Extract data from the FriendModel
        String location = modelList.get(position).getLocation();
        String date = modelList.get(position).getDate();
        String message = modelList.get(position).getMessage();

        // Set the data to appropriate TextViews
        holder.locationTextView.setText("Location: " + location);
        holder.dateTextView.setText("Date: " + date);
        holder.messageTextView.setText("Message: " + message);
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView locationTextView;
        TextView dateTextView;
        TextView messageTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            locationTextView = itemView.findViewById(R.id.studentName);
            dateTextView = itemView.findViewById(R.id.date);
            messageTextView = itemView.findViewById(R.id.message);
        }
    }
}
