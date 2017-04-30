package com.example.toyin.tinker.Fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;

import com.example.toyin.tinker.R;

/**
 * Created by Toyin on 28/12/2016.
 */
public abstract class SingleFragmentActivity extends FragmentActivity{
    //A protected method that is used over and over again and create the fragment space needed.
    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInsBundle){
        super.onCreate(savedInsBundle);
        setContentView(R.layout.activity_fragment);

        //Call the fragment manager and made a fragment in it.
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        //If the fragment called is empty, empty, create an instance of one as the container for the fragments
        if(fragment == null){
            fragment = createFragment();
            //Start a container for the fragment.
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }

}
