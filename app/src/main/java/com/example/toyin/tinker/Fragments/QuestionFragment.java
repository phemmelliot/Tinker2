package com.example.toyin.tinker.Fragments;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.toyin.tinker.Activities.ClassFinish;
import com.example.toyin.tinker.Activities.QuestionActivity;
import com.example.toyin.tinker.ModelClasses.QuestionWorld;
import com.example.toyin.tinker.R;

import java.math.BigDecimal;

/**
 * Created by Toyin on 03/01/2017.
 */
public class QuestionFragment extends android.support.v4.app.Fragment {
    public boolean checkTag;
    public int numberOfAnswersGotten, numberOfQuestions;
    public double result, d1, d2, d3;
    private TextView A;
    private TextView B;
    private TextView C;
    private TextView D;
    public TextView attempt, gotten;
    private String answer_option;
    private QuestionWorld mQuestion;
    private TextView question, timer;
    private String shuf, str;
    private String shuf2;
    public int progress;
    public CountDownTimer countdown;
    public String difficult;


    public static double round(double paramDouble, int paramInt) {
        return new BigDecimal(Double.toString(paramDouble)).setScale(paramInt, 4).doubleValue();
    }

    //Check if answer is correct or wrong, then update question.
    public void checkAnswer(TextView[] paramArrayOfTextView, int paramInt) {

        checkTag = ((Boolean) paramArrayOfTextView[paramInt].getTag()).booleanValue();
        //Cancel the timer of the first question.
        if(numberOfQuestions == 1 && numberOfAnswersGotten == 0){
            stopTimer();
        }
        if (checkTag) {
            numberOfAnswersGotten++;
            //Show the correct message then update the question
            Toast toast = Toast.makeText(getActivity(), "Right", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.LEFT, 0, 0);
            toast.show();
            updateQuestion(paramArrayOfTextView);
            //If the user makes a click, cancel the countdown timer.
        }else{
            //Show the wrong message and then update the question.
            Toast toast = Toast.makeText(getActivity(), "Wrong", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.LEFT, 0, 0);
            toast.show();
            updateQuestion(paramArrayOfTextView);
        }
    }

