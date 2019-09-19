#include <stdio.h>
#include <stdlib.h>
int main(int argc, char* argv[]){
	if(argc<2){
		printf("Usage %s [What to echo]", argv[0]);
	}
	else{
		for(int i=1; i<argc; i++){
			printf("%s ", argv[i]);
		}
	}
	return 0;
}

