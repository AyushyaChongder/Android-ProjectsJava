package com.example.a2247214_cat3;

import android.database.Cursor;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Home extends Fragment {



    View view;

    private String email;

    public Home(String e) {
        this.email = e;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_home, container, false);

        TextView profile_name = view.findViewById(R.id.profile_name);


        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(requireContext());
        Cursor cursor = databaseHelper.getUserData(email);

        if (cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            profile_name.setText("Hi, "+name);

        }



        return view;

    }
}
