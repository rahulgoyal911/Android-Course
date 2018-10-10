package com.example.rahul.a09listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView myListView = (ListView)findViewById(R.id.lv);

        final ArrayList<String> myList = new ArrayList<String>();

        myList.add("A");
        myList.add("B");
        myList.add("C");
        myList.add("D");
        myList.add("A");
        myList.add("B");
        myList.add("C");
        myList.add("D");
        myList.add("A");
        myList.add("B");
        myList.add("C");
        myList.add("D");
        myList.add("A");
        myList.add("B");
        myList.add("C");
        myList.add("D");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,myList);

        myListView.setAdapter(arrayAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),myList.get(i),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
