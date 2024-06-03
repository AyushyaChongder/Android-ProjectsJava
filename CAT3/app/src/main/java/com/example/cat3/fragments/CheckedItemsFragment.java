package com.example.cat3.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cat3.CheckedItemsAdapter;
import com.example.cat3.MathModel;
import com.example.cat3.R;
import com.example.cat3.checkitemModel;

import java.util.ArrayList;
import java.util.List;

public class CheckedItemsFragment extends Fragment {
    private RecyclerView recyclerViewChecked;
    private CheckedItemsAdapter checkedItemsAdapter;
    private List<MathModel> checkedItemsList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checked_items, container, false);

        recyclerViewChecked = view.findViewById(R.id.recyclerViewChecked);
        recyclerViewChecked.setLayoutManager(new LinearLayoutManager(requireContext()));

        checkedItemsList = new ArrayList<>();

        // Retrieve checked items from arguments
        Bundle bundle = getArguments();
        if (bundle != null) {
            ArrayList<MathModel> checkedItems = bundle.getParcelableArrayList("checkedItems");
            if (checkedItems != null) {
                checkedItemsList.addAll(checkedItems);
            }
        }

        checkedItemsAdapter = new CheckedItemsAdapter(requireContext(), checkedItemsList);
        recyclerViewChecked.setAdapter(checkedItemsAdapter);
        Button downloadButton = view.findViewById(R.id.download);

        // Set a click listener for the "Download" button
        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display a Toast message when the button is clicked
                Toast.makeText(requireContext(), "Your Notes will be sent to your email!", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}

