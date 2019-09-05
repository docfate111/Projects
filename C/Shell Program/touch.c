#include <stdio.h>
#include <stdlib.h>
int main(int argc, char* argv[]){
	if(argc==2){
		FILE *fptr;
		fptr=fopen(argv[1], "w");
		if(fptr==NULL){
			printf("Error!");
			return 1;
		}
		fclose(fptr);
		return 0;
	}else{
		printf("Usage: %s [new file]", argv[0]);
		return 1;
	}
}
