package com.example.toyin.tinker.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.toyin.tinker.R;

/**
 * Created by Toyin on 06/01/2017.
 */
public class AboutPage extends AppCompatActivity {
    public ImageView back_arrow;
    public TextView back_text;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        back_arrow = (ImageView) findViewById(R.id.back_arrow);
        back_text = (TextView) findViewById(R.id.back_text);

        //On clicking the back_arrow button and the back text.
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutPage.this, MainActivity.class);
                startActivity(intent);
            }
        });

        back_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutPage.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
