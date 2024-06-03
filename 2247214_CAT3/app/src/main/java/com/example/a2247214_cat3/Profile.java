package com.example.a2247214_cat3;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends Fragment {

    View view;
    private TextView profile_name, profile_email, profile_password, profile_post;

    private String email;

    public Profile(String email) {
        this.email = email;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        profile_name = view.findViewById(R.id.profile_name);
        profile_email = view.findViewById(R.id.profile_email);
        profile_password = view.findViewById(R.id.profile_password);
        profile_post = view.findViewById(R.id.profile_post);

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(requireContext());
        Cursor cursor = databaseHelper.getUserData(email);

        if (cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
            String password = cursor.getString(cursor.getColumnIndexOrThrow("password"));
            String post = cursor.getString(cursor.getColumnIndexOrThrow("post"));
            profile_name.setText(name);
            profile_email.setText(email);
            profile_password.setText(password);
            profile_post.setText(post);
        }



        ImageView update = view.findViewById(R.id.imageView3);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the context of the hosting Activity
                if (getActivity() != null) {
                    Intent intent = new Intent(getActivity(), UpdateActivity.class);
                    startActivity(intent);
                }
            }
        });

        ImageView delete = view.findViewById(R.id.imageView4);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Perform the delete operation
                boolean isDeleted = databaseHelper.deleteUser(email);

                if (isDeleted) {
                    // Deletion was successful, you can provide feedback to the user
                    Toast.makeText(getContext(), "User deleted successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    // Optionally, update your UI or navigate to a different screen
                    // For example, if this code is inside a Fragment, you might want to refresh the list of users.
                    // If it's in an Activity, you could navigate back to the user list, etc.
                } else {
                    // Deletion failed, provide feedback to the user
                    Toast.makeText(getContext(), "Failed to delete user", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }

}
