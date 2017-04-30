package com.example.toyin.tinker.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.toyin.tinker.R;

/**
 * Created by Toyin on 03/03/2017.
 */
public class Splash_main extends AppCompatActivity {

    public boolean _active = true;
    public int _splashTime = 3000;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        //Start the splash as a thread
        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (_active && (waited < _splashTime)) {
                        sleep(100);
                        if (_active) {
                            waited += 100;
                        }
                    }
                } catch (Exception e) {

                } finally {

                    startActivity(new Intent(Splash_main.this,
                            MainActivity.class));
                    finish();
                }
            }
        };
        splashTread.start();
    }


}
