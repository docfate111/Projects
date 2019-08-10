from check4 import *
import random
class Board:
    def __init__(self, width, height):
        self.width = width
        self.height = height
        self.data = [[' ']*width for row in range(height)]
        # We do not need to return anything from a constructor!
    def __repr__(self):
        s = ''                          # the string to return
        for row in range(0, self.height):
            s += '|'
            for col in range(0, self.width):
                s += self.data[row][col] + '|'
            s += '\n'
        s += (2*self.width + 1) * '-'   # bottom of the board
        # and the numbers underneath here
        s+='\n '
        s+=" ".join([str(i) for i in range(self.width)])
        return s
    def addMove(self, col, ox): 
        for i in range(self.height-1, -1, -1):
            if(self.data[i][col]==' '):
            #if there is an empty slot set it equal to symbol
                self.data[i][col]=ox
                return
            #otherwise go up a row
    def clear(self):
        self.data=[[' ']*self.width for row in range(self.height)]
    def setBoard(self, moveString):
        nextChecker = 'X'   # start by playing 'X'
        for colChar in moveString:
            col = int(colChar)
            if 0 <= col <= self.width:
                self.addMove(col, nextChecker)
            if nextChecker == 'X':
               nextChecker = 'O'
            else:
                nextChecker = 'X'
    def allowsMove(self, c):
        if(c<0 or c>=self.width):
            return False
        #if column isnt full to the top
        if(self.data[0][c]!=' '):
            return False
        return True
    def isFull(self):
        count=0
        for i in range(self.width):
            if(self.data[self.height-1][i]!=' '):
                count+=1
        return count==self.width
    def delMove(self, c):
        if(self.data[self.height-1][c]!=' ' and self.data[self.height-2][c]==' '):
            self.data[self.height-1][c]=' '
        for i in range(self.height-1):
            if(self.data[i+1][c]!=' ' and self.data[i][c]!=' '):
                self.data[i][c]=' '
                break
    def winsFor(self, ox):
        for row in range(self.width):
            for col in range(self.height):
                if(inarow_Neast(ox, row, col, self.data, 4) or inarow_Nnortheast(ox, row, col, self.data, 4) or inarow_Nsouth(ox, row, col, self.data, 4) or inarow_Nsoutheast(ox, row, col, self.data, 4)):
                    return True
        return False
    def check(self):
        if(self.winsFor('X')):
                print("X wins! congrats")
                return True
        if(self.winsFor('O')):
                print("O wins!-congrats")
                return True
    def greeting(self):
        print("Choose an option:")
        print()
        print("(1) Play yourself")
        print("(2) Play AI")
        print("(3) Watch AI")
        choice=int(input("Enter your choice: "))
        return choice
    def hostGame(self):
        print("Welcome to Thelford's Connect Four Game")
        p1=str(input("Player 1 is X or O? "))
        a=self.greeting()
        if(p1 not in "OX"):
            print("Incorrect choice. Default is O")
            p1='O'
        if(a==1):
            print("(1) Play yourself:")
            self.startGame(p1, False, False)
        elif(a==2):
            print("(2) Playing against AI")
            turn=int(input("You first(1) or AI first(2)? "))
            if(turn==1):
                self.startGame(p1, False, True)
            else:
                self.startGame(p1, True, False)
        elif(a==3):
            print("(3) Watching AI")
            self.startGame(p1, True, True)
    def startGame(self, ox, p1isai, p2isai):
        if(ox=='X'):
            other='O'
        else:
            other='X'
        ch='Y'
        while(not self.isFull() and ch=='Y'):
            print(self)
            print("Player 1 goes")
            if(p1isai==False):
                choice=int(input("Player 1({})'s choice: ".format(ox)))
            else:
                choice=self.aiMove(ox)
            if(self.allowsMove(choice)):
                self.addMove(choice, ox)
            else:
                print("Invalid move! Try again: ")
            print(self)
            print("Player 2 goes")
            if(self.check()):
                break
            if(not p2isai):
                choice=int(input("Player 2({})'s choice: ".format(other)))
            else:
                choice=self.aiMove(other)
            if(self.allowsMove(choice)):
                self.addMove(choice, other)
            else:
                print("Invalid move! Try again: ")
            if(self.check()):
                print(self)
                break
        ch=str(input("Play again(Y/N)? "))
        if(ch=='Y'):
            self.clear()
            self.hostGame()
        print("Goodbye! ")
    def colsToWin(self, ox):
        l=[]
        for col in range(self.width):
            self.addMove(col, ox)
            if(self.winsFor(ox)):
                l.append(col)
            self.delMove(col)
        return l
    def colsTwoToWin(self, ox):
        l=[]
        for col in range(self.width):
            self.addMove(col, ox)
            for i in self.colsToWin(ox):
                l.append(i)
            if(self.winsFor(ox)):
                l.append(col)
            self.delMove(col)
        return l
    def aiMove(self, ox):
        move=3
        other=''
        if(ox=='X'):
            other+='O'
        else:
            other+='X'
        if(len(self.colsToWin(ox))>0):
            return random.choice(self.colsToWin(ox))
        else:
            if(len(self.colsToWin(other))>0):
                return random.choice(self.colsToWin(other))
            else:
                if(len(self.colsTwoToWin(other))>0):
                    return random.choice(self.colsTwoToWin(other))
                else:
                    if(len(self.colsTwoToWin(other))>0):
                        return random.choice(self.colsTwoToWin(ox))
                    else:
                        if(self.data[self.height-1][3]==' '):
                            return move
                        else:
                            return random.choice([0, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 5, 5, 6])
b = Board(7, 6)
b.hostGame()
'''
print(b.aiMove('X'))
print(b.aiMove('O'))
b.setBoard('334050505')
print(b.aiMove('O'))
print(b.aiMove('X'))
#print(b.colsToWin('X'))
#print(b.colsToWin('O'))
'''
