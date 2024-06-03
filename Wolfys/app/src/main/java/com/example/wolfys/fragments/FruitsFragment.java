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

public class FruitsFragment extends Fragment {

    private RecyclerView recyclerView;
    VegetableAdapter vegetableAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fruits, container, false);

        // Initialize the RecyclerView with vegetable data
        recyclerView = view.findViewById(R.id.recyclerview);
        ArrayList<VegetableModel> recyclerList = new ArrayList<>();
        recyclerList.add(new VegetableModel(1,"Apple", R.drawable.apple));
        recyclerList.add(new VegetableModel(2,"Banana", R.drawable.banana));
        recyclerList.add(new VegetableModel(3,"Blueberries", R.drawable.blueberries));
        recyclerList.add(new VegetableModel(4,"Mango", R.drawable.mango));
        recyclerList.add(new VegetableModel(5,"Pineapple", R.drawable.pineapple));
        recyclerList.add(new VegetableModel(6,"Pomegranate", R.drawable.pomegranate));
        recyclerList.add(new VegetableModel(7,"Grapes", R.drawable.grapes));
        recyclerList.add(new VegetableModel(8,"Guava", R.drawable.guava));
        recyclerList.add(new VegetableModel(9,"Watermelon", R.drawable.watermelon));
        recyclerList.add(new VegetableModel(10,"Dragonfruit", R.drawable.dragonfruit));
        // Add more vegetables to the list...
        vegetableAdapter = new VegetableAdapter(recyclerList, requireContext());
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        recyclerView.setAdapter(vegetableAdapter);
        Button viewCartButton = view.findViewById(R.id.placeorder);
        viewCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToOrderPage();
            }
        });

        return view;
    }
    private void navigateToOrderPage() {
        // Create an intent to launch the OrderPage
        Intent intent = new Intent(requireContext(), OrderPage.class);
        // Start the OrderPage activity
        startActivity(intent);
    }
}
