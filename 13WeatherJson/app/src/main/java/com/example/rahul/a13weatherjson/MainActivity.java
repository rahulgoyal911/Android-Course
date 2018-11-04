package com.example.rahul.a13weatherjson;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText city;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.button);
        city = (EditText)findViewById(R.id.editText);
        result = (TextView)findViewById(R.id.textView2);
    }
    public void btnClick(View view){
        view.animate().rotation(2160).setDuration(2000);
        try{
        DownloadTask task = new DownloadTask();
        String encodedCityName = URLEncoder.encode(city.getText().toString(),"UTF-8");
        task.execute("https://openweathermap.org/data/2.5/weather?q=" + encodedCityName + "&appid=b6907d289e10d714a6e88b30761fae22");
        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Not available",Toast.LENGTH_SHORT).show();
        }
    }

    public class DownloadTask extends AsyncTask<String, Void, String>{


        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;
            try{
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();
                while(data!=-1){
                    char current = (char)data;
                    result += current;
                    data = reader.read();
                }
                return result;
            }
            catch (Exception e){
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),"Not available",Toast.LENGTH_SHORT).show();

            }
            return null;

        }@Override
        protected void onPostExecute(String s){
        super.onPostExecute(s);
        try{
            JSONObject jsonObject=new JSONObject(s);
            String weatherInfo = jsonObject.getString("weather");
            JSONArray arr =new JSONArray(weatherInfo);
            String message = "";

            for(int i=0;i<arr.length();i++){
                JSONObject jsonPart = arr.getJSONObject(i);
                String main = jsonPart.getString("main");
                String description =jsonPart.getString("description");

                if(!main.equals("") && !description.equals("")){
                    message += main+ ":" +description + "\r\n";
                }

            }
            if(!message.equals("")){
                result.setText(message);

            }
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Not available",Toast.LENGTH_SHORT).show();
        }
        }
    }

}
