package com.example.myhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class signup extends AppCompatActivity {
    Button start,end,submit;
    TextView start_sel,end_sel,name,age,email,phone,days;
    CheckBox check1,check2,check3;
    ToggleButton btn_yes_no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        start=findViewById(R.id.start);
        end=findViewById(R.id.end);
        start_sel=findViewById(R.id.start_date);
        end_sel=findViewById(R.id.end_date);
        name=findViewById(R.id.name);
        age=findViewById(R.id.age);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        days=findViewById(R.id.days);
        check1=findViewById(R.id.regular);
        check2=findViewById(R.id.irregular);
        check3=findViewById(R.id.nocycle);
        btn_yes_no=findViewById(R.id.yes_no);
        submit=findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showRatingDialog();


            }
        });

        Calendar calendar=Calendar.getInstance();
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog1(calendar, start_sel);
            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog2(calendar, end_sel);
            }
        });

    }
    private void showDatePickerDialog1(final Calendar calendar, final TextView start_sel) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year, month, dayOfMonth);
                        updateSelectedDate1(calendar, start_sel);
                    }
                },
                year,
                month,
                day
        );

        datePickerDialog.show();
    }
    private void updateSelectedDate1(Calendar calendar, TextView start_sel) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String selectedDate = dateFormat.format(calendar.getTime());
        start_sel.setText(selectedDate);
    }
    private void showDatePickerDialog2(final Calendar calendar, final TextView end_sel) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year, month, dayOfMonth);
                        updateSelectedDate2(calendar, end_sel);
                    }
                },
                year,
                month,
                day
        );

        datePickerDialog.show();
    }
    private void updateSelectedDate2(Calendar calendar, TextView end_sel) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String selectedDate = dateFormat.format(calendar.getTime());
        end_sel.setText(selectedDate);
    }
    private void showRatingDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Detail Submission");
        builder.setMessage("Do you want to submit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Here you can add the code to submit the rating to your backend or perform any action.
                // For now, let's just display a Toast to show that the rating is submitted.
                Toast.makeText(signup.this, "Details submitted", Toast.LENGTH_SHORT).show();
                String uname=name.getText().toString();
                String uphone=phone.getText().toString();
                String uage=age.getText().toString();
                String uemail=email.getText().toString();
                String udays=days.getText().toString();
                String ustart_date=start_sel.getText().toString();
                String uend_date=end_sel.getText().toString();

                Intent intent=new Intent(signup.this, displaypage.class);
                intent.putExtra("name", uname);
                intent.putExtra("phone", uphone);
                intent.putExtra("email", uemail);
                intent.putExtra("age", uage);
                intent.putExtra("start_d", ustart_date);
                intent.putExtra("end_d", uend_date);

                startActivity(intent);


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