package com.example.toyin.tinker.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.toyin.tinker.Data.SqliteOpenHelper;
import com.example.toyin.tinker.Data.Tinker;
import com.example.toyin.tinker.ModelClasses.UserDetails;
import com.example.toyin.tinker.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.HashMap;

/**
 * Created by Toyin on 06/01/2017.
 */
public class ClassFinish extends AppCompatActivity {
    public ImageView minions;
    public int pic1, pic2, pic3, pic4, pic5, pic6, pic7, pic8, pic9, pic10, pic11, pic12, shuf;
    public LinearLayout start_again, menu_button;
    public TextView name, gotten, attempted;
    public int got, attempt;
    public String Player;
    private DatabaseReference mDatabase;
    private FirebaseDatabase firebaseDatabase;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finish_page);
        //Set toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        else if(got == (attempt / 2)){
            Toast.makeText(getApplicationContext(), "Cool, You tried. Try harder next time", Toast.LENGTH_LONG).show();
        }
        else if(got == ((attempt / 3) * 2)){
            Toast.makeText(getApplicationContext(), "Just a little more harder", Toast.LENGTH_LONG).show();
        }
        else if(got == ((attempt / 3) * 1)){
            Toast.makeText(getApplicationContext(), "Hmm, just a littel more trial", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Try hard next time!!!", Toast.LENGTH_LONG).show();
        }



        gotten.setText("  " + got + "  ");
        attempted.setText("" + attempt);

        Player = getIntent().getStringExtra("player_name");


        HashMap<String, Integer> user_data = new HashMap<>();
        user_data.put(Player, got);

//        HashMap<String, HashMap> push_details = new HashMap<>();
//        push_details.put("Detail", push_details);

        //Implementing fireabse
        firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = firebaseDatabase.getReference().child("user_data");

        //Using the UserDetails class
        UserDetails user = new UserDetails(Player, got);

        //Set the name to the name here.
        name.setText(Player);

        //Set various picture to be shuffled to the int variables.
        //Set the images resource id to the pics variable


        //Put the pictures in an array then shuffle them.
        int[] pictures = {pic1, pic2, pic3, pic4, pic5, pic6, pic7, pic8, pic9, pic10, pic11, pic12};
        shufflePictures(pictures);
        //Get the resource id for the parameters in the activity.
        //minions = (ImageView) findViewById(R.id.image);
        start_again = (LinearLayout) findViewById(R.id.start_again);
        menu_button = (LinearLayout) findViewById(R.id.menu_button);
        //Set the image to the first image of the shuffled array.
        //minions.setImageResource(pictures[0]);

        //Set a listener to the play again button.
        start_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClassFinish.this, DifficultActivity.class);
                intent.putExtra("player_name", Player);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        menu_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClassFinish.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        displayDatabaseInfo();
    }

    private void displayDatabaseInfo(){
        SqliteOpenHelper openHelper = new SqliteOpenHelper(this);
        SQLiteDatabase db = openHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Tinker.TinkerEntry.TABLE_NAME, null);
        try {
            TextView display = (TextView) findViewById(R.id.db_test);
        }
        finally {

        }
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

    @Override
    public void onBackPressed(){
        //Do nothing if player clicks the back button.
    }
}
