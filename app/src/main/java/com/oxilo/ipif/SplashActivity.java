package com.oxilo.ipif;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.oxilo.ipif.modal.login.Login;

public class SplashActivity extends AppCompatActivity {

    Login login = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

         login = ApplicationController.getInstance().getAppPrefs().getObject("LOGIN",Login.class);
        if (login == null){
//            ApplicationController.getInstance().getAppPrefs().putObject("LOGIN",login);
//            ApplicationController.getInstance().getAppPrefs().commit();
        }else{
            //Bypasss it
        }



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        },3000);
    }
}
