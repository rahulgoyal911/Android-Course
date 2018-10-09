package com.example.rahul.a08tictactie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int activePlayer =0;
    boolean gameActive =true;
    String winner;
    int []gameState = {2,2,2,2,2,2,2,2,2};
    int [][]winningPos = {{0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6},{0,3,6},{1,4,7},{2,5,8}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void btnClick(View view){
        recreate();
    }
    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());


        if(gameState[tappedCounter]==2 && gameActive){
            counter.setTranslationY(-1500);


            gameState[tappedCounter] = activePlayer;
            if(activePlayer == 0){
                counter.setImageResource(R.drawable.green);
                activePlayer = 1;
            }
            else{
                counter.setImageResource(R.drawable.blue);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).setDuration(200);
            for(int[] winningPos : winningPos){
                if(gameState[winningPos[0]] == gameState[winningPos[1]] && gameState[winningPos[2]] == gameState[winningPos[1]] && gameState[winningPos[0]]!=2){

                    gameActive = false;
                    if(activePlayer == 1){
                        winner = "Green";

                    }
                    else{
                        winner = "Blue";
                    }
                    Toast.makeText(this,winner + " has won" ,Toast.LENGTH_SHORT).show();
                    Button b1 = (Button)findViewById(R.id.button);
                    b1.setVisibility(View.VISIBLE);
                }
            }
        }
    }
}
