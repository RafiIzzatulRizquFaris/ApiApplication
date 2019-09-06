package com.toughput.apiapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class LocalActivity extends AppCompatActivity {

    LocalAdapter localAdapter;
    ArrayList<Local> locals;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
        recyclerView = findViewById(R.id.rv_local);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplication()));

        progressBar = findViewById(R.id.pg_local);
        progressBar.setVisibility(View.VISIBLE);

        localAdapter = new LocalAdapter();
        recyclerView.setAdapter(localAdapter);

        locals = new ArrayList<>();

        getLocal();
        localAdapter.setLocalArrayList(locals);
    }

    private void getLocal() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get("http://10.10.234.49/tried/index.php", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                try {
                    JSONArray jsonArray = new JSONArray(result);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String namalengkap = jsonObject.getString("nama_lengkap");
                        String tempatlahir = jsonObject.getString("tempat_lahir");
                        String email = jsonObject.getString("email");

                        Local local = new Local();
                        local.setNamalengkap(namalengkap);
                        local.setEmail(email);
                        local.setTempatlahir(tempatlahir);
                        locals.add(local);
                    }
                    progressBar.setVisibility(View.INVISIBLE);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(LocalActivity.this, "Error :" + error, Toast.LENGTH_LONG).show();
            }
        });
    }
}
