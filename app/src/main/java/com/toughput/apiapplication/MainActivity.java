package com.toughput.apiapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvUsername, tvPassword, tvIdUser, tvEmail, tvResult;
    Button btnGet, btnPost, btnRv;
    ProgressBar pg;
    EditText edtUsername, edtEmail, edtPassword, edtSearch;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvIdUser = findViewById(R.id.iduser);
        tvUsername = findViewById(R.id.username);
        tvPassword = findViewById(R.id.password);
        tvEmail = findViewById(R.id.email);
        tvResult = findViewById(R.id.result_fix);
        btnGet = findViewById(R.id.btn_get);
        btnGet.setOnClickListener(this);
        btnPost = findViewById(R.id.btn_post);
        btnPost.setOnClickListener(this);
        btnRv = findViewById(R.id.btn_rv);
        btnRv.setOnClickListener(this);
        pg = findViewById(R.id.progress);

        edtUsername = findViewById(R.id.edt_username);
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        edtSearch = findViewById(R.id.edt_searchuser);

        pg.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btn_get:
                pg.setVisibility(View.VISIBLE);
                int intuser = 0;
                String iduser = edtSearch.getText().toString();
                if (!iduser.isEmpty()){
                    intuser = Integer.valueOf(iduser);
                }
                getData(intuser);
                break;
            case R.id.btn_post:
                pg.setVisibility(View.VISIBLE);
                postData();
                break;
            case R.id.btn_rv:
                Intent intent = new Intent(MainActivity.this, RecycleActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void postData(){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("username", edtUsername.getText().toString());
        params.add("email", edtPassword.getText().toString());
        params.add("password", edtPassword.getText().toString());

        client.post("http://puguh.zeblogic.com/api.php?method=create-data", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String sUsername = jsonObject.getJSONObject("data").getString("username");
                    String sEmail = jsonObject.getJSONObject("data").getString("email");
                    String sPassword = jsonObject.getJSONObject("data").getString("password");

                    String resultfix = sUsername + sEmail + sPassword;
                    tvResult.setText(resultfix);

                    pg.setVisibility(View.INVISIBLE);

                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("Keslahan :", "post");
            }
        });
    }

    public void getData(final int id){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://puguh.zeblogic.com/api.php?method=get-data", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                try {
                    JSONArray jsonArray = new JSONArray(result);
                    JSONObject jsonObject = jsonArray.getJSONObject(id - 1);
                    String string_id = jsonObject.getString("id");
                    String string_email = jsonObject.getString("email");
                    String string_username = jsonObject.getString("username");
                    String string_password = jsonObject.getString("password");

                    tvIdUser.setText(string_id);
                    tvEmail.setText(string_email);
                    tvPassword.setText(string_password);
                    tvUsername.setText(string_username);

                    pg.setVisibility(View.INVISIBLE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("Kelalahan", "get Data");
                Toast.makeText(MainActivity.this, "Error "+error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
