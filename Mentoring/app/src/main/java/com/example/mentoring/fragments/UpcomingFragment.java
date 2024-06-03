package com.example.mentoring.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mentoring.R;
public class UpcomingFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upcoming, container, false);

        Button showStudentsButton = view.findViewById(R.id.show_students_button);
        showStudentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace the current fragment with StudentListFragment
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new StudentList())
                        .addToBackStack(null)
                        .commit();
            }
        });

        // Rest of your code for UpcomingFragment

        return view;
    }
}
