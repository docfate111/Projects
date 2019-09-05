#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/wait.h>
#include "builtin.h"
#define BUFSIZE 64
#define DELIM " \t\r\n\a"
char *sh_read();
char **sh_split(char* line);
int main(){
	char* line; char** args; int status;
	 do{
                printf("THEL>");
		//get input and store it in line:
                line=sh_read();
		//divide input into char** array
		args=sh_split(line);
		//execute arguments
		status=sh_exec(args);
        }while(status);
	return 0;
}
char *sh_read(){ //reads input of unlimited size
	char *line=NULL;
	size_t bufsize=0;
	getline(&line, &bufsize, stdin);
	return line;
}
char **sh_split(char* line){//divides input into arguments
	int bufsize=BUFSIZE, position=0;
	char **tokens=malloc(bufsize*sizeof(char*));
	char *token;
	if(!tokens){fprintf(stderr, "tsh: allocation error\n");
		exit(0);}
	token=strtok(line, DELIM);
	//splits string whenever there is a space
	while(token!=NULL){
		tokens[position]=token;
		position++;
		if(position>=bufsize){
			bufsize+=BUFSIZE;
			tokens=realloc(tokens, bufsize*sizeof(char*));
			if(!tokens){fprintf(stderr, "tsh: allocation error\n");
					exit(0);
			}
		}
		token=strtok(NULL, DELIM);
		}
		tokens[position]=NULL;
		return tokens;
}
