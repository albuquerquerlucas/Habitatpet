package com.mobile.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.mobile.R;

public class SplashActivity extends Activity {

    private static final String ERR = "Erro";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread timerThread = new Thread(){
            @Override
            public void run(){
                try {
                    sleep(4000);
                }catch (InterruptedException e){
                    Log.e(ERR, "Erro na execução da Thread.");
                    e.printStackTrace();
                }finally {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause(){
        super.onPause();
        finish();
    }
}
