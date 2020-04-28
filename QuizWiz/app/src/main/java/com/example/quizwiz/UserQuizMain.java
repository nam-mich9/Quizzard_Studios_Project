package com.example.quizwiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class UserQuizMain extends AppCompatActivity {

    UserQuiz quiz;

    int time = 0;
    CountDownTimer countDownTimer;
    TextView timer;

    TextView quizName, question, progress;
    TextView scoreView,passesView,livesView;
    Button answer1, answer2, answer3, answer4;

    int correct = 0;
    int questionIndex = 0;

    Dialog confirmationpass;
    TextView Passask;
    Button passbutton, closePop;

    int score = 0;
    int passes = 3;
    int lives = 3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_quiz_main);

        scoreView = findViewById(R.id.score);
        passesView = findViewById(R.id.passes);
        livesView = findViewById(R.id.lives);

        quiz = loadQuiz(getIntent().getExtras().getInt("quizIndex"));

        timer = findViewById(R.id.timer);

        quizName = findViewById(R.id.userQuizName);
        question = findViewById(R.id.question);
        progress = findViewById(R.id.progress);
        answer1 = findViewById(R.id.answerChoice1);

        //This allows you to choose the correct answer amongst the inputted options
        answer1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                chooseAnswer(getCurrentQuestion().getAnswer(1));
            }
        });

        answer2 = findViewById(R.id.answerChoice2);
        answer2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                chooseAnswer(getCurrentQuestion().getAnswer(2));
            }
        });

        answer3 = findViewById(R.id.answerChoice3);
        answer3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                chooseAnswer(getCurrentQuestion().getAnswer(3));
            }
        });

        answer4 = findViewById(R.id.answerChoice4);
        answer3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                chooseAnswer(getCurrentQuestion().getAnswer(4));
            }
        });

        setupUI();

        //Sensor for shake
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorListener = new ShakeEventListener();
        //Waits for a shake
        mSensorListener.setOnShakeListener(new ShakeEventListener.OnShakeListener() {
            //If there is a shake, a pop-up window will come up
            public void onShake(){
                confirmationpass.setContentView(R.layout.dialoguebox);
                closePop = (Button) confirmationpass.findViewById(R.id.closePop);
                passbutton = (Button) confirmationpass.findViewById(R.id.passbutton);
                Passask = (TextView) confirmationpass.findViewById(R.id.Passask);

                //Button to close popup with no pass
                closePop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        confirmationpass.dismiss();
                    }
                });
                //Button to close popup with pass
                passbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        countDownTimer.cancel();
                        if (lives > 0 ) {
                            countDownTimer.start();
                        }
                        confirmationpass.dismiss();
                    }
                });
                //Showing pop up
                confirmationpass.getWindow();
                confirmationpass.show();
            }
        });
    }



    //Runs the Sensor to wait for a shake
    private SensorManager mSensorManager;
    private ShakeEventListener mSensorListener;
    //Keeps the sensor going or starts it again after pause
    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorListener,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_UI);
    }
    //Keeps the sensor on pause
    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
        super.onPause();
    }


    //Stops the countdown clock
    @Override
    public void onDestroy()
    {
        super.onDestroy();

        stopCountDownTimer();
    }



    //Loads any previously made quizzes
    private UserQuiz loadQuiz(int index)
    {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("quizzes", null);
        Type type = new TypeToken<ArrayList<UserQuiz>>() {}.getType();

        ArrayList<UserQuiz> savedQuizzes = gson.fromJson(json, type);

        if (savedQuizzes == null)
        {
            savedQuizzes = new ArrayList<UserQuiz>();
        }

        return savedQuizzes.get(index);
    }



    //Sets up the layout for custom quiz
    private void setupUI()
    {
        scoreView.setText(Integer.toString(score));
        passesView.setText("Passes: " + Integer.toString(passes));
        livesView.setText(Integer.toString(lives));

        quizName.setText(quiz.getQuizName());
        setTime();
        populateQuestion(questionIndex);
    }


    //Allows user to create a custom quiz
    private void populateQuestion(int index)
    {
        resetTimer(time);
        setProgress();

        UserQuestion quizQuestion = quiz.getQuestion(index);

        question.setText(quizQuestion.getQuestion());

        answer1.setText(quizQuestion.getAnswer(1));
        answer2.setText(quizQuestion.getAnswer(2));
        answer3.setText(quizQuestion.getAnswer(3));
        answer4.setText(quizQuestion.getAnswer(4));
    }


    //Allows user to choose Difficulty and changes time per question
    private void setTime()
    {
        int difficulty = getIntent().getExtras().getInt("difficulty");

        if (difficulty == 0)
        {
            time = 30;
        }
        else if (difficulty == 1)
        {
            time = 20;
        }
        else
        {
            time = 10;
        }
    }


    //Resets the Countdown timer
    private void resetTimer(int time)
    {
        stopCountDownTimer();

        countDownTimer = new CountDownTimer(time * 1000, 1000)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {
                timer.setText("Seconds Remaining: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish()
            {
                //decrementLives();
                incrementQuestionIndex();
            }
        }.start();
    }


    //Allows user to choose a answer
    private void chooseAnswer(String chosenAnswer)
    {
        if (isCorrectAnswer(chosenAnswer))
        {
            correct++;
        }

        incrementQuestionIndex();
    }


    //Checks to see if the question selected is the correct answer
    private boolean isCorrectAnswer(String chosenAnswer)
    {
        return chosenAnswer.equals(quiz.getQuestion(questionIndex).getCorrectAnswer());
    }


    //Goes through question list
    private void incrementQuestionIndex()
    {
        questionIndex++;

        if (questionIndex > quiz.getNumberOfQuestions() - 1)
        {
            openResultsActivity();
        }
        else
        {
            populateQuestion(questionIndex);
        }
    }


    //Shows the results of the quiz
    private void openResultsActivity()
    {
        Intent intent = new Intent(this, UserQuizResults.class);
        intent.putExtra("correct", correct);
        intent.putExtra("total", quiz.getNumberOfQuestions());
        startActivity(intent);
        finish();
    }


    //Chooses the current question
    private UserQuestion getCurrentQuestion()
    {
        return quiz.getQuestion(questionIndex);
    }


    //Stops the countdown timer
    private void stopCountDownTimer()
    {
        if (countDownTimer != null)
        {
            countDownTimer.cancel();
        }
    }


    //Shows progress
    private void setProgress()
    {
        progress.setText(questionIndex + 1 + "/" + quiz.getNumberOfQuestions());
    }

}
