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
	cout<<"numless5"<<endl;
                for(int i=0; i<num; i++){
		cout<<"forloop"<<endl;
                        if (score_array[i]<x){
			cout<<"if"<<endl; 
                                score_array[i] = x;
				break;
			}
			else{
			cout<<"else"<<endl;
				score_array[num] = x;
			}
		}
		num++;
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
