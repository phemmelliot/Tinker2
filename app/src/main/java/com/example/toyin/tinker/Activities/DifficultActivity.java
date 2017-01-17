package com.example.toyin.tinker.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;


import com.example.toyin.tinker.R;

/**
 * Created by Toyin on 29/12/2016.
 */
public class DifficultActivity extends AppCompatActivity {

    public EditText name;
    public RadioButton easy, medium, hard;
    public Button start;
    public String difficult;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.name_page);
        start = (Button) findViewById(R.id.start_button);
        name = (EditText) findViewById(R.id.player_name);
        easy = (RadioButton) findViewById(R.id.ease);
        medium = (RadioButton) findViewById(R.id.medium);
        hard = (RadioButton) findViewById(R.id.hard);

        //Send the player name to the map in hofSingleton.
//        Bundle bundle = new Bundle();
//        bundle.putString("name", name.getText().toString());
//        hofFragment hofF = new hofFragment();
//        hofF.setArguments(bundle);

        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                difficult = "easy";
            }
        });

        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                difficult = "medium";
            }
        });

        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                difficult = "hard";
            }
        });
        //Check for click on the start button.
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DifficultActivity.this, QuestionActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("player_name", name.getText().toString());
                intent.putExtra("difficult", difficult);
                startActivity(intent);

            }
        });
    }
}
