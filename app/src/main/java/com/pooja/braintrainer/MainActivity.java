package com.pooja.braintrainer;

import android.graphics.Bitmap;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startBtn;
    ArrayList<Integer> psblResult = new ArrayList<Integer>();
    TextView result;
    TextView noOfQueTextView;
    TextView queTextView;
    TextView timer;
    int locationOfCorrectAns;
    int score = 0;
    int ques = 0;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainBtn;
    RelativeLayout mainRelative;

    public void startGame(View view) {

        startBtn.setVisibility(View.INVISIBLE);
        mainRelative.setVisibility(RelativeLayout.VISIBLE);
        playAgain(findViewById(R.id.playAgain));
    }

    public void playAgain(View view) {
        score = 0;
        ques = 0;
        timer.setText("30s");
        noOfQueTextView.setText("0/0");
        result.setText("");
        playAgainBtn.setVisibility(View.INVISIBLE);
        createQue();


        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                playAgainBtn.setVisibility(View.VISIBLE);
                timer.setText("0s");
                result.setText("Your Score is " + Integer.toString(score) + "/"  + Integer.toString(ques));

            }
        }.start();
    }

    public void createQue() {

        Random random = new Random();

        int a = random.nextInt(21);
        int b = random.nextInt(21);

        queTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAns = random.nextInt(4);
        psblResult.clear();

        int incorrectAns;

        for(int i=0; i<4; i++) {

            if (i == locationOfCorrectAns) {
                psblResult.add(a + b);
            }
            else {
                incorrectAns = random.nextInt(41);
                while (incorrectAns == a+b) {
                    incorrectAns = random.nextInt(41);
                }
                psblResult.add(incorrectAns);
            }

        }

        button0.setText(Integer.toString(psblResult.get(0)));
        button1.setText(Integer.toString(psblResult.get(1)));
        button2.setText(Integer.toString(psblResult.get(2)));
        button3.setText(Integer.toString(psblResult.get(3)));

    }

    public void chooseAnswer(View view) {

        //Log.i("Tag", (String) view.getTag());
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAns+1))) {
            score++;
            result.setText("Correct!");
        }
        else {
            result.setText("Wrong Ans..");
        }

        ques++;
        noOfQueTextView.setText(Integer.toString(score) + "/"  + Integer.toString(ques));
        createQue();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = (Button) findViewById(R.id.startBtn);
        queTextView = (TextView) findViewById(R.id.queTextView);
        result = (TextView) findViewById(R.id.result);
        timer =(TextView) findViewById(R.id.timerTextView);
        noOfQueTextView = (TextView) findViewById(R.id.noOfQueTextView);
        button0 = (Button) findViewById(R.id.option0);
        button1 = (Button) findViewById(R.id.option1);
        button2 = (Button) findViewById(R.id.option2);
        button3 = (Button) findViewById(R.id.option3);
        playAgainBtn = (Button) findViewById(R.id.playAgain);
        mainRelative = (RelativeLayout) findViewById(R.id.mainRelative);


    }
}
