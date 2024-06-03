package com.example.mentoring.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mentoring.Model;
import com.example.mentoring.R;
import com.example.mentoring.StudentAdapter;

import java.util.ArrayList;
import java.util.List;

public class StudentList extends Fragment {

   RecyclerView recyclerView;
    StudentAdapter mAdapter;
    List<Model> modelList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.current_student_list, container, false);

        // Initialize your list of students
        modelList = new ArrayList<>();
        modelList.add(new Model("Aniket Singh", R.drawable.profile));
        modelList.add(new Model("Sagnik Mukhopadhyay", R.drawable.profile));
        modelList.add(new Model("Debanjan Basu", R.drawable.profile));
        modelList.add(new Model("Darpan Deb", R.drawable.profile));
        modelList.add(new Model("Rajarshi Barman", R.drawable.profile));
        modelList.add(new Model("Arunabh Krishna", R.drawable.profile));
        modelList.add(new Model("Ayushya Chongder", R.drawable.profile));
        modelList.add(new Model("Anushka Banerjee", R.drawable.profile));
        modelList.add(new Model("Tiyasa Paul", R.drawable.profile));
        modelList.add(new Model("Aadrita Banerjee", R.drawable.profile));

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Initialize and set the adapter
        mAdapter = new StudentAdapter(getActivity(), modelList);
        recyclerView.setAdapter(mAdapter);

        // Set an item click listener for the adapter
        mAdapter.setOnItemClickListener(new StudentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Model model) {
                // Replace the current fragment with StudentDetailsFragment
                String studentName = model.getStuName();
                String previousSessionText = "Previous session details"; // Replace with actual data
                String todaysSessionText = "Today's session details";     // Replace with actual data
                StudentDetails detailsFragment = StudentDetails.newInstance(studentName, previousSessionText, todaysSessionText);
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, detailsFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        return view;
    }
}
