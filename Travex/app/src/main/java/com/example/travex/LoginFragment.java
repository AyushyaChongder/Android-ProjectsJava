package com.example.travex;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class LoginFragment extends Fragment {
    EditText email_txt, password_txt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        email_txt = rootView.findViewById(R.id.email);
        password_txt = rootView.findViewById(R.id.password);

        Button loginButton = rootView.findViewById(R.id.login_main);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = email_txt.getText().toString();
                String password = password_txt.getText().toString();

                // Get reference to the MainActivity and call its method
                MainActivity mainActivity = (MainActivity) getActivity();
                if (mainActivity != null) {
                    mainActivity.onLogin(email, password);
                }
            }
        });

        return rootView;
    }
}
