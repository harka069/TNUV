package com.example.sparovcek2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private Calendar calendar;
    private DatePickerDialog datePickerDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //odhodek ali prihodek
        Spinner spinnerVrsta = findViewById(R.id.spinnerVrsta);
        // Create an ArrayAdapter and set it as the adapter for the Spinner
        ArrayAdapter<CharSequence> adapterVrsta = ArrayAdapter.createFromResource(
                MainActivity.this,
                R.array.array_vrsta,
                R.layout.spinner_dropdown_item
        );
        adapterVrsta.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerVrsta.setAdapter(adapterVrsta);


        //izbira kategorij
        Spinner spinner = findViewById(R.id.spinnerCategory);
        // Create an ArrayAdapter and set it as the adapter for the Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                MainActivity.this,
                R.array.array_odhodkov,
                R.layout.spinner_dropdown_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //koledar
        final TextView textView = findViewById(R.id.editTextDate);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });
        calendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(
                MainActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Update the selected date in the TextView
                        String date = dayOfMonth + "/" + (month + 1) + "/" + year;
                        textView.setText(date);
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));


        //pritisk tipke shrani odhodek
        Button submitButton = findViewById(R.id.buttonOdhodekSave);
        submitButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                showToast();
            }
        });
    }



    private void showToast() {
        Toast.makeText(MainActivity.this, "Odhodek zabeležen!", Toast.LENGTH_SHORT).show();
    }
    private void showDatePicker() {
        datePickerDialog.show();
    }
}