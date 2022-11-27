package com.jmj.mariejulio.presence_et_cotisation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;

public class Login extends AppCompatActivity {
    AutoCompleteTextView loginusername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Bundle extras = getIntent().getExtras();
        loginusername = (AutoCompleteTextView) findViewById(R.id.loginusername);

        if (extras!=null){
            String usernamelogin = extras.getString("USERNAME");
            loginusername.setText(usernamelogin);
        }



//        Intent intent = getIntent();
//        String usernamelogin = intent.getStringExtra("USERNAME");
//
//        loginusername = (AutoCompleteTextView) findViewById(R.id.loginusername);
//        loginusername.setText(usernamelogin);
    }

    public void showDashboardApp(View view) {
        Intent intent = new Intent(this, SlideActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);

        super.onBackPressed();
    }
}
