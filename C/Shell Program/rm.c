#include <stdio.h>
#include <stdlib.h>
int main(int argc, char* argv[]){
	if(argc<2){
		printf("Usage %s [What to echo]", argv[0]);
	}
	else{
		for(int i=1; i<argc; i++){
			remove(argv[i]);
		}
	}
	return 0;
}

