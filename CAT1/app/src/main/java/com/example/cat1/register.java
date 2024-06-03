package com.example.cat1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class register extends AppCompatActivity {
    EditText name,email,phone;
    Button btnDatePicker;
    TextView tvSelectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        btnDatePicker = findViewById(R.id.date);
        tvSelectedDate = findViewById(R.id.date_sel);
        Calendar calendar=Calendar.getInstance();
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(calendar, tvSelectedDate);
            }
        });

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
    private void updateSelectedDate(Calendar calendar, TextView tvSelectedDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String selectedDate = dateFormat.format(calendar.getTime());
        tvSelectedDate.setText(selectedDate);
    }

}