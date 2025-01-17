package com.example.cat3.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cat3.Model;
import com.example.cat3.MyViewPagerAdapter;
import com.example.cat3.R;
import com.example.cat3.TopicAdapter;

import java.util.ArrayList;
import java.util.List;


public class TopicFragment extends Fragment {
    RecyclerView recyclerView;
    TopicAdapter mAdapter;
    List<Model> modelList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topic, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        modelList = new ArrayList<>();
        modelList.add(new Model("Maths", R.drawable.maths));
        modelList.add(new Model("Physics", R.drawable.physics));
        modelList.add(new Model("Chemistry", R.drawable.chemistry));
        modelList.add(new Model("Biology", R.drawable.biology));

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        // Initialize and set the adapter
        mAdapter = new TopicAdapter(getActivity(), modelList);
        recyclerView.setAdapter(mAdapter);



        // Inflate the layout for this fragment
        return view;
    }
}