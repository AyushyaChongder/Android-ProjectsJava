package com.example.wolfys;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class VegetableAdapter extends RecyclerView.Adapter<VegetableAdapter.VegetableViewHolder> {

    private ArrayList<VegetableModel> vegetableList;
    private ArrayList<VegetableModel> selectedItems = new ArrayList<>(); // Store selected items
    private Context mcontext;
    private OnAddToCartClickListener addToCartClickListener;

    public interface OnAddToCartClickListener {
        void onAddToCartClick(VegetableModel vegetable);
    }

    public void setOnAddToCartClickListener(OnAddToCartClickListener listener) {
        addToCartClickListener = listener;
    }


    public VegetableAdapter(ArrayList<VegetableModel> vegetableList, Context mcontext) {
        this.vegetableList = vegetableList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public VegetableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_veggie, parent, false);
        return new VegetableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VegetableViewHolder holder, int position) {
        VegetableModel vegetableItem = vegetableList.get(position);
        holder.veggieImage.setImageResource(vegetableItem.getImgId());
        holder.veggieName.setText(vegetableItem.getTitle());

        holder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vegetableItem.setSelected(!vegetableItem.isSelected());
                if (addToCartClickListener != null) {
                    addToCartClickListener.onAddToCartClick(vegetableItem);
                }
                notifyDataSetChanged(); // Notify the adapter of the data change after the click event
            }
        });

        holder.addButton.setText(vegetableItem.isSelected() ? "Added" : "Add to Cart");
    }


    @Override
    public int getItemCount() {
        return vegetableList.size();
    }


    public class VegetableViewHolder extends RecyclerView.ViewHolder {

        private ImageView veggieImage;
        private TextView veggieName;
        private ConstraintLayout constraintLayout;
        private Button addButton;

        public VegetableViewHolder(@NonNull View itemView) {
            super(itemView);
            veggieImage = itemView.findViewById(R.id.veggieImage);
            veggieName = itemView.findViewById(R.id.veggieName);
            addButton = itemView.findViewById(R.id.addButton);
            constraintLayout = itemView.findViewById(R.id.constraintLayout);
        }
    }
}
