package com.example.toyin.tinker.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.toyin.tinker.R;

import static com.example.toyin.tinker.R.id.start;

/**
 * Created by Toyin on 29/12/2016.
 */
public class DifficultActivity extends AppCompatActivity {


      public String choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.name_page);
        final Button start = (Button) findViewById(R.id.start_button);
       final EditText name1 = (EditText) findViewById(R.id.player_name);
        final Spinner spinner1 = (Spinner) findViewById(R.id.select);



        //Set toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Get the name of the player that just finished playing
        String default_name = getIntent().getStringExtra("player_name");
        name1.setText(default_name);
        final String spinner = String.valueOf(spinner1.getSelectedItem());


            //Get the name and send it to the QuestionActivity.
            final String name = name1.getText().toString();
            //Start the QuestionActivity Activity.

            start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Check for the selected difficulty level and the stuff
                    String difficult = null;
                    choice = spinner;
                    if (choice == null || name1.getText().toString().isEmpty()) {
                        Toast.makeText(DifficultActivity.this, "You have to fill in the right details", Toast.LENGTH_SHORT).show();
                    } else if (choice == null && name1.getText().toString().isEmpty()) {
                        Toast.makeText(DifficultActivity.this, "You have to pick a difficulty level", Toast.LENGTH_SHORT).show();
                    } else {
                        //Switch decision for the clicked button.
                        switch (choice) {
                            case "Easy":
                                difficult = "easy";
                                break;
                            case "Medium":
                                difficult = "medium";
                                break;
                            case "Difficult":
                                difficult = "hard";
                                break;
                            default:
                                Toast.makeText(DifficultActivity.this, "Make sure that you pick a level", Toast.LENGTH_SHORT ).show();
                                break;
                        }
                        //Get the name and send it to the QuestionActivity.
                        //name = name1.getText().toString();
                        Log.e("Before Click", name);
                        Intent intent = new Intent(DifficultActivity.this, QuestionActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("player_name", name);
                        intent.putExtra("difficult", difficult);
                        startActivity(intent);
                        Log.e("After Click", name);
                    }
                }
            });

        }

    }

