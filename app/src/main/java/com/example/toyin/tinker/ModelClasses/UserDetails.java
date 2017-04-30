package com.example.toyin.tinker.ModelClasses;
import java.util.Scanner;

/**
 * Created by Toyin on 18/04/2017.
 */
public class UserDetails {

    public String Playername;
    public int score;

    public UserDetails() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public UserDetails(String playername, int score) {
        this.Playername = playername;
        this.score = score;
    }


}