# hw9pr1.py - Game of Life lab
# Name:Thelford Williams
import random
def createOneRow(width):
    """ returns one row of zeros of width "width"...  
         You might use this in your createBoard(width, height) function """
    row = []
    for col in range(width):
        row += [0]
    return row
def createBoard(width, height):
    """Returns a 2D array with "height" rows and "width" columns."""
    A = []
    for row in range(height):
        A += [createOneRow(width)]        # use the above function so that SOMETHING is one row!!
    return A
#A=createBoard(5, 3)
def printBoard(A):
    """This function prints the 2D list-of-lists A."""
    for row in A:               # row is the whole row
        for col in row:         # col is the individual element
            print(col, end='')  # print that element
        print()
#printBoard(A)
def diagonalize(width, height):
    """Creates an empty board and then modifies it
       so that it has a diagonal strip of "on" cells.
       But do that only in the *interior* of the 2D array.
    """
    A = createBoard(width, height)
    for row in range(1, height - 1):
        for col in range(1, width - 1):
            if row == col:
                A[row][col] = 1
            else:
                A[row][col] = 0
    return A
#printBoard(diagonalize(7, 6))
def innerCells(width, height):
    A=createBoard(width, height)
    for row in range(1, height-1):
        for col in range(1, width-1):
            A[row][col]=1
    return A
#printBoard(innerCells(5, 5))
def randomCells(width, height):
    A=createBoard(width, height)
    for row in range(1, height-1):
        for col in range(1, width-1):
            A[row][col]=random.choice([0, 1])
    return A
#printBoard(randomCells(10, 10))
def copy(A):
    """Returns a DEEP copy of the 2D array A."""
    height = len(A)
    width = len(A[0])
    newA = createBoard(width, height)
    for row in range(1, height - 1):
        for col in range(1, width - 1):
            newA[row][col]=A[row][col]
    return newA
'''A=innerCells(5, 5)
printBoard(A)
newA=copy(A)
printBoard(newA)
A[2][2]=5
printBoard(A)
printBoard(newA)'''
def innerReverse(A):
    """Returns a DEEP copy of the 2D array A."""
    height = len(A)
    width = len(A[0])
    newA = createBoard(width, height)
    for row in range(1, height - 1):
        for col in range(1, width - 1):
            if(A[row][col]):
                newA[row][col]=0
            else:
                newA[row][col]=1
    return newA
'''A=randomCells(8, 8)
printBoard(A)
A2=innerReverse(A)
printBoard(A2)
'''
def countNeighbors(row, col, A):
    count=0
    for i in range(row-1, row+2):
        for j in range(col-1, col+2):
            if(A[i][j]==1):
                count+=1
    if(A[row][col]==1):
        return count-1
    else:
        return count
'''
A = [[0, 0, 0, 0, 0],
      [0, 0, 1, 0, 0],
      [0, 0, 1, 0, 0],
      [0, 0, 1, 0, 0],
      [0, 0, 0, 0, 0]]
printBoard(A)
print(countNeighbors(2, 1, A))
print(countNeighbors(2, 2, A))
print(countNeighbors(0, 1, A))
'''
def next_life_generation(A):
    """Makes a copy of A and then advances one
       generation of Conway's Game of Life within
       the *inner cells* of that copy.
       The outer edge always stays at 0.
    """
    newA=copy(A)
    height = len(A)
    width = len(A[0])
    for row in range(1, height - 1):
        for col in range(1, width - 1):
            if(A[row][col]==1):
                if(countNeighbors(row, col, A)<2):
                    newA[row][col]=0
                elif(countNeighbors(row, col, A)>3):
                    newA[row][col]=0
            else:
                if(countNeighbors(row, col, A)==3):
                    newA[row][col]=1
    return newA
A = [[0, 0, 0, 0, 0], [0, 0, 1, 0, 0], [0, 0, 1, 0, 0], [0, 0, 1, 0, 0], [0, 0, 0, 0, 0]]
printBoard(A)
print()
A2=next_life_generation(A)
printBoard(A2)
print()
A3 = next_life_generation(A2)
printBoard(A3)

                




