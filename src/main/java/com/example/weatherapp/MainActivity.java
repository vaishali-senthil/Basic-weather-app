package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    class Weather extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... address) {
            try{
                URL url = new URL(address[0]);
                HttpURLConnection connection =(HttpURLConnection) url.openConnection();

                connection.connect();
                InputStream is =connection.getInputStream();
                InputStreamReader isr =new InputStreamReader(is);


                int data = isr.read();
                String content= "";
                char ch;
                while (data !=-1){
                    ch= (char)data;
                    content= content+ch;
                    data = isr.read();
                }
                return content;




            } catch (IOException e){
                e.printStackTrace();
            }


            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String content;
        Weather weather = new Weather();
        try {
            content = weather.execute("http://api.openweathermap.org/data/2.5/weather?q=hosur&appid=b9a2ff8ccc7254763121a1019d2421e0").get();
            Log.i("content",content);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}