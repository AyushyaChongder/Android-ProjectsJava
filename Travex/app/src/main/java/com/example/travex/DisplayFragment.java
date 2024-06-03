package com.example.travex;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DisplayFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_display, container, false);

        // Get arguments from bundle
        Bundle bundle = getArguments();
        if (bundle != null) {
            String email = bundle.getString("email");
            String password = bundle.getString("password");

            // Set values in TextViews
            TextView emailTextView = rootView.findViewById(R.id.eml);
            TextView passwordTextView = rootView.findViewById(R.id.pwd);

            if (emailTextView != null && passwordTextView != null) {
                emailTextView.setText(email);
                passwordTextView.setText(password);
            }
        }

        return rootView;
    }
}
