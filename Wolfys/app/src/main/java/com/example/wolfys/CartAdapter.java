package com.example.wolfys;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private ArrayList<VegetableModel> cartItems;

    public CartAdapter(ArrayList<VegetableModel> cartItems) {
        this.cartItems = cartItems;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        VegetableModel cartItem = cartItems.get(position);

        // Bind the data to the ViewHolder
        holder.itemNameTextView.setText(cartItem.getTitle());
        holder.itemImageView.setImageResource(cartItem.getImgId());
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView itemNameTextView;
        ImageView itemImageView;

        CartViewHolder(View itemView) {
            super(itemView);
            itemNameTextView = itemView.findViewById(R.id.cartItemName);
            itemImageView = itemView.findViewById(R.id.cartItemImage);
        }
    }
}
