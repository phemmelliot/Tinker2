package com.example.toyin.tinker.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.toyin.tinker.R;

/**
 * Created by Toyin on 06/01/2017.
 */
public class ClassFinish extends AppCompatActivity {
    public ImageView minions;
    public int pic1, pic2, pic3, pic4, pic5, pic6, pic7, pic8, pic9, pic10, pic11, pic12, shuf;
    public Button start_again;
    public TextView name, gotten, attempted;
    public int got, attempt;
    public String Player;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finish_page);

        //SEt the textView in the xml file
        name =(TextView) findViewById(R.id.name);
        gotten = (TextView) findViewById(R.id.answers_gotten);
        attempted = (TextView) findViewById(R.id.attempted_question);

        got = getIntent().getIntExtra("gotten", 0);
        attempt = getIntent().getIntExtra("attempted", 0);
        //Show a toast message after the player exits the question session.
        if(got == attempt){
            Toast.makeText(getApplicationContext(), "Genius", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Try hard next time!!!", Toast.LENGTH_SHORT).show();
        }

        gotten.setText("  " + got + "  ");
        attempted.setText("" + attempt);

        Player = getIntent().getStringExtra("player_name");

        //Set the name to the name here.
        name.setText(Player);

        //Set various picture to be shuffled to the int variables.
        //Set the images resource id to the pics variable
        pic1 = R.drawable.pic1;
        pic2 = R.drawable.pic2;
        pic3 = R.drawable.pic3;
        pic4 = R.drawable.pic4;
        pic5 = R.drawable.pic5;
        pic6 = R.drawable.pic6;
        pic7 = R.drawable.pic7;
        pic8 = R.drawable.pic8;
        pic9 = R.drawable.pic9;
        pic10 = R.drawable.pic10;
        pic11 = R.drawable.pic11;
        pic12 = R.drawable.pic12;

        //Put the pictures in an array then shuffle them.
        int[] pictures = {pic1, pic2, pic3, pic4, pic5, pic6, pic7, pic8, pic9, pic10, pic11, pic12};
        shufflePictures(pictures);

        //Get the resource id for the parameters in the activity.
        minions = (ImageView) findViewById(R.id.image);
        start_again = (Button) findViewById(R.id.start_again);

        //Set the image to the first image of the shuffled array.
        minions.setImageResource(pictures[0]);

        //Set a listener to the play again button.
        start_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClassFinish.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    public void shufflePictures(int[] option){
        //Shuffle the picture array.
        for (int i = 0; i < option.length; i++) {
            int m = (int) (Math.random() * option.length);
            shuf = option[i];
            option[i] = option[m];
            option[m] = shuf;
        }
    }
}
