package com.example.quizwiz;

public class UserQuestion
{
    public String question;
    public String choice1;
    public String choice2;
    public String choice3;
    public String choice4;
    public String correctAnswer;

    UserQuestion(String question, String choice1, String choice2, String choice3, String choice4, String correctAnswer)
    {
        this.question = question;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.choice4 = choice4;
        this.correctAnswer = correctAnswer;
    }
}
