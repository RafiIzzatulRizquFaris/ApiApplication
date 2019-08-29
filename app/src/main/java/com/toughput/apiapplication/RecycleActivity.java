package com.toughput.apiapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class RecycleActivity extends AppCompatActivity {

    BaseAdapter baseAdapter;
    ArrayList<Puguh> puguhs;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        progressBar = findViewById(R.id.pg_rv);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView = findViewById(R.id.rv_puguh);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplication()));

        baseAdapter = new BaseAdapter();
        recyclerView.setAdapter(baseAdapter);

        puguhs = new ArrayList<>();

        getData();
        baseAdapter.setPuguhArrayList(puguhs);



    }

    public void getData (){
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get("http://puguh.zeblogic.com/api.php?method=get-data", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                try {
                    JSONArray jsonArray = new JSONArray(result);
                    for (int i = 0; i < jsonArray.length(); i++){
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String usernaming = jsonObject1.getString("username");
                        String emailing = jsonObject1.getString("email");
                        String hashing = jsonObject1.getString("password");
                        Puguh puguh = new Puguh();
                        puguh.setUsernme(usernaming);
                        puguh.setEmail(emailing);
                        puguh.setPassword(hashing);
                        puguhs.add(puguh);
                    }
                    progressBar.setVisibility(View.INVISIBLE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(RecycleActivity.this, "Error :"+error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
