package com.example.cat3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CheckedItemsAdapter extends RecyclerView.Adapter<CheckedItemsAdapter.ViewHolder> {

    private List<MathModel> checkedItemsList;
    private Context context;

    public CheckedItemsAdapter(Context context, List<MathModel> checkedItemsList) {
        this.context = context;
        this.checkedItemsList = checkedItemsList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.check_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MathModel checkedItem = checkedItemsList.get(position);

        holder.itemName.setText(checkedItem.getSubjectName());
    }

    @Override
    public int getItemCount() {
        return checkedItemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;

        public ViewHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.subjectName);
        }
    }
}
