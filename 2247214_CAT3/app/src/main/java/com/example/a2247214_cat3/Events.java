package com.example.a2247214_cat3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Events extends Fragment {

    public Events() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<MyModel> dataList = new ArrayList<>();
        dataList.add(new MyModel(R.drawable.coding, "Coding"));
        dataList.add(new MyModel(R.drawable.coding, "Web Development"));
        dataList.add(new MyModel(R.drawable.coding, "Design"));

        MyAdapter adapter = new MyAdapter(dataList, requireContext());
        recyclerView.setAdapter(adapter);

        return view;
    }
}
