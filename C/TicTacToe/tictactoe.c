#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
void showBoard();
int changeBoard(char input);
char* board;
char choice='a';
char symbol='X';
char other_symbol='O';
int check();
char won=' ';
int randMove();
int aiMove();
int main(){
	printf("Starting game...\n");
	board=(char*)calloc(9, sizeof(char));
	strcpy(board, "123456789");
	printf("X or O? ");
	scanf("%c", &symbol);
	if(!isalpha(symbol)){
		printf("Unrecognized option. Choosing 'O' ");
		symbol='O';
	}
	//symbol is user input
	//other_symbol is computer's move
	if(symbol==other_symbol||toupper(symbol)==other_symbol||symbol=='0'){
		other_symbol='X';
	}
	while(won==' '){
		showBoard();
		printf("Enter a position: ");
		scanf(" %c", &choice);
		if(check()){
			break;
		}
		else if(changeBoard(choice)){
			printf("Tie! Game Over.");
			showBoard();
			return 0;
		}
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
int randMove(){return rand()%10;}
int aiMove(){
	int combo[]={0, 0};
	int index=0;
	int move=index;
	for(int i=0; i<strlen(board); i++){
		if(index==2){
			break;
		}
		if(board[i]==symbol){
			combo[index++]=i;
		}
	}
	if(combo[1]==0){
		move=randMove();
	}else{
		move=(combo[0]*2)-combo[1];
	}
	for(;;){
	if(((isdigit(board[move])) && (board[move]!=symbol)) && (board[move]!=other_symbol)){
		return move;
	}
		move=randMove();
	}
}
int changeBoard(char input){
	int found=1;
	int count=0;
	for(int i=0; i<9; i++){
		if(!isdigit(board[i])){
			count++;
		}
		if(board[i]==input){
			board[i]=symbol;
			found=0;
	}}
	if(count>=8){return 1;}
	if(found){
		printf("\nSpace taken. Enter another value.\n");
	}
	int r=aiMove();
	board[r]=other_symbol;
	return 0;
}
void showBoard(){
	printf("\n");
	for(int j=0; j<9; j++){
		printf("%c ", board[j]);
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
