package com.example.coffeeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Model> modelList;
    RecyclerView recyclerView;
    OrderAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // creating an arraylist

        modelList = new ArrayList<>();
        modelList.add(new Model("Green Tea", getString(R.string.greentea), R.drawable.teapot ));
        modelList.add(new Model("Latte", getString(R.string.latte), R.drawable.coffecup));
        modelList.add(new Model("Orange Smoothie", getString(R.string.orangesmoothie), R.drawable.icedcoffee));
        modelList.add(new Model("Orange Vanilla", getString(R.string.orangevanilla), R.drawable.coffee));
        modelList.add(new Model("Cappucino", getString(R.string.cappcuni), R.drawable.cofcup));
        modelList.add(new Model("Thai Tea", getString(R.string.thaitea), R.drawable.bt1));
        modelList.add(new Model("Tea", getString(R.string.tea), R.drawable.coffeecup));
        modelList.add(new Model("Bubble Tea", getString(R.string.bubbletea), R.drawable.bt));
        modelList.add(new Model("Matcha", getString(R.string.match), R.drawable.latte));

        // recyclerview
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(null));
        // adapter
        mAdapter = new OrderAdapter(this, modelList);
        recyclerView.setAdapter(mAdapter);




    }
}