package com.example.mocktest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start = findViewById(R.id.button2);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText durationInput = findViewById(R.id.duration);

                Intent intent = new Intent(MainActivity.this, TimeActivity.class);
                intent.putExtra("duration", durationInput.getText().toString());
                startActivity(intent);
            }
        });
    }
}