package com.example.projecte1_conversor_v2fitxer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class MainActivity extends AppCompatActivity {
    String[] nameMoney;
    String jsonDownland;
    OkHttpClient client = new OkHttpClient();

    TextView _text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _text = findViewById(R.id.textView2);


        Request request = new Request.Builder().url("http://api.exchangeratesapi.io/v1/latest?access_key=18ef78164de8af79aee66eb4f78356c3").build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure( Call call,IOException e) {
                e.printStackTrace();
                Log.e("msg","error no funciona");
            }

            @Override
            public void onResponse( Call call,Response response) throws IOException {
                if(response.isSuccessful())
                {
                    jsonDownland=response.body().string();
                    System.out.println(jsonDownland);
                    JSONObject jsonObject= null;
                    nameMoney = null;
                    try {
                        jsonObject = new JSONObject(jsonDownland);
                        JSONArray tipo_moneda =jsonObject.getJSONArray("rates");
                        for(int i=0; i<tipo_moneda.length();i++) {
                            JSONObject moneda = tipo_moneda.getJSONObject(i);
                            String name_moneda = moneda.getString(tipo_moneda.getJSONObject((i)).toString());
                            nameMoney[i] = name_moneda;
                            ShowStrings();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("msg","error no funciona");
                    }
                }
            }
        });


    }

    public void ShowStrings(){
        String show="";
        for(int i=0; i<nameMoney.length;i++){
           show+=nameMoney[i].toString()+"\n";
        }
        _text.setText(show);
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
}