package com.example.sparovcek2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private Calendar calendar;
    private DatePickerDialog datePickerDialog;

    private Spinner tip;
    private Spinner kategorija;
    private EditText cena;
    private EditText opis;

    private String datum;



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
                        datum = dayOfMonth + "/" + (month + 1) + "/" + year;
                        textView.setText(datum);
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));

        //vnos cene
        cena = findViewById(R.id.editTextAmount);

        //vnos opisa transakcije
        opis = findViewById(R.id.editDescription);

        //pritisk tipke shrani odhodek
        Button submitButton = findViewById(R.id.buttonOdhodekSave);
        submitButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "database-name").build();

                DAO_class Dao = db.Dao();

                new Thread() {

                    @Override
                    public void run() {
                        String tiptransakcije = spinnerVrsta.getSelectedItem().toString();
                        String datumtransakcije = datum;
                        String kategorijatransakcije = spinner.getSelectedItem().toString();
                        float cenatransakcije = Float.parseFloat(cena.getText().toString());
                        String opistransakcije = opis.getText().toString();

                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        Date datum = null;
                        try {
                            datum = format.parse(datumtransakcije);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        long time = datum.toInstant().toEpochMilli()/1000L;

                        Transaction record = new Transaction();
                        record.setType(tiptransakcije);
                        record.setDate(time);
                        record.setCategory(kategorijatransakcije);
                        record.setSum(cenatransakcije);
                        record.setDescription(opistransakcije);

                        Dao.insertRecord(record);


                    }
                }.start();

                showToast();

                spinnerVrsta.setSelection(0);
                textView.setText("");
                spinner.setSelection(0);
                cena.setText("");
                opis.setText("");

                //
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
        Button buttonPregled = findViewById(R.id.buttonPregled);
        buttonPregled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to launch the new activity
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
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
