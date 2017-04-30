package com.example.toyin.tinker.Activities;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.toyin.tinker.Fragments.QuestionFragment;
import com.example.toyin.tinker.R;

/**
 * Created by Toyin on 28/12/2016.
 */
public class QuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = new QuestionFragment();
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }




    }


}

