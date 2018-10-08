package com.example.rahul.a02currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void converter(View v)
    {
        EditText et1 = (EditText)findViewById(R.id.editText);
        EditText et2 = (EditText)findViewById(R.id.editText2);
        TextView tv1 = (TextView)findViewById(R.id.textView2);
        double doll = Double.parseDouble(et1.getText().toString());
        double doll1 = Double.parseDouble(et2.getText().toString());
        double fin = doll* doll1;
       // int i = 5;
        tv1.setText(Double.toString(fin));
    }
}
