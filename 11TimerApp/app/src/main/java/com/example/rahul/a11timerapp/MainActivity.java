package com.example.rahul.a11timerapp;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView timerTextView;
    SeekBar timerSeekBar;
    CountDownTimer countDownTimer;
    Button btn;
    Boolean a = false;
    public void btnClick(View view) {

        if (a){
            timerTextView.setText("0:30");
            timerSeekBar.setProgress(30);
            timerSeekBar.setEnabled(true);
            countDownTimer.cancel();
            btn.setText("Start!");
            a = false;
        }
        else {
            a=true;
            timerSeekBar.setEnabled(false);
            btn.setText("STOP");
            countDownTimer = new CountDownTimer(timerSeekBar.getProgress()*1000 + 100,1000) {
                @Override
                public void onTick(long l) {
                    updateTimer((int)l/1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.horn);
                    mediaPlayer.start();
                }
            }.start();
        }
    }
    public void updateTimer(int secondsLeft){
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;
        String ss = Integer.toString(seconds);
        if (seconds <= 9) {
            ss = "0" + ss;
        }

        timerTextView.setText(Integer.toString(minutes) + ":" + ss);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         timerSeekBar = (SeekBar) findViewById(R.id.seekBar);
         timerTextView = (TextView) findViewById(R.id.textView);
         btn = (Button)findViewById(R.id.button);

        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


}
