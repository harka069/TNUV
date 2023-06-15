package com.example.sparovcek2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    ListView l;

    // Custom data structure to hold multiple variables
    class ListItem {
        String title;
        String type;
        String date;
        String category;
        String sum;
        String descritption;

        public ListItem(String title, String type, String date, String category, String sum, String descritption) {
            this.title = "Stevilka vnosa: " +title;
            this.type = "Tip: "  +type;
            this.date = "Datum: "+date;
            this.category = "Kategorija: "+ category;
            this.sum = "Cena:" + sum +" €";
            this.descritption ="Opis: "+ descritption;

        }
    }
    volatile ListItem seznam[];

    ListItem items[] = {
            new ListItem("1","Odhodek","12/6/2023","Avto","50","bencin"),
            new ListItem("1","Odhodek","12/6/2023","Avto","50","bencin"),
            new ListItem("1","Odhodek","12/6/2023","Avto","50","bencin"),
            new ListItem("1","Odhodek","12/6/2023","Avto","50","bencin"),
            new ListItem("1","Odhodek","12/6/2023","Avto","50","bencin"),
            new ListItem("1","Odhodek","12/6/2023","Avto","50","bencin"),
            new ListItem("1","Odhodek","12/6/2023","Avto","50","bencin"),
            new ListItem("1","Odhodek","12/6/2023","Avto","50","bencin"),
            new ListItem("1","Odhodek","12/6/2023","Avto","50","bencin"),
            new ListItem("1","Odhodek","12/6/2023","Avto","50","bencin"),
            new ListItem("1","Odhodek","12/6/2023","Avto","50","bencin"),
            new ListItem("1","Odhodek","12/6/2023","Avto","50","bencin"),
            new ListItem("1","Odhodek","12/6/2023","Avto","50","bencin"),
            new ListItem("1","Odhodek","12/6/2023","Avto","50","bencin"),
            new ListItem("1","Odhodek","12/6/2023","Avto","50","bencin"),


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

            AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, "database-name").allowMainThreadQueries().build();

            DAO_class Dao = db.Dao();

                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
                Date firstday = cal.getTime();
                long time = firstday.toInstant().toEpochMilli()/1000L;
                Log.d("print1", String.valueOf(time));



                List<Transaction> odg = Dao.getMonthData(time);
                ListItem seznam[] = new ListItem[odg.size()];
                int i = 0;
                for(Transaction trans : odg){

                    long timestamp = trans.getDate();
                    Date date = new Date(timestamp*1000);
                    DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    seznam[i] = new ListItem(String.valueOf(trans.getId()), trans.getType(), format.format(date),trans.getCategory(), Float.toString(trans.getSum()), trans.getDescription());
                    Log.d("print2", trans.getDescription());
                    i++;
                }






        l = findViewById(R.id.list);

        ArrayAdapter<ListItem> arr = new ArrayAdapter<ListItem>(
                this,
                R.layout.list_item,
                R.id.title,
                seznam) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView title = view.findViewById(R.id.title);
                TextView type = view.findViewById(R.id.type);
                TextView date = view.findViewById(R.id.date);
                TextView category = view.findViewById(R.id.category);
                TextView sum = view.findViewById(R.id.sum);
                TextView description = view.findViewById(R.id.description);



                title.setText(getItem(position).title);
                type.setText(getItem(position).type);
                date.setText(getItem(position).date);
                category.setText(getItem(position).category);
                sum.setText(getItem(position).sum);
                description.setText(getItem(position).descritption);

                return view;
            }
        };

        l.setAdapter(arr);

        /*FloatingActionButton fab = findViewById(R.id.floatingbutton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });*/
        Button ButtonNazaj= findViewById(R.id.btnNazaj);
        ButtonNazaj.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Create an Intent to launch the new activity
                Intent intent = new Intent(MainActivity2.this, MainActivity1.class);
                startActivity(intent);
            }
        });
    }
}
