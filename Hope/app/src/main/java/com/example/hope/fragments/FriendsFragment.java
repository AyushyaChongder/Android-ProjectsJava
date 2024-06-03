package com.example.hope.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hope.FriendModel;
import com.example.hope.FriendsAdapter;
import com.example.hope.R;

import java.util.ArrayList;
import java.util.List;

public class FriendsFragment extends Fragment {
    private RecyclerView recyclerView;
    private FriendsAdapter mAdapter;
    private List<FriendModel> friendList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friends, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        friendList = new ArrayList<>();
        mAdapter = new FriendsAdapter(requireContext(), friendList);
        recyclerView.setAdapter(mAdapter);

        // Manually add some activities (replace this with your actual data)
        friendList.add(new FriendModel("SP Road", "2023-10-10", "Help for Donation Drive"));
        friendList.add(new FriendModel("Koramangala", "2023-11-10", "Food Drive"));
        friendList.add(new FriendModel("Hosur Road", "2023-12-15", "Clothes Drive"));

        mAdapter.notifyDataSetChanged();

        return view;
    }
}
