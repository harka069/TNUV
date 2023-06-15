package com.example.sparovcek2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.room.Room;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);


    Button ButtonPregled= findViewById(R.id.btnPregled);
       ButtonPregled.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            // Create an Intent to launch the new activity
            Intent intent = new Intent(MainActivity1.this, MainActivity2.class);
            startActivity(intent);
        }
    });
    Button ButtonVnesiSpremembo= findViewById(R.id.btnVnesiSpremembo);
    ButtonVnesiSpremembo.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            // Create an Intent to launch the new activity
            Intent intent = new Intent(MainActivity1.this, MainActivity3.class);
            startActivity(intent);
        }
    });
    Button ButtonExportCSV= findViewById(R.id.exportCSV);
    ButtonExportCSV.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, "database-name").build();

            DAO_class Dao = db.Dao();
            // Create an Intent to launch the new activity
            new Thread() {

                @Override
                public void run() {
                    try {
                        File towrite = new File(getApplicationContext().getFilesDir(), "saldo.csv");
                        FileWriter writer = new FileWriter(towrite, false);
                        Uri fileUri = FileProvider.getUriForFile(MainActivity1.this, getPackageName() + ".provider", towrite);

                        List<Transaction> odg = Dao.getAll();
                        for (Transaction trans : odg) {
                            long timestamp = trans.getDate();
                            Date date = new Date(timestamp*1000);

                            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                            String datum = format.format(date);
                            writer.write(datum + "," + trans.getType() + "," + Float.toString(trans.getSum()) + ","+trans.getCategory() + "," + trans.getDescription() + System.lineSeparator());
                        }

                        writer.flush();
                        writer.close();

                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_SEND);
                        intent.setType("text/csv");
                        intent.putExtra(Intent.EXTRA_STREAM, fileUri);
                        startActivity(Intent.createChooser(intent, null));
                    towrite.delete();
                    } catch (IOException e){
                        e.printStackTrace();
                    }

                }
            }.start();
        }
    });
        Button buttonZbrisiPodatke = findViewById(R.id.btnZbrisiPodatke);
        buttonZbrisiPodatke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "database-name").allowMainThreadQueries().build();

                DAO_class Dao = db.Dao();

                // Create an Intent to launch the new activity
                Dao.deleteAll();
                Toast.makeText(MainActivity1.this, "Baza podatkov zbrisana!", Toast.LENGTH_SHORT).show();
            }
        });

        Button buttonAbout = findViewById(R.id.About);
        buttonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to launch the new activity
                Intent intent = new Intent(MainActivity1.this, MainActivity4.class);
                startActivity(intent);
            }
        });

}}