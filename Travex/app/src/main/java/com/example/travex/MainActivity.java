package com.example.travex;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_btn = findViewById(R.id.login);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show the LoginFragment
                showLoginFragment();
            }
        });
    }

    private void showLoginFragment() {
        LoginDialogFragment loginFragment = new LoginDialogFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, loginFragment)
                .addToBackStack(null)
                .commit();
    }

    // Method to receive login data from LoginFragment
    public void onLogin(String email, String password) {
        // Handle login data here, e.g., display another fragment
        // with the data passed to it
        DisplayDialogFragment anotherFragment = new DisplayDialogFragment();

        // Bundle the data and set it to the fragment
        Bundle bundle = new Bundle();
        bundle.putString("email", email);
        bundle.putString("password", password);
        anotherFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, anotherFragment)
                .addToBackStack(null)
                .commit();
    }
}
