package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor edit = pref.edit();
        Button btnServer = findViewById(R.id.chooseServer);
        Button btnCliente = findViewById(R.id.chooseCliente);
        btnServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                edit.putBoolean("KEY_SERVER", true).apply();
                Intent inte = new Intent(HomeActivity.this,MainActivity.class);
                startActivity(inte);

            }
        });
        btnCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent inte = new Intent(HomeActivity.this,MainActivity.class);
                startActivity(inte);

            }
        });
    }

}
