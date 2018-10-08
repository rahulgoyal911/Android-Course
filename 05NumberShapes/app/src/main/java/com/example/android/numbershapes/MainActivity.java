package com.example.android.numbershapes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void makeToast(String string) {
        Toast.makeText(MainActivity.this, string, Toast.LENGTH_SHORT).show();
    }

    public void check(View view) {

        EditText text = (EditText)findViewById(R.id.edittext);
        if(text.getText().toString().isEmpty())
            makeToast("Enter the number");
        else {
            class Number {
                int number;
                public boolean issquare(){
                    int n = (int) Math.sqrt(number);
                    if(number == n*n)
                        return true;
                    else
                        return false;
                }
                public boolean trinum(){
                    int sum=0;
                    for(int i=1;i<number;i++) {
                        sum+=i;
                        if(sum==number)
                            return true;
                    }
                    return false;
                }
            }

            Number num = new Number();
            num.number = Integer.parseInt(text.getText().toString());
            if(num.issquare()){
                if(num.trinum())
                    makeToast("Triangular and Square");
                else if(num.trinum() == false)
                    makeToast("Square but not Triangular");
            }
            if(num.issquare() == false){
                if(num.trinum())
                    makeToast("Triangular but not Square");
                else if(num.trinum()==false)
                    makeToast("Neither Triangular nor Square");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
