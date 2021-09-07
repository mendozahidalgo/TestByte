package com.example.testbyte;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.testbyte.models.Statement;
import com.example.testbyte.utils.VolleyS;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StatementActivity extends AppCompatActivity {

    private ImageButton btnSalir;
    private MaterialButton btnSalir2;
    private TextView loginuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statement);

        btnSalir = findViewById(R.id.btnSalir);
        btnSalir2 = findViewById(R.id.btnSalir2);
        loginuser = findViewById(R.id.loginuser);

        Intent intentLg = getIntent();
        String username = intentLg.getStringExtra("user");
        if (username == null){
            username = "anonimo";
        }
        username = "Bienvenido, "+username;
        loginuser.setText(username);


        View.OnClickListener listenerSalir = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goLogin();
            }
        };

        btnSalir.setOnClickListener(listenerSalir);
        btnSalir2.setOnClickListener(listenerSalir);

    }

    private void goLogin(){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

}