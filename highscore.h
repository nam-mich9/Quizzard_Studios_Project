#include <iostream>

using namespace std;

template<typename T>
class HighScore{
	public:
		int num=0;
		int score_array[5];
		void add_score(T x);
		void display_score();
};

template<typename T>
void HighScore<T>:: add_score(T x){
	if (num == 0){
		score_array[num] = x;
		num++;
	}
	else if (num<5){
		int count = 0;
                for(int i=0; i<num; i++){
		cout<<i<<endl;
                        if (score_array[i]<x){
			cout<<"if"<<endl; 
                                for (int j=num-1; j==i; j--){
					score_array[j+1] = score_array[j];
				}
				score_array[i] = x;
				break;
			}
			count++;
		}
		if (count==num){
		cout<<"count"<<endl;
			score_array[num] = x;
		}
		num++;
		cout<< num<< endl;
	}		
	else if(num==5) {
		for(int i=0; i<num; i++){
			if (score_array[i]<x){
				score_array[i] = x;
			}
		}
	}
	
}

template<typename T>
void HighScore<T>:: display_score(){
	for(int i=0; i<num; i++){
		cout<<score_array[i]<<endl;
	}
}
