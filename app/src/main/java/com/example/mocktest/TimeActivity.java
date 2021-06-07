package com.example.mocktest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TimeActivity extends AppCompatActivity {

    TextView player2score, player1score;
    Button next;
    ArrayList<String> data;
    boolean isP2;
    int player1, player2;
    CountDownTimer cdt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        Intent intent = getIntent();

        int duration = Integer.parseInt(intent.getStringExtra("duration"));
        player1score = findViewById(R.id.player1score);
        player2score = findViewById(R.id.player2score);
        player1 = duration * 1000;
        player2 = duration * 1000;

        player1score.setText(""+duration);
        player2score.setText(""+duration);



        data = new ArrayList<>();
        ListView list = findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        list.setAdapter(adapter);

        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cdt.cancel();
                isP2 = !isP2;
                if(isP2)
                {
                    data.add("Player 1 left " + player1/1000 + "secs");
                    startTimer(player2);

                }
                else
                {
                    data.add("Player 2 left " + player2/1000 + "secs");
                    startTimer(player1);
                }
                adapter.notifyDataSetChanged();
            }
        });
        startTimer(player1);
    }
    /*Player 1 will start on entry*/
    private void startTimer(int dur)
    {
        cdt = new CountDownTimer(dur, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(isP2)
                {
                    player2score.setText("" + millisUntilFinished/1000);
                    player2 = (int) millisUntilFinished; // settings player 2 current time
                }
                else
                {
                    player1score.setText("" + millisUntilFinished/1000);
                    player1 = (int) millisUntilFinished;
                }
            }

            @Override
            public void onFinish() {
                if(isP2)
                {
                    Toast.makeText(TimeActivity.this, "Player 2's time has ran out", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(TimeActivity.this, "Player 1's time has ran out", Toast.LENGTH_SHORT).show();
                }
            }
        };
        cdt.start();
    }
}