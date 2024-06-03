package com.example.dreamapp2;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class AddProfile extends AppCompatActivity {

    TextInputLayout usernameL;
    Button submit, chooseImageButton;
    Switch movies, music;
    CheckBox terms;
    Spinner stateSpinner;
    RadioGroup gender;
    String selectedState;
    FirebaseDatabase database;
    DatabaseReference profileRef;
    private ActivityResultLauncher<String> imagePickerLauncher;
    ImageView profileImage;
    Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profile);

        database = FirebaseDatabase.getInstance("https://dream-app-62cc8-default-rtdb.asia-southeast1.firebasedatabase.app/");
        profileRef = database.getReference("user-profiles");

        usernameL = findViewById(R.id.username);
        submit = findViewById(R.id.submit);
        movies = findViewById(R.id.movies_switch);
        music = findViewById(R.id.music_switch);
        terms = findViewById(R.id.tAndC);
        stateSpinner = findViewById(R.id.states);
        profileImage = findViewById(R.id.profile_image);
        chooseImageButton = findViewById(R.id.choose_image_button);


        // Set up image picker launcher
        imagePickerLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        if (result != null) {
                            profileImage.setImageURI(result);
                            imageUri = result;
                        }
                    }
                });

        // Set click listener for "Choose Image" button
        chooseImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Launch image picker
                imagePickerLauncher.launch("image/*");
            }
        });


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.states, R.layout.spinner_text);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        stateSpinner.setAdapter(adapter);

        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedState = adapterView.getItemAtPosition(i).toString();
                Log.d("Debug", "Selected state: " + selectedState);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.d("Debug", "No state selected");
            }
        });

        movies.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    music.setChecked(false);
                }
                else {
                    music.setChecked(true);
                }
            }
        });

        music.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    movies.setChecked(false);
                }
                else {
                    movies.setChecked(true);
                }
            }
        });

        terms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    submit.setClickable(true);
                    submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            usernameL = findViewById(R.id.username);
                            movies = findViewById(R.id.movies_switch);
                            music = findViewById(R.id.music_switch);
                            terms = findViewById(R.id.tAndC);
                            stateSpinner = findViewById(R.id.states);
                            gender = findViewById(R.id.radioGroup);

                            String username = usernameL.getEditText().getText().toString().trim();
                            String switchText;

                            if (movies.isChecked()) {
                                switchText = movies.getText().toString();
                            }
                            else {
                                switchText = music.getText().toString();
                            }

                            int selectedId = gender.getCheckedRadioButtonId();
                            RadioButton selectedRadioButton = findViewById(selectedId);
                            String selectedGender = selectedRadioButton.getText().toString();

                            //get the user ID
                            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

                            // reference to the user's profile in the database
                            DatabaseReference userRef = FirebaseDatabase.getInstance("https://dream-app-62cc8-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("profiles").child(userId);

                            //create a hashmap to store the profile data
                            HashMap<String, Object> profileData = new HashMap<>();
                            profileData.put("Username", username);
                            profileData.put("Use", switchText);
                            profileData.put("Gender", selectedGender);
                            profileData.put("State", selectedState);

                            userRef.setValue(profileData).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(AddProfile.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(AddProfile.this, LoggedIn.class));
                                    finish();
                                }
                            })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(AddProfile.this, "Profile updated failed", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                            if (imageUri != null) {
                                StorageReference storageRef = FirebaseStorage.getInstance("gs://dream-app-62cc8.appspot.com").getReference();
                                StorageReference profileImageRef = storageRef.child("profile_images/" + userId + ".jpg");

                                profileImageRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                            @Override
                                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                                profileImageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                    @Override
                                                    public void onSuccess(Uri uri) {
                                                        // Store the relative path to the image in the Realtime Database
                                                        String imagePath = "profile_images/" + userId + ".jpg";
                                                        profileData.put("ProfileImage", imagePath);

                                                        // Reference to the user's profile in the database
                                                        DatabaseReference userRef = FirebaseDatabase.getInstance("https://dream-app-62cc8-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("profiles").child(userId);
                                                        userRef.setValue(profileData).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                    @Override
                                                                    public void onSuccess(Void unused) {
                                                                        Toast.makeText(AddProfile.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
                                                                        startActivity(new Intent(AddProfile.this, LoggedIn.class));
                                                                        finish();
                                                                    }
                                                                })
                                                                .addOnFailureListener(new OnFailureListener() {
                                                                    @Override
                                                                    public void onFailure(@NonNull Exception e) {
                                                                        Toast.makeText(AddProfile.this, "Profile update failed", Toast.LENGTH_SHORT).show();
                                                                    }
                                                                });
                                                    }
                                                });
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(AddProfile.this, "Profile update failed", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        }
                    });
                }
                else {
                    submit.setClickable(false);
                }
            }
        });
    }
}