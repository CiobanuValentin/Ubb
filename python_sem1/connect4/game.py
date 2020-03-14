from board import Board
from tools import GameException,checkMoveInList,getBestMove,get2movesAhead
class Game:
    def __init__(self):
        self.__board=Board()
    @property
    def board(self):
        '''returns the board state'''
        return self.__board
    def moveHuman(self,circle,color):
        '''gets the circle in which the human wants to move and makes the move'''
        options=self.__board.playableCircles()
        if checkMoveInList(circle,options):
            self.__board.move(circle,color)
        else:
            raise GameException('Move not allowed')
    #def moveHuman2(self,circle):
    #    self.board.move(circle,'0')
    def moveComputer(self):
        #options=self.__board.playableCircles()
        #for i in options:
        #    print('row',i.row+1,'col',i.col+1)
        '''calls the minimax function and uses it to make the next move'''
        move=getBestMove(self.__board,'_aiPlayer')
        self.__board.move(move[0],'0')
    def moveComputer2(self):
        #options=self.__board.playableCircles()
        #for i in options:
        #    print('row',i.row+1,'col',i.col+1)
        '''calls the minimax function and uses it to make the next move'''
        move=get2movesAhead(self.__board,'_aiPlayer')
        print(move[0].row,move[0].col)
        self.__board.move(move[0],'0')
        return move[0].col
