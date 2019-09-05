#include <stdio.h>
#include <stdlib.h>
int main(int argc, char* argv[]){
	if(argc==3){
		char c;
		FILE *fptr;
		fptr=fopen(argv[1], "r");
		FILE *fptr2;
		fptr2=fopen(argv[2], "w");
		if(fptr==NULL||fptr2==NULL){
			printf("Error!");
			return 1;
		}
		c=fgetc(fptr);
		while(c!=EOF){
			fputc(c, fptr2);
			c=fgetc(fptr);
		}
		fclose(fptr);
		fclose(fptr2);
		return 0;
	}else{
		printf("Usage: %s [file] [new-file]", argv[0]);
		return 1;
	}
}
