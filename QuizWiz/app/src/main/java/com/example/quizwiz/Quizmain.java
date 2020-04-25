package com.example.quizwiz;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;


public class Quizmain extends AppCompatActivity {

    Random rand = new Random();
    int rand_num,rand_num1,rand_num2, rand_num3, find_colon;
    int found,found1,found2,found3, found_quote,found_quote1,found_quote2,found_quote3, find_end_of_ques;
    String ans,ans1,ans2,ans3, final_ans, sentence,sentence1, sentence2,sentence3, quest, player_choice;
    String incorrect1,incorrect2,incorrect3;
    int lives = 3;
    int score = 0;
    static int new_score;
    Integer[] a =  { R.id.button7, R.id.button8, R.id.button11, R.id.button12};

    Button tv1, tv2,tv3,tv4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizmain);

        TextView tv = findViewById(R.id.mainquiestion);
        tv.setText(getquestion());
        String[] b =  { getans(), getincorrect1(), getincorrect2(), getincorrect3()};
        TextView nv = findViewById(R.id.displife);
        nv.setText(life());

        TextView nv1 = findViewById(R.id.dispscore);
        nv1.setText(score());

        Collections.shuffle(Arrays.asList(a));

        Collections.shuffle(Arrays.asList(b));

        tv1 = findViewById(a[0]);
        tv1.setText(b[0]);


        tv2 = findViewById(a[1]);
        tv2.setText(b[1]);


        tv3 = findViewById(a[2]);
        tv3.setText(b[2]);


        tv4 = findViewById(a[3]);
        tv4.setText(b[3]);


        tv1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(tv1.getText() == getans()) {
                    scoreplus();
                }else{
                    lifeminus();
                }

                TextView nv = findViewById(R.id.displife);
                nv.setText(life());

                TextView nv1 = findViewById(R.id.dispscore);
                nv1.setText(score());

                TextView tv = findViewById(R.id.mainquiestion);
                tv.setText(getquestion());
                String[] b =  { getans(), getincorrect1(), getincorrect2(), getincorrect3()};

                Collections.shuffle(Arrays.asList(a));
                Collections.shuffle(Arrays.asList(b));

                tv1.setText(b[0]);



                tv2.setText(b[1]);



                tv3.setText(b[2]);



                tv4.setText(b[3]);


            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(tv2.getText() == getans()) {
                    scoreplus();
                }else{
                    lifeminus();
                }

                TextView nv = findViewById(R.id.displife);
                nv.setText(life());

                TextView nv1 = findViewById(R.id.dispscore);
                nv1.setText(score());


                TextView tv = findViewById(R.id.mainquiestion);
                tv.setText(getquestion());
                String[] b =  { getans(), getincorrect1(), getincorrect2(), getincorrect3()};


                Collections.shuffle(Arrays.asList(a));


                Collections.shuffle(Arrays.asList(b));

                tv1.setText(b[0]);



                tv2.setText(b[1]);



                tv3.setText(b[2]);



                tv4.setText(b[3]);




            }
        });

        tv3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(tv3.getText() == getans()) {
                    scoreplus();
                }else{
                    lifeminus();
                }

                TextView nv = findViewById(R.id.displife);
                nv.setText(life());

                TextView nv1 = findViewById(R.id.dispscore);
                nv1.setText(score());


                TextView tv = findViewById(R.id.mainquiestion);
                tv.setText(getquestion());
                String[] b =  { getans(), getincorrect1(), getincorrect2(), getincorrect3()};


                Collections.shuffle(Arrays.asList(a));

                Collections.shuffle(Arrays.asList(b));


                tv1.setText(b[0]);



                tv2.setText(b[1]);



                tv3.setText(b[2]);



                tv4.setText(b[3]);



            }
        });

        tv4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(tv4.getText() == getans()) {
                    scoreplus();
                }else{
                    lifeminus();
                }

                TextView nv = findViewById(R.id.displife);
                nv.setText(life());

                TextView nv1 = findViewById(R.id.dispscore);
                nv1.setText(score());


                TextView tv = findViewById(R.id.mainquiestion);
                tv.setText(getquestion());
                String[] b =  { getans(), getincorrect1(), getincorrect2(), getincorrect3()};


                Collections.shuffle(Arrays.asList(a));

                Collections.shuffle(Arrays.asList(b));


                tv1.setText(b[0]);



                tv2.setText(b[1]);



                tv3.setText(b[2]);



                tv4.setText(b[3]);



            }
        });
    }







    public String getquestion() {

        rand_num = rand.nextInt(MainActivity.sentences.size());

        sentence = MainActivity.sentences.get(rand_num);
        found = sentence.indexOf("\",");
        ans = sentence.substring(found+4, sentence.length());

        found_quote = ans.indexOf('\"');
        final_ans = ans.substring(0,found_quote);

        find_colon = sentence.indexOf(':');
        String quest_part = sentence.substring(find_colon+1,sentence.length());
        find_end_of_ques = quest_part.indexOf("\",");
        quest = quest_part.substring(0, find_end_of_ques);

        return quest;
    }

    public String getans() {
        return final_ans;
    }

    public  String getincorrect1(){

        rand_num1 = rand.nextInt(MainActivity.sentences.size());
        while(rand_num1 == rand_num){
        rand_num1 = rand.nextInt(MainActivity.sentences.size());}

        sentence1 = MainActivity.sentences.get(rand_num1);
        found1 = sentence1.indexOf("\","); //Find instance of ','
        ans1 = sentence1.substring(found1 + 4, sentence1.length()); // Taking subtring from ','+3 to the end

        found_quote1 = ans1.indexOf('\"'); //Finding next "
        incorrect1 = ans1.substring(0, found_quote1); //Taking a new substring from initial ans to the found_quote

            return incorrect1;

    }
    public String getincorrect2(){

        rand_num2 = rand.nextInt(MainActivity.sentences.size());
        while(rand_num2 == rand_num1 || rand_num2 == rand_num){
            rand_num2 = rand.nextInt(MainActivity.sentences.size());}

        sentence2 = MainActivity.sentences.get(rand_num2);
        found2 = sentence2.indexOf("\","); //Find instance of ','
        ans2 = sentence2.substring(found2 + 4, sentence2.length()); // Taking subtring from ','+3 to the end

        found_quote2 = ans2.indexOf('\"'); //Finding next "
        incorrect2 = ans2.substring(0, found_quote2); //Taking a new substring from initial ans to the found_quote

        return incorrect2;

    }
    public String getincorrect3(){

            rand_num3 = rand.nextInt(MainActivity.sentences.size());
            while(rand_num3 == rand_num2 || rand_num3 == rand_num1 || rand_num3 == rand_num)
                {
                    rand_num3 = rand.nextInt(MainActivity.sentences.size());
                }

                sentence3 = MainActivity.sentences.get(rand_num3);
                found3 = sentence3.indexOf("\","); //Find instance of ','
                ans3 = sentence3.substring(found3 + 4, sentence3.length()); // Taking subtring from ','+3 to the end

                found_quote3 = ans3.indexOf('\"'); //Finding next "
                incorrect3 = ans3.substring(0, found_quote3); //Taking a new substring from initial ans to the found_quote

                return incorrect3;

            }


    public  void scoreplus(){

        ++score;

        new_score = score;


    }
    public void lifeminus(){

        --lives;
        if (lives < 1) {
            Intent intent = new Intent(this, MainActivity.class);

            startActivity(intent);
        }
    }

    public  String life(){

       String life = String.valueOf(lives);
        return life;
    }
    public String score(){

        return String.valueOf(score);
       
    }

}


