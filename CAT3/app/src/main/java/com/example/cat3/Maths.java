package com.example.cat3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cat3.fragments.CheckedItemsFragment;

import java.util.ArrayList;
import java.util.List;

public class Maths extends AppCompatActivity {

    private RecyclerView recyclerViewMath;
    private MathAdapter mathAdapter;
    private List<MathModel> mathModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maths);

        recyclerViewMath = findViewById(R.id.recyclerViewMath);
        Button displayCheckedButton = findViewById(R.id.displayCheckedButton); // Add this button in your layout

        mathModelList = new ArrayList<>();
        mathAdapter = new MathAdapter(this, mathModelList);

        recyclerViewMath.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewMath.setAdapter(mathAdapter);

        // Sample data for mathModelList
        mathModelList.add(new MathModel("Number Systems", false));
        mathModelList.add(new MathModel("Profit and Loss", false));
        mathModelList.add(new MathModel("Simple Interest", false));
        mathModelList.add(new MathModel("Banking", false));
        mathModelList.add(new MathModel("Compound Interest", false));
        mathModelList.add(new MathModel("Algebra", false));
        mathModelList.add(new MathModel("Differentiation", false));
        mathModelList.add(new MathModel("Integration", false));

        mathAdapter.notifyDataSetChanged();

        // Button click event to display checked items
        displayCheckedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the checked items from the adapter
                List<MathModel> checkedItems = mathAdapter.getCheckedItems();

                // Pass the checked items to the fragment
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("checkedItems", new ArrayList<>(checkedItems));

                // Replace the current fragment with CheckedItemsFragment
                CheckedItemsFragment checkedItemsFragment = new CheckedItemsFragment();
                checkedItemsFragment.setArguments(bundle);

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, checkedItemsFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
