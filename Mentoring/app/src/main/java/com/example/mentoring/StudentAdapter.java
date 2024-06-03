package com.example.mentoring;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mentoring.fragments.StudentDetails;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    private List<Model> modelList;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public StudentAdapter(Context context, List<Model> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    public interface OnItemClickListener {
        void onItemClick(Model model);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.student_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Model model = modelList.get(position);
        String nameOfStudent = model.getStuName();
        int imageResource = model.getStuImage();
        holder.itemName.setText(nameOfStudent);
        holder.itemImg.setImageResource(imageResource);

        // Handle item click event
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(model);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemName;
        ImageView itemImg;

        public ViewHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.studentName);
            itemImg = itemView.findViewById(R.id.studentImage);
        }
    }
}