    public void doubleShuffle(String[] option) {
        for (int i = 0; i < option.length; i++) {
            int m = (int) (Math.random() * option.length);
            shuf = option[i];
            option[i] = option[m];
            option[m] = shuf;
        }
        for (int j = 0; j < option.length; j++) {
            int k = (int) (Math.random() * option.length);
            shuf2 = option[j];
            option[j] = option[k];
            option[k] = shuf2;
        }
    }
    @Override
    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
    }

    @Override
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        View localView = paramLayoutInflater.inflate(R.layout.fragment_question, paramViewGroup, false);
        question = (TextView) localView.findViewById(R.id.question_text);
        A = (TextView) localView.findViewById(R.id.option_a);
        B = (TextView) localView.findViewById(R.id.option_b);
        C = (TextView) localView.findViewById(R.id.option_c);
        D = (TextView) localView.findViewById(R.id.option_d);
        attempt = (TextView) localView.findViewById(R.id.attempt);
        gotten = (TextView) localView.findViewById(R.id.gotten);
        timer = (TextView) localView.findViewById(R.id.timer);

        return localView;
    }
    @Override
    public void onViewCreated(View paramView, Bundle paramBundle) {
        super.onViewCreated(paramView, paramBundle);

        //Set the initial number of question gotten right.
        numberOfAnswersGotten = 0;
        //Set the initial number of question viewed.
        numberOfQuestions = 0;

        //Create an instance of the class again
        TextView[] option = {A, B, C, D};

        //Call the clickListener method which set the question and sets a listener to it.
        updateQuestion(option);
    }

    public void stopStartTimer(){

        //Increase the number of attempted question
        numberOfQuestions++;

        //TimerClass instance.
        difficult = getActivity().getIntent().getStringExtra("difficult");
        switch (difficult){
            case "easy":
                countdown = new CountDownTimer(15000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        progress = (int) (millisUntilFinished/1000);
                        timer.setText(Integer.toString(progress));
                        Log.e("Tick", "" + millisUntilFinished);
                    }

                    @Override
                    public void onFinish() {
                        String name = getActivity().getIntent().getStringExtra("player_name");
                        Intent intent = new Intent(getActivity(), ClassFinish.class);
                        intent.putExtra("gotten", numberOfAnswersGotten);
                        intent.putExtra("attempted", numberOfQuestions);
                        intent.putExtra("player_name", name);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                };
                break;
            case "medium":
                countdown = new CountDownTimer(10000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        progress = (int) (millisUntilFinished/1000);
                        timer.setText(Integer.toString(progress));
                        Log.e("Tick", "" + millisUntilFinished);
                    }

                    @Override
                    public void onFinish() {
                        String name = getActivity().getIntent().getStringExtra("player_name");
                        Intent intent = new Intent(getActivity(), ClassFinish.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("gotten", numberOfAnswersGotten);
                        intent.putExtra("attempted", numberOfQuestions);
                        intent.putExtra("player_name", name);
                        startActivity(intent);
                    }
                };
                break;
            case "hard":
                countdown = new CountDownTimer(7000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        progress = (int) (millisUntilFinished/1000);
                        timer.setText(Integer.toString(progress));
                        Log.e("Tick", "" + millisUntilFinished);
                    }

                    @Override
                    public void onFinish() {
                        String name = getActivity().getIntent().getStringExtra("player_name");
                        Intent intent = new Intent(getActivity(), ClassFinish.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("gotten", numberOfAnswersGotten);
                        intent.putExtra("attempted", numberOfQuestions);
                        intent.putExtra("player_name", name);
                        startActivity(intent);
                    }
                };
                break;
            case "":
                Toast.makeText(getActivity(), "Please select a difficulty level", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;

        }
    }

    public void stopTimer(){
        ((QuestionActivity) getActivity()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //Cancel the countDown variable.
                if(countdown != null)
                countdown.cancel();
            }
        });
    }


    //Method that updates questions
    public void updateQuestion(final TextView[] option) {

        //Create an instance of the CountDownTimer.
        stopStartTimer();

        //Start timer
        countdown.start();

        //Set the number of questions attempted and the ones gotten while playing.
        gotten.setText("" + numberOfAnswersGotten);
        attempt.setText("" + numberOfQuestions);

        //Get a new question from the model class.
        mQuestion = new QuestionWorld();
        mQuestion.setQestion();
        question.setText(mQuestion.getOutput().toString());
        String[] stringOptions = {"A", "B", "C", "D"};
        doubleShuffle(stringOptions);


        for (int i = 0; i < stringOptions.length; i++) {
            Log.e("Options_values", stringOptions[i]);
        }
        answer_option = stringOptions[0];
        Log.e("Check answer_option", this.answer_option);
        result = mQuestion.getResult();
        result = round(result, 2);
        d1 = result + round(Math.random(), 2);
        d2 = result + round(Math.random(), 2);
        d3 = result + round(Math.random(), 2);

        //Set initial boolean checkTag value to false.
        checkTag = false;

        //Get the first element of the stringOptions.
        str = answer_option;

        switch (str) {
            case "A":
                option[0].setTag(true);
                option[0].setText(this.result + "");
                option[1].setTag(Boolean.valueOf(false));
                option[1].setText(d1 + "");
                option[2].setTag(Boolean.valueOf(false));
                option[2].setText(d2 + "");
                option[3].setTag(Boolean.valueOf(false));
                option[3].setText(d3 + "");
                break;
            case "B":
                option[1].setTag(true);
                option[1].setText(this.result + "");
                option[0].setTag(Boolean.valueOf(false));
                option[0].setText(d1 + "");
                option[2].setTag(Boolean.valueOf(false));
                option[2].setText(d2 + "");
                option[3].setTag(Boolean.valueOf(false));
                option[3].setText(d3 + "");
                break;
            case "C":
                option[2].setTag(true);
                option[2].setText(this.result + "");
                option[0].setTag(Boolean.valueOf(false));
                option[0].setText(d1 + "");
                option[1].setTag(Boolean.valueOf(false));
                option[1].setText(d2 + "");
                option[3].setTag(Boolean.valueOf(false));
                option[3].setText(d3 + "");
                break;
            case "D":
                option[3].setTag(true);
                option[3].setText(this.result + "");
                option[0].setTag(Boolean.valueOf(false));
                option[0].setText(d1 + "");
                option[1].setTag(Boolean.valueOf(false));
                option[1].setText(d2 + "");
                option[2].setTag(Boolean.valueOf(false));
                option[2].setText(d3 + "");
                break;
            default:
                break;
        }

        //Check for equality in answer and modify.
        if (result == d1) {
            d1 = round(d1 + round((int) (10.0D * Math.random()), 2), 2);
        }

        else if (result == d2) {
            d2 = round(d2 + round((int) (10.0D * Math.random()), 2), 2);
        }

        else if (result == d3) {
            d3 = round(d3 + round((int) (10.0D * Math.random()), 2), 2);
        }
        else if (d1 == d2) {
            d1 = round(d1 + round((int) (10.0D * Math.random()), 2), 2);
        }

        else if (d1 == d3) {
            d3 = round(d3 + round((int) (10.0D * Math.random()), 2), 2);
        }

        else if (d2 != d3) {
            d2 = round(d2 + round((int) (10.0D * Math.random()), 2), 2);
        }

        //Set a listener for each of the options.
        option[0].setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                stopTimer();
                checkAnswer(option, 0);

            }
        });

        option[1].setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                stopTimer();
                checkAnswer(option, 1);
            }
        });

        option[2].setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                stopTimer();
                checkAnswer(option, 2);
            }
        });

        option[3].setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                stopTimer();
                checkAnswer(option, 3);
            }
        });
    }


    @Override
    public void onPause() {
        super.onPause();
    }

}