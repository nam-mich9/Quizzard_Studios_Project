package com.example.quizwiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class UserQuizMain extends AppCompatActivity {

    UserQuiz quiz;

    int time = 0;
    CountDownTimer countDownTimer;
    TextView timer;
    /*TextView scoreView;
    TextView passesView;
    TextView livesView;*/

    TextView quizName;
    TextView question;
    TextView progress;
    Button answer1;
    Button answer2;
    Button answer3;
    Button answer4;

    /*int score = 0;
    int passes = 3;
    int lives = 3;*/

    int correct = 0;

    int questionIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_quiz_main);

        quiz = loadQuiz(getIntent().getExtras().getInt("quizIndex"));

        timer = findViewById(R.id.timer);
        /*scoreView = findViewById(R.id.score);
        passesView = findViewById(R.id.passes);
        livesView = findViewById(R.id.lives);*/

        quizName = findViewById(R.id.userQuizName);
        question = findViewById(R.id.question);
        progress = findViewById(R.id.progress);
        answer1 = findViewById(R.id.answerChoice1);
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
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();

        stopCountDownTimer();
    }

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

    private void setupUI()
    {
        /*scoreView.setText(Integer.toString(score));
        passesView.setText("Passes: " + Integer.toString(passes));
        livesView.setText(Integer.toString(lives));*/

        quizName.setText(quiz.getQuizName());
        setTime();
        populateQuestion(questionIndex);
    }

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

    private void chooseAnswer(String chosenAnswer)
    {
        if (isCorrectAnswer(chosenAnswer))
        {
            //incrementScore();
            correct++;
        }
        /*else
        {
            decrementLives();
        }*/

        incrementQuestionIndex();
    }

    private boolean isCorrectAnswer(String chosenAnswer)
    {
        return chosenAnswer.equals(quiz.getQuestion(questionIndex).getCorrectAnswer());
    }

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

    private void openResultsActivity()
    {
        Intent intent = new Intent(this, UserQuizResults.class);
        intent.putExtra("correct", correct);
        intent.putExtra("total", quiz.getNumberOfQuestions());
        startActivity(intent);
        finish();
    }

    private UserQuestion getCurrentQuestion()
    {
        return quiz.getQuestion(questionIndex);
    }

    private void stopCountDownTimer()
    {
        if (countDownTimer != null)
        {
            countDownTimer.cancel();
        }
    }

    private void setProgress()
    {
        progress.setText(questionIndex + 1 + "/" + quiz.getNumberOfQuestions());
    }
}
