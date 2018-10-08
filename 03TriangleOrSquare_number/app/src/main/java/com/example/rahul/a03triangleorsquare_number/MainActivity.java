package com.example.rahul.a03triangleorsquare_number;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void toCheck(View v)
    {
        EditText tv1= (EditText)findViewById(R.id.editText);
        int a = Integer.parseInt(tv1.getText().toString());
        boolean tri,squ;
        tri = isTri(a);
        squ = isSqu(a);
        if(tri&&squ){
            Toast.makeText(this, "Both triangular and square", Toast.LENGTH_SHORT).show();
        }
        else if(tri){
            Toast.makeText(this, "Only triangular", Toast.LENGTH_SHORT).show();
        }
        else if(squ){
            Toast.makeText(this, "Only square", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "None", Toast.LENGTH_SHORT).show();
        }


    }

    public boolean isTri(int a){
        if(a<0)
            return false;
        int sum=0;
        for(int n=1; sum <= a; n++){
            sum += n;
            if(sum==a)
                return true;

        }
        return false;
    }
    public boolean isSqu(int a){
        int count=0;
        for(int i=0;i<a;i++){
            if(i*i==a)
                count++;
        }
        if(count>0)
            return true;
        else
            return false;
    }
}