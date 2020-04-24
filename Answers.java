import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Scanner;
import java.util.Random;

class Main{


	public static void main( String[] args){

		Vector<String> sentences = new Vector<String>();
		String line;
		String ans, final_ans, sentence, quest;
		Random rand = new Random(); 

		Scanner input = new Scanner(System.in);

		System.out.print("Pick a Category: Sports[1], History[2], TV/Film[3]: \n");

		int choice = input.nextInt();

		//reading files based on choice

		if(choice == 1){

			File Sports= new File("Sports_Questions.txt");

			try(BufferedReader br = new BufferedReader(new FileReader(Sports))){

				while((line = br.readLine()) !=null){

					sentences.add(line);
				}
			} catch(IOException e){

				e.printStackTrace();
			}

		}

		else if(choice == 2){

			File History= new File("History_Questions.txt");

			try(BufferedReader br = new BufferedReader(new FileReader(History))){

				while((line = br.readLine()) !=null){

					sentences.add(line);
				}
			} catch(IOException e){

				e.printStackTrace();
			}

		}

		else{

			File Tv = new File("TV_Questions.txt");

			try(BufferedReader br = new BufferedReader(new FileReader(Tv))){

				while((line = br.readLine()) !=null){

					sentences.add(line);
				}
			} catch(IOException e){

				e.printStackTrace();
			}

		}

		int rand_num, found, found_quote, find_end_of_ques,find_colon;
		int lives = 2;
		int score = 0;

		//generating question from file
		//creating questions and answer strings

		while(lives!=0){

			rand_num = rand.nextInt(sentences.size());

			sentence = sentences.get(rand_num);
			found = sentence.indexOf("\",");
			ans = sentence.substring(found+4, sentence.length());

			found_quote = ans.indexOf('\"');
			final_ans = ans.substring(0,found_quote);

			find_colon = sentence.indexOf(':');
			String quest_part = sentence.substring(find_colon+1,sentence.length());
			find_end_of_ques = quest_part.indexOf("\",");
			quest = quest_part.substring(0, find_end_of_ques);

			System.out.println(final_ans);
			System.out.println("Current Score: " +score);
			System.out.println("Lives: " +lives);

			Scanner answer = new Scanner(System.in);
			System.out.println("Question: " +quest);

			String player_choice = answer.nextLine();

			System.out.println(final_ans.length());
			System.out.println(player_choice.length());


			if(player_choice.compareTo(final_ans)==0){

				score++;


			}

			else{

				lives--;

			}


		}
		
		//scan.close();
		System.out.println("GAME OVER");


	}
}