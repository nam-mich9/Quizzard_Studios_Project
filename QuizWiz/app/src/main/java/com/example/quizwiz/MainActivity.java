package com.example.quizwiz;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.*;



public class MainActivity extends AppCompatActivity {
    public static Vector<String> sentences = new Vector<>();
     static  int high_score = 0;
    static  int sport_score = 0;
    static  int his_score = 0;
    static  int tv_score = 0;
    static  int sport_logic = 0;
    static  int his_logic = 0;
    static  int tv_logic = 0;
    String line;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView hsc = findViewById(R.id.HS);
        hsc.setText(highscore());
        //Example of a call to a native method
        //TextView tv = findViewById(R.id.action_text);
        //tv.setText(stringFromJNI());

        /*final Button user = findViewById(R.id.user);
        user.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                openEditUserQuizzes();
            }
        });*/

        final Button customQuizzes = findViewById(R.id.UserCreatedQuizzes);
        customQuizzes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                openEditUserQuizzes();
            }
        });
    }

    public void openEditUserQuizzes()
    {
        startActivity(new Intent(this, EditUserQuizzes.class));
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void gotosports(View view) throws IOException {
        Intent intent = new Intent(this, Difficulty.class);
        startActivity(intent);

        sport_logic = 1;
        his_logic = 0;
        tv_logic = 0;

        sentences.clear();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("sports_questions.txt"), "UTF-8"));
            while ((line = reader.readLine()) != null) {
                sentences.add(line);
            } }
                catch(UnsupportedEncodingException e){
                e.printStackTrace();
            }

    }
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void gotohistory(View view) throws IOException {
        Intent intent = new Intent(this, Difficulty.class);
        startActivity(intent);
            sport_logic = 0;
            his_logic = 1;
            tv_logic = 0;

            sentences.clear();

            BufferedReader reader = null;
            try {
                reader = new BufferedReader(
                        new InputStreamReader(getAssets().open("history_questions.txt"), "UTF-8"));
                while ((line = reader.readLine()) != null) {
                    sentences.add(line);
                } }
            catch(UnsupportedEncodingException e){
                e.printStackTrace();
            }
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void gotoTV(View view) throws IOException {
        Intent intent = new Intent(this, Difficulty.class);
        startActivity(intent);
        sport_logic= 0;
        his_logic = 0;
        tv_logic = 1;
        BufferedReader reader = null;

        sentences.clear();

        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("tv_questions.txt"), "UTF-8"));
            while ((line = reader.readLine()) != null) {
                sentences.add(line);
            } }
        catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }

    }
    public  String highscore(){

        //String.valueOf(Quizmain.high_score)

        if(high_score < Quizmain.new_score){
            high_score = Quizmain.new_score;
        }
            if(sport_logic == 1 && sport_score < Quizmain.new_score){
                    sport_score = Quizmain.new_score;
            }
        if(his_logic == 1 && his_score < Quizmain.new_score){
            his_score = Quizmain.new_score;
        }
        if(tv_logic== 1 && tv_score < Quizmain.new_score){
            tv_score = Quizmain.new_score;
        }

        String hs = String.valueOf(high_score);
        return hs;
    }

    public void gostats(View view) {
        Intent intent = new Intent(this, Stats.class);

        startActivity(intent);
    }
}
