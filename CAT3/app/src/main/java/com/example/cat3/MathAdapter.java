package com.example.cat3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MathAdapter extends RecyclerView.Adapter<MathAdapter.ViewHolder> {

    private List<MathModel> modelList;
    private Context context;

    public MathAdapter(Context context, List<MathModel> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_math, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MathModel mathModel = modelList.get(position);

        holder.subjectName.setText(mathModel.getSubjectName());
        holder.checkbox.setChecked(mathModel.isChecked());

        holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mathModel.setChecked(isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView subjectName;
        CheckBox checkbox;

        public ViewHolder(View itemView) {
            super(itemView);
            subjectName = itemView.findViewById(R.id.subjectName);
            checkbox = itemView.findViewById(R.id.checkBox);
        }
    }
    // Method to get checked items
    public List<MathModel> getCheckedItems() {
        List<MathModel> checkedItems = new ArrayList<>();
        for (MathModel mathModel : modelList) {
            if (mathModel.isChecked()) {
                checkedItems.add(mathModel);
            }
        }
        return checkedItems;
    }
}
