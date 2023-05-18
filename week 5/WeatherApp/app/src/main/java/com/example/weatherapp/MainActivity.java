package com.example.weatherapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    public String URL_CONTENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setURLtext();
        findViewById(R.id.weatherIconImageView).setOnClickListener(setURLtext());
    }

    private View.OnClickListener setURLtext(){
        String url = "https://api.openweathermap.org/data/2.5/weather?q=New%20York&appid=01ecb769ce4c6d322d80e2e3c9c2154c&units=imperial";
        RequestQueue requestQueue;

// Instantiate the cache
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

// Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

// Instantiate the RequestQueue with the cache and network.
        requestQueue = new RequestQueue(cache, network);

// Start the queue
        requestQueue.start();


// Formulate the request and handle the response.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        URL_CONTENT = response;
                        setText();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                    }
                });

// Add the request to the RequestQueue.
        requestQueue.add(stringRequest);
        return null;
    }


    private void setText() {
        TextView city = findViewById(R.id.cityTextView);
        TextView country = findViewById(R.id.countryTextView);
        TextView temp = findViewById(R.id.temperatureTextView);
        TextView condition = findViewById(R.id.weatherConditionTextView);
        TextView description = findViewById(R.id.weatherDescriptionTextView);

        try {

            ImageView iv = findViewById(R.id.weatherIconImageView);

            JSONObject obj = new JSONObject(URL_CONTENT);
            String icon = obj.getJSONArray("weather").getJSONObject(0).get("icon").toString();
            String img = "https://openweathermap.org/img/wn/"+icon+"@4x.png";
            city.setText(obj.get("name").toString());
            country.setText(obj.getJSONObject("sys").get("country").toString());
            temp.setText(obj.getJSONObject("main").get("temp").toString());
            Glide.with(this).load(img).into(iv);
            condition.setText(obj.getJSONArray("weather").getJSONObject(0).get("main").toString());
            description.setText(obj.getJSONArray("weather").getJSONObject(0).get("description").toString());
        } catch (Exception e){
            city.setText(R.string.fail);
            country.setText(R.string.fail);
            temp.setText(R.string.fail);
            condition.setText(R.string.fail);
            description.setText(R.string.fail);
        }
    }
}