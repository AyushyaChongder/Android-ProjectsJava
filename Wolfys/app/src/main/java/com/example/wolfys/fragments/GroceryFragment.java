package com.example.wolfys.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wolfys.OrderPage;
import com.example.wolfys.R;
import com.example.wolfys.VegetableAdapter;
import com.example.wolfys.VegetableModel;

import java.util.ArrayList;

public class GroceryFragment extends Fragment {

    private RecyclerView recyclerView;
    private VegetableAdapter vegetableAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grocery, container, false);

        // Initialize the RecyclerView with vegetable data
        recyclerView = view.findViewById(R.id.recyclerview);
        ArrayList<VegetableModel> recyclerList = new ArrayList<>();
        recyclerList.add(new VegetableModel(21,"Bread", R.drawable.bread));
        recyclerList.add(new VegetableModel(22,"Rice", R.drawable.rice));
        recyclerList.add(new VegetableModel(23, "Nutella", R.drawable.nutella));
        recyclerList.add(new VegetableModel(24,"Cookie", R.drawable.cookie));
        recyclerList.add(new VegetableModel(25,"Sauce", R.drawable.sauce));
        recyclerList.add(new VegetableModel(26,"Chocolate", R.drawable.chocolate));
        recyclerList.add(new VegetableModel(27,"Maggi", R.drawable.maggi));
        recyclerList.add(new VegetableModel(28,"Coffee", R.drawable.coffee));
        recyclerList.add(new VegetableModel(29,"Cereals", R.drawable.cereals));
        recyclerList.add(new VegetableModel(30,"Honey", R.drawable.honey));
        // Add more vegetables to the list...

        // Initialize the VegetableAdapter with the list of vegetables
        vegetableAdapter = new VegetableAdapter(recyclerList, requireContext());

        // Set the layout manager and adapter for the RecyclerView
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        recyclerView.setAdapter(vegetableAdapter);

        // Find and set up the View Cart button
        Button viewCartButton = view.findViewById(R.id.placeorder);
        viewCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToOrderPage();
            }
        });

        return view;
    }

    // Method to navigate to the OrderPage
    private void navigateToOrderPage() {
        // Create an intent to launch the OrderPage
        Intent intent = new Intent(requireContext(), OrderPage.class);
        // Start the OrderPage activity
        startActivity(intent);
    }
}
