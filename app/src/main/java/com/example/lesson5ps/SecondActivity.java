package com.example.lesson5ps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    ListView lv;
    SongArrayAdapter adapter;
    Button btnShowStars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        lv = (ListView) this.findViewById(R.id.lv);
        btnShowStars = (Button) this.findViewById(R.id.btnShow5Stars);

        DBHelper dbh = new DBHelper(this);
        ArrayList<Song> songs = dbh.getAllSongs();

        adapter = new SongArrayAdapter(this, R.layout.row, songs);
        lv.setAdapter(adapter);
        
    }
}
