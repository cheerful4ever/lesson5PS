package com.example.lesson5ps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etSongTitle, etSingerName, etYear;
    Button btnInsert, btnShowList;
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSongTitle = findViewById(R.id.etSongTitle);
        etSingerName = findViewById(R.id.etSingerName);
        etYear = findViewById(R.id.etYear);
        btnInsert = findViewById(R.id.btnInsert);
        btnShowList = findViewById(R.id.btnShowList);
        rg = (RadioGroup) findViewById(R.id.radioGroupStars);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String note = etSongTitle.getText().toString().trim();
                if (note.length() == 0)
                    return;

                DBHelper dbh = new DBHelper(MainActivity.this);
                if (dbh.isExistingSong(etSongTitle.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Same note already exists", Toast.LENGTH_LONG).show();
                } else {
                    String title = etSongTitle.getText().toString();
                    String singer = etSingerName.getText().toString();
                    int year = Integer.parseInt(etYear.getText().toString());
                    int stars = getStars();
                    dbh.insertSong(title, singer, year, stars);
                    Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private int getStars() {
        int stars = 1;
        switch (rg.getCheckedRadioButtonId()) {
            case R.id.rb1:
                stars = 1;
                break;
            case R.id.rb2:
                stars = 2;
                break;
            case R.id.rb3:
                stars = 3;
                break;
            case R.id.rb4:
                stars = 4;
                break;
            case R.id.rb5:
                stars = 5;
                break;
        }
        return stars;
    }
}
