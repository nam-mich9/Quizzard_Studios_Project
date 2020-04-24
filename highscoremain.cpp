#include <iostream>
#include "highscore.h"

using namespace std;

int main(){
        HighScore <int> h;
	h.add_score(6);
	h.add_score(4);
	h.add_score(5);
	h.display_score();
return 0;
}
