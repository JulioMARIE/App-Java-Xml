package com.jmj.mariejulio.presence_et_cotisation;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AutoCompleteTextView;

import com.j256.ormlite.dao.Dao;

import Model.Member;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getWindow().setBackgroundDrawableResource(R.drawable.pre_cos_bg);
    }

    public void valider(View v){
        AutoCompleteTextView username = (AutoCompleteTextView) findViewById(R.id.username);
        String user = username.getText().toString();

        Intent intent = new Intent(this,Login.class);
        intent.putExtra("USERNAME", user);

        startActivity(intent);
        finish();
    }
}
