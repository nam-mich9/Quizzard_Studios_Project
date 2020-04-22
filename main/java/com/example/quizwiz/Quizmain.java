package com.example.quizwiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Quizmain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizmain);
    }
    public void gotomain(View view) {
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }
}
