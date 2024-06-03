package com.example.cat3;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemsPage extends AppCompatActivity {

    private RecyclerView recyclerViewCheckedItems;
    private MathAdapter mathAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_page);

        recyclerViewCheckedItems = findViewById(R.id.recyclerViewChecked); // Use the correct RecyclerView ID
        mathAdapter = new MathAdapter(this, getCheckedItems()); // Initialize adapter with checked items

        recyclerViewCheckedItems.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCheckedItems.setAdapter(mathAdapter);
    }

    // Method to get checked items
    private List<MathModel> getCheckedItems() {
        // Assuming you have a reference to the MathAdapter (mathAdapter) in your activity
        return mathAdapter.getCheckedItems();
    }
}
