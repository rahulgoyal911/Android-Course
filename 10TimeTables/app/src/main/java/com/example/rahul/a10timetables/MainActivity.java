package com.example.rahul.a10timetables;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
int a,i;
String b;
ListView mylistView;
    SeekBar seekBar;

ArrayList<String> myArrayList;
    public void gen(int x){
        myArrayList= new ArrayList<String>();
        for(i=1;i<50;i++){
            myArrayList.add(String.valueOf(i*x));
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this,R.layout.support_simple_spinner_dropdown_item,myArrayList);
        mylistView.setAdapter(arrayAdapter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        seekBar = (SeekBar)findViewById(R.id.sb);
        mylistView = (ListView)findViewById(R.id.lv);
        seekBar.setProgress(5);
        seekBar.setMax(20);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                a = (seekBar.getProgress());
                gen(a);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
               // recreate();
                a = (seekBar.getProgress());
            }
        });



    }
}
