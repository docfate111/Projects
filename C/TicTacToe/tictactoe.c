#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
void showBoard(); void changeBoard(char input); char* board;
char choice='a'; char symbol='x'; char other_symbol='o';
int check(); char won=' ';
int main(){
	printf("Starting game...\n");
	board=(char *)calloc(9, sizeof(char));
	strcpy(board, "123456789");
	printf("X or O? ");
	scanf("%c", &symbol);
	if(symbol==other_symbol||toupper(symbol)==other_symbol||symbol=='0'){
		other_symbol='x';
	}
	while(won==' '){
		showBoard();
		printf("Enter a position:");
		scanf(" %c", &choice);
		changeBoard(choice);
		if(check()){break;}
		printf("\nI moved. Your turn.\n");
	}
	printf("Tic. Tac. Toe. Three in a row.\n");
	if(won==symbol){
		showBoard();
		printf("You win!");
	}else{
		showBoard();
		printf("You lost!");
	}
	return 0;}
void changeBoard(char input){
	int found=1;
	for(int i=0; i<9; i++){
		if(board[i]==input){board[i]=symbol; found=0;}
	}
	if(found){
		printf("\nSpace taken. Enter another value.\n");
	}
	for(;;){
		int r=rand()%10;
		//if its not chosen already input a random location
		if(board[r]!=symbol){
			board[r]=other_symbol;
			break;
		}
	}
}
void showBoard(){
	printf("\n");
	for(int j=0; j<9; j++){
		printf("%c", board[j]);
		if((j+1)%3==0){printf("\n");}
	}}
int has[]={0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
int check(){
	switch(choice){
		case '1': has[0]=1; break;
		case '2': has[1]=1; break;
		case '3': has[2]=1; break;
		case '4': has[3]=1; break;
		case '5': has[4]=1; break;
		case '6': has[5]=1; break;
		case '7': has[6]=1; break;
		case '8': has[7]=1; break;
		case '9': has[8]=1; break;
		default: printf("\nThat is not an option.\n"); break;
	}
	if((has[0]+has[1]+has[2])==3){
		won=symbol;
		return 1;
	}else if((has[0]+has[3]+has[6])==3){
		won=symbol;
		return 1;
	}else if((has[0]+has[4]+has[8])==3){
		won=symbol;
		return 1;
	}else if((has[2]+has[4]+has[6])==3){
		won=symbol;
		return 1;
	}else if((has[2]+has[7]+has[8])==3){
		won=symbol;
		return 1;
	}else if((has[1]+has[4]+has[7])==3){
		won=symbol;
		return 1;
	}else if((has[3]+has[4]+has[5])==3){
		won=symbol;
		return 1;
	}else if((has[6]+has[7]+has[8])==3){
		won=symbol;
		return 1;
	}
	return 0;
}
