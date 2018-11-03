package com.example.rahul.a12braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView timerView;
    TextView scoreView;
    TextView sumView;
    TextView resultView;
    Button startBtn;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Random rand;
    CountDownTimer countDownTimer;
    Button fin;
    ArrayList<Integer> answers= new ArrayList<Integer>();
    int locationOfCorrect;
    int score = 0;
    int ques = 0;

    public void chooseAns(View view){
        if(Integer.toString(locationOfCorrect).equals(view.getTag().toString())){
            resultView.setText("Correct");
            score++;
        }
        else{
            resultView.setText("InCorrect");
        }
        ques++;
        scoreView.setText(Integer.toString(score) + "/" + Integer.toString(ques));
        answers.clear();
        newQuestion();
    }
    public void newQuestion(){
        rand = new Random();
        int a = rand.nextInt(10);
        int b = rand.nextInt(10);
        sumView.setText(Integer.toString(a) + "+" + Integer.toString(b) + "=?");

        locationOfCorrect = rand.nextInt(4);

        for(int i=0;i<4;i++){
            if(i==locationOfCorrect){
                answers.add(a+b);
            }
            else{
                int wrongAns = rand.nextInt(21);
                if(wrongAns==a+b){
                    wrongAns = rand.nextInt(21);
                }
                answers.add(wrongAns);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timerView = (TextView)findViewById(R.id.timerView);
        scoreView = (TextView)findViewById(R.id.scoreView);
        sumView = (TextView)findViewById(R.id.sumView);
        startBtn = (Button)findViewById(R.id.goButton);
        resultView = (TextView)findViewById(R.id.resultView);
        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        fin = (Button)findViewById(R.id.button5);

        startBtn.setVisibility(View.VISIBLE);
        timerView.setVisibility(View.GONE);
        sumView.setVisibility(View.GONE);
        scoreView.setVisibility(View.GONE);
        resultView.setVisibility(View.GONE);
        button0.setVisibility(View.GONE);
        button1.setVisibility(View.GONE);
        button2.setVisibility(View.GONE);
        button3.setVisibility(View.GONE);


       newQuestion();
       countDownTimer = new CountDownTimer(10500,1000){

           @Override
           public void onTick(long l) {
               timerView.setText(String.valueOf(l/1000) + "s");
           }

           @Override
           public void onFinish() {
                resultView.setText("Timeout");
                button0.setClickable(false);
               button1.setClickable(false);
               button2.setClickable(false);
               button3.setClickable(false);
                fin.setVisibility(View.VISIBLE);
           }
       };
    }
    public void onStartq(View view){
        countDownTimer.start();
        startBtn.setVisibility(View.GONE);
        timerView.setVisibility(View.VISIBLE);
        sumView.setVisibility(View.VISIBLE);
        scoreView.setVisibility(View.VISIBLE);
        resultView.setVisibility(View.VISIBLE);
        button0.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
    }
    public void finClick(View view){
        recreate();
    }
}
