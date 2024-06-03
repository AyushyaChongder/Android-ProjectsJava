package com.example.mentoring.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mentoring.R;

public class StudentDetails extends Fragment {

    private String studentName;
    private String previousSessionText;
    private String todaysSessionText;

    public StudentDetails() {
        // Required empty public constructor
    }

    public static StudentDetails newInstance(String name, String previousSession, String todaysSession) {
        StudentDetails fragment = new StudentDetails();
        Bundle args = new Bundle();
        args.putString("studentName", name);
        args.putString("previousSessionText", previousSession);
        args.putString("todaysSessionText", todaysSession);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_details, container, false);

        // Initialize views and retrieve arguments
        TextView nameTextView = view.findViewById(R.id.textName);
        TextView previousSessionTextView = view.findViewById(R.id.textPreviousSession);
        EditText todaysSessionEditText = view.findViewById(R.id.editTodaysSession);

        if (getArguments() != null) {
            studentName = getArguments().getString("studentName");
            previousSessionText = getArguments().getString("previousSessionText");
            todaysSessionText = getArguments().getString("todaysSessionText");
        }

        // Populate views
        nameTextView.setText(studentName);
        previousSessionTextView.setText(previousSessionText);
        todaysSessionEditText.setText(todaysSessionText);

        return view;
    }
}
