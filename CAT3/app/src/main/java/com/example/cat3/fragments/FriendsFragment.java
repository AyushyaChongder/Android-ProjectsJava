package com.example.cat3.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cat3.DatabaseHelper;
import com.example.cat3.FriendModel;
import com.example.cat3.FriendsAdapter;
import com.example.cat3.R;

import java.util.ArrayList;
import java.util.List;

public class FriendsFragment extends Fragment {
    private RecyclerView recyclerView;
    private FriendsAdapter mAdapter;
    private List<FriendModel> friendList;
    private DatabaseHelper databaseHelper;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friends, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        friendList = new ArrayList<>();
        Context context = getActivity(); // or use any valid context
        mAdapter = new FriendsAdapter(context,friendList);
        recyclerView.setAdapter(mAdapter);

        // Initialize the database helper
        databaseHelper = new DatabaseHelper(requireContext());

        // Fetch names from the database and update the friendList
        ArrayList<String> namesList = databaseHelper.getAllNames();
        for (String name : namesList) {
            friendList.add(new FriendModel(name));
        }

        mAdapter.notifyDataSetChanged();

        return view;
    }
}
