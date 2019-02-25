package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);

        queue = Volley.newRequestQueue(this);
        getNews();
    }

    public void getNews() {
        // Instantiate the RequestQueue.
        // Request를 요청하면 Queue에 하나씩 담아서 받아온다.

        String url = "https://newsapi.org/v2/top-headlines?country=kr&apiKey=2030c847ca1443c89149f21c325e263a";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // dustData 타입의 List를 생성해서 데이터를 넣어준다.
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("articles");

                            List<dustData> news = new ArrayList<>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject obj = jsonArray.getJSONObject(i);

                                Log.d("News", obj.toString());
//
//                                NewsData newsData = new NewsData();
//                                newsData.setTitle(obj.getString("title"));
//                                newsData.setUrlToImage(obj.getString("urlToImage"));
//                                newsData.setContent(obj.getString("description"));
//                                news.add(newsData);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
