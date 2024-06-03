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

public class VeggiesFragment extends Fragment {

    private RecyclerView recyclerView;
    VegetableAdapter vegetableAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_veggies, container, false);

        // Initialize the RecyclerView with vegetable data
        recyclerView = view.findViewById(R.id.recyclerview);
        ArrayList<VegetableModel> vegetableList = new ArrayList<>();
        vegetableList.add(new VegetableModel(11,"Tomato", R.drawable.tomato));
        vegetableList.add(new VegetableModel(12,"Potato", R.drawable.potato));
        vegetableList.add(new VegetableModel(13,"Chilli", R.drawable.chilli));
        vegetableList.add(new VegetableModel(14,"Cucumber", R.drawable.cucumber));
        vegetableList.add(new VegetableModel(15,"Cabbage", R.drawable.cabbage));
        vegetableList.add(new VegetableModel(16,"Brinjal", R.drawable.brinjal));
        vegetableList.add(new VegetableModel(17,"Onion", R.drawable.onion));
        vegetableList.add(new VegetableModel(18,"Capsicum", R.drawable.capsicum));
        vegetableList.add(new VegetableModel(19,"Beetroot", R.drawable.beetroot));
        vegetableList.add(new VegetableModel(20,"Pumpkin", R.drawable.pumpkin));
        // Add more vegetables to the list...
        vegetableAdapter = new VegetableAdapter(vegetableList, requireContext());
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
