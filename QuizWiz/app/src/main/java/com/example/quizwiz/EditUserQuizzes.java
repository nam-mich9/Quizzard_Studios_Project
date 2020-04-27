package com.example.quizwiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class EditUserQuizzes extends AppCompatActivity {

    ArrayList<UserQuiz> savedQuizzes;
    ArrayList<ExampleQuiz> exampleQuizzes = new ArrayList<ExampleQuiz>();

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_quizzes);

        loadQuizzes();

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new ExampleAdapter(exampleQuizzes);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        final Button createQuizButton = findViewById(R.id.createQuizButton);
        createQuizButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                openCreateQuiz();
            }
        });

        final Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                returnToMainMenu();
            }
        });
    }

    private void openCreateQuiz()
    {
        Intent intent = new Intent(this, CreateQuiz.class);
        intent.putExtra("updateQuiz", false);
        startActivity(intent);
    }

    private void returnToMainMenu()
    {
        startActivity(new Intent(this, MainActivity.class));
    }

    private void loadQuizzes()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("quizzes", null);
        Type type = new TypeToken<ArrayList<UserQuiz>>() {}.getType();

        savedQuizzes = gson.fromJson(json, type);

        if (savedQuizzes == null)
        {
            savedQuizzes = new ArrayList<UserQuiz>();
        }

        for (int i = 0; i < savedQuizzes.size(); i++)
        {
            exampleQuizzes.add(new ExampleQuiz(savedQuizzes.get(i).getQuizName(), i + 1, savedQuizzes.get(i).getNumberOfQuestions()));
        }
    }

    public void removeItem(int position)
    {
        exampleQuizzes.remove(position);
        adapter.notifyItemRemoved(position);
    }
}
