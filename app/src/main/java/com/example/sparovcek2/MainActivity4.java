package com.example.sparovcek2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        Button buttonNazaj = findViewById(R.id.buttonNazaj);
        buttonNazaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to launch the new activity
                Intent intent = new Intent(MainActivity4.this, MainActivity1.class);
                startActivity(intent);
            }
        });
    }
}