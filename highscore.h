#include <iostream>

using namespace std;

template<typename T>
class HighScore{
	public:
		int num;
		int score_array[5];
		void add_score(T x);
		void display_score();
};

template<typename T>
HighScore<T>::void add_score(T x){
	if (num == 0){
		score_array[num] = x;
		num++
	}
	else if (num<5){
                for(i=0; i<num; i++){
                        if (score_array[i]<x){ 
                                score_array[i] = x;
			}
			else{
				num++
				score_array[num-1] = x;
			}
		}
	}		
	else if(num==5) {
		for(i=0; i<num; i++){
			if (score_array[i]<x){
				score_array[i] = x;
			}
		}
	}
	
}

template<typename T>
HighScore<T>::void display_score(){
	for(i=0; i<num; i++){
		cout<<score_array[i]<<endl;
	}
}
