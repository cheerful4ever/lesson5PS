package com.example.lesson5ps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class thirdActivity extends AppCompatActivity {
    EditText etId, etSongTitle, etSingerName, etYear;
    RadioGroup rg;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    Button btnUpdate, btnCancel, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        rg = (RadioGroup) findViewById(R.id.radioGroupStars);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        Intent i = getIntent();
        songs = (Song).i.getSerializableExtra("song");
        etId.setText(String.valueOf(song.getId()));
        etSongTitle.setText(song.getTitle());
        etSingerName.setText(song.getYear());

        int stars = songs.getStars();
        if (stars == 1) {
            rb1.setChecked(true);
        }
        if(stars == 2) {
            rb2.setChecked(true);
        }
        if(stars == 3) {
            rb3.setChecked(true);
        }
        if(stars == 4) {
            rb4.setChecked(true);
        }
        if (stars == 5) {
            rb5.setChecked(true);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                song.setTitle(etSongTitle.getText().toString()),
                song.setSingers(etSingerName.getText().toString()),
                song.setYear(etYear.getText().toString()),
                song.setStars(getStars()),

                DBHelper dbh = new DBHelper(UpdateActivity.this);
                dbh.updateNote(song);
                setResult(RESULT_OK);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}
