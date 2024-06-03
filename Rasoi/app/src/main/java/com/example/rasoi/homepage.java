package com.example.rasoi;

import static com.example.rasoi.R.id.list;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.ViewSwitcher;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class homepage extends AppCompatActivity {
TextView uid;
Button filter,product,recipe;
Spinner menu;
SeekBar seekBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        String uname=getIntent().getStringExtra("uname");
        uid=findViewById(R.id.username1);
        uid.setText(uname);
        filter=findViewById(R.id.filters);
        product=findViewById(R.id.product);
        recipe=findViewById(R.id.recipe);
        seekBar = findViewById(R.id.progress);
        menu=findViewById(R.id.list);

        if (seekBar != null) {
            seekBar.setOnSeekBarChangeListener(
                    new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(
                                SeekBar seekBar, int progress,
                                boolean fromUser)
                        {

                        }

                        @Override
                        public void onStartTrackingTouch(
                                SeekBar seekBar)
                        {

                        }

                        @Override
                        public void onStopTrackingTouch(
                                SeekBar seekBar)
                        {
                            // Write code to perform some action
                            // when touch is stopped.
                            Toast
                                    .makeText(
                                            homepage.this,
                                            "Current value is "
                                                    + seekBar.getProgress(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    });
        }

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();

            }
        });

        product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog_1();

            }
        });
        recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialog_2();
            }
        });


    }
    private void showDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.filters_bottom_sheet);

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations=R.style.DialoAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

            FloatingActionButton spice1=dialog.findViewById(R.id.mild);
        FloatingActionButton spice2=dialog.findViewById(R.id.medium);
        FloatingActionButton spice3=dialog.findViewById(R.id.hot);
        spice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "You have selected MILD spice level!", Snackbar.LENGTH_LONG).show();
            }
        });
        spice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "You have selected MEDIUM spice level!", Snackbar.LENGTH_LONG).show();
            }
        });
        spice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "You have selected EXTREME spice level!", Snackbar.LENGTH_LONG).show();
            }
        });
        dialog.show();

    }
    private void showDialog_1() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.product_bottom_sheet);

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialoAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        Button btnDatePicker = dialog.findViewById(R.id.date);
        Button btnTimePicker = dialog.findViewById(R.id.time);
        TextView tvSelectedDate = dialog.findViewById(R.id.date_sel);
        TextView tvSelectedTime = dialog.findViewById(R.id.time_sel);
        ToggleButton toggleButton = dialog.findViewById(R.id.toggleButton);
        Calendar calendar=Calendar.getInstance();
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(calendar, tvSelectedDate);
            }
        });

        btnTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(calendar, tvSelectedTime);
            }
        });
        dialog.show();
    }


    private void showDatePickerDialog(final Calendar calendar, final TextView tvSelectedDate) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year, month, dayOfMonth);
                        updateSelectedDate(calendar, tvSelectedDate);
                    }
                },
                year,
                month,
                day
        );

        datePickerDialog.show();
    }

    private void showTimePickerDialog(final Calendar calendar, final TextView tvSelectedTime) {
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                        updateSelectedTime(calendar, tvSelectedTime);
                    }
                },
                hour,
                minute,
                true
        );

        timePickerDialog.show();
    }

    private void updateSelectedDate(Calendar calendar, TextView tvSelectedDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String selectedDate = dateFormat.format(calendar.getTime());
        tvSelectedDate.setText(selectedDate);
    }

    private void updateSelectedTime(Calendar calendar, TextView tvSelectedTime) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String selectedTime = timeFormat.format(calendar.getTime());
        tvSelectedTime.setText(selectedTime);
    }

    private void showDialog_2() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.recipe_bottom_sheet);

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialoAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        WebView adv=dialog.findViewById(R.id.advertise);
        RatingBar ratingBar = dialog.findViewById(R.id.stars);
        Button display=dialog.findViewById(R.id.show_rating);
        ImageSwitcher imageSwitcher = dialog.findViewById(R.id.imageSwitcher);
        Button nextButton =dialog.findViewById(R.id.next);
        int imageSwitcherImages[] = {R.drawable.img1, R.drawable.img2, R.drawable.img3};
        int switcherImageLength = imageSwitcherImages.length;
        int[] counter = {-1};
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView switcherImageView = new ImageView(getApplicationContext());
                switcherImageView.setLayoutParams(new ImageSwitcher.LayoutParams(
                        ActionBar.LayoutParams.FILL_PARENT, ActionBar.LayoutParams.FILL_PARENT
                ));
                switcherImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                switcherImageView.setImageResource(R.drawable.img1);
                //switcherImageView.setMaxHeight(100);
                return switcherImageView;
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter[0]++;
                if (counter[0] == switcherImageLength){
                    counter[0] = 0;
                    imageSwitcher.setImageResource(imageSwitcherImages[counter[0]]);
                }
                else{
                    imageSwitcher.setImageResource(imageSwitcherImages[counter[0]]);
                }
            }
        });
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float rating = ratingBar.getRating();
                showRatingDialog(rating);
            }
        });
        adv.loadUrl("https://www.simplyrecipes.com/");
        adv.setWebViewClient(new WebViewClient());
        dialog.show();
}
    private void showRatingDialog(float rating) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Rating");
        builder.setMessage("Do you want to submit a rating of " + rating + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Here you can add the code to submit the rating to your backend or perform any action.
                // For now, let's just display a Toast to show that the rating is submitted.
                Toast.makeText(homepage.this, "Rating submitted: " + rating, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User canceled the rating submission.
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
