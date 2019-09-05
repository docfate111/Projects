#include <stdio.h>
#include <stdlib.h>
int main(int argc, char* argv[]){
	if(argc==2){
		char c;
		FILE *fptr;
		fptr=fopen(argv[1], "r");
		if(fptr==NULL){
			printf("Error!");
			return 1;
		}
		c=fgetc(fptr);
		while(c!=EOF){
			printf("%c", c);
			c=fgetc(fptr);
		}
		fclose(fptr);
		return 0;
	}else{
		printf("Usage: %s [file]", argv[0]);
		return 1;
	}
}
