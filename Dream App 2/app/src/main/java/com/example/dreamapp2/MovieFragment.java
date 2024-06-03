package com.example.dreamapp2;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

public class MovieFragment extends Fragment {
    View view;
    CardView card1, card2, card3, card4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_movie, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        card1 = view.findViewById(R.id.card1);
        card2 = view.findViewById(R.id.card2);
        card3 = view.findViewById(R.id.card3);
        card4 = view.findViewById(R.id.card4);

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Webcontents.class);
                String value = "https://www.rottentomatoes.com/m/oppenheimer_2023";
                intent.putExtra("key", value);
                startActivity(intent);
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Webcontents.class);
                String value = "https://www.rottentomatoes.com/m/barbie";
                intent.putExtra("key", value);
                startActivity(intent);
            }
        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Webcontents.class);
                String value = "https://www.rottentomatoes.com/m/mission_impossible_dead_reckoning_part_one";
                intent.putExtra("key", value);
                startActivity(intent);
            }
        });

        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Webcontents.class);
                String value = "https://www.rottentomatoes.com/m/spider_man_across_the_spider_verse";
                intent.putExtra("key", value);
                startActivity(intent);
            }
        });
    }
}