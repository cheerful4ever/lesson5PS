package com.example.lesson5ps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    ListView lv;
    SongArrayAdapter adapter;
    Button btnShowStars;
    ArrayList<Song> songs;
    ArrayAdapter aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        lv = (ListView) this.findViewById(R.id.lv);
        btnShowStars = (Button) this.findViewById(R.id.btnShow5Stars);

        DBHelper dbh = new DBHelper(this);
        songs = dbh.getAllSongs();

        adapter = new SongArrayAdapter(this, R.layout.row, songs);
        lv.setAdapter(adapter);

        btnShowStars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(SecondActivity.this);
                songs = dbh.getAllSongsByStars(5);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == this.requestCode && resultCode == RESULT_OK) {
            DBHelper dbHelper = new DBHelper(MainActivity.this);
            songs.clear();
            songs.addAll(dbHelper.getAllSongs());
            aa.notifyDataSetChanged();
            dbHelper.close();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
