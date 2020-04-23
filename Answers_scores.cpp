#include <iostream>
#include <fstream>
#include <string>
#include <vector>
using namespace std;

int main() {
  
    srand(time(0)); 
    vector <string> sentences;
    int rand_num, find_colon;
    int found, found_quote, find_end_of_ques;
    string ans, final_ans, sentence, quest, player_choice;  
    string line;
    int choice;

    cout<<"Pick a Category: Sports[1], History[2], TV/Film[3]"<<endl;
    cin>>choice;
    cin.ignore();

    if(choice==1){

        ifstream myfile;
        myfile.open("Sports_Questions.txt");
        
        while(getline(myfile,line)){

            sentences.push_back(line);
        
        }
        myfile.close();


    }

    else if(choice==2){

        ifstream myfile;
        myfile.open("History_Questions.txt");
        
        while(getline(myfile,line)){

            sentences.push_back(line);
        
        }
        myfile.close();

    }

    else{

        ifstream myfile;
        myfile.open("TV_Questions.txt");
        
        while(getline(myfile,line)){

            sentences.push_back(line);
        
        }
        myfile.close();

    }

    int lives = 2, score = 0;
    while(lives!=0) {   
         

        rand_num = rand() % sentences.size();

        sentence = sentences[rand_num];
        found = sentence.find("\","); //Find instance of ','
        ans = sentence.substr(found+4, sentence.length()); // Taking subtring from ','+3 to the end

        found_quote = ans.find('\"'); //Finding next "
        final_ans = ans.substr(0, found_quote); //Taking a new substring from initial ans to the found_quote

        find_colon = sentence.find(':');
        string quest_part = sentence.substr(find_colon+1, sentence.length());
        find_end_of_ques = quest_part.find("\",");
        quest = quest_part.substr(0, find_end_of_ques);


        cout<<final_ans<<endl;
        cout<<"Current Score: "<<score<< " Lives: "<<lives<<endl;
        cout<<"Question: "<<quest<<endl;
        getline(cin,player_choice);

        cout<<final_ans.length()<<endl;
        cout<<player_choice.length()<<endl;

        if (player_choice.compare(final_ans) == 0){
            score++;

        }

        else{
            lives--;

        }

    }

    cout<<"GAME OVER"<<endl;
    return 0;
}


    





